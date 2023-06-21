package api;

import gui.CustomJFrame;
import gui.dialogs.ViewDialog;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("DuplicatedCode")
public class Main {

    private static Connection conn;
    public static Logger logger;

    public static void main(String[] args) throws SQLException {
        String uid = "G02";
        String password = "tZIzV8tK";

        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        conn = DriverManager.getConnection(url, uid, password);

        logger = new Logger();

        CustomJFrame base = new CustomJFrame();
        base.setLocationRelativeTo(null);
        base.setVisible(true);
    }

    // Gets the section/grade info for the grade report
    public static String[] queryGrade(String nNumber) {
        String result = "";
        String q = """
                    SELECT COURSE_NUM, SECTION_NUM, SEMESTER, YEAR, GRADE
                    FROM ASSIGNED_TO
                    WHERE NNUMBER = ?""";

        double attempted = 0;
        double earned = 0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(q);
            pstmt.setString(1, nNumber);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String course = resultSet.getString("COURSE_NUM");
                int section = resultSet.getInt("SECTION_NUM");
                String semester = resultSet.getString("SEMESTER");
                int year = resultSet.getInt("YEAR");

                String grade = resultSet.getString("GRADE"); // Add grade and calculate GPA

                double gradePoint = 0;
                int hours = getQualityHours(course);
                switch (grade) {
                    case "A" -> gradePoint = 4.0;
                    case "A-" -> gradePoint = 3.7;
                    case "B+" -> gradePoint = 3.3;
                    case "B" -> gradePoint = 3.0;
                    case "B-" -> gradePoint = 2.7;
                    case "C+" -> gradePoint = 2.3;
                    case "C" -> gradePoint = 2.0;
                    case "D" -> gradePoint = 1.0;
                    case "F" -> gradePoint = 0.0;
                }
                earned += (gradePoint) * hours;
                attempted += hours;

                result += course + ", " + section + ", " + semester + ", " + year + ", " + gradePoint + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + nNumber);
            return new String[]{"",""};
        }
        return new String[]{result, "" + (earned / attempted)};
    }

    public static String queryStudent(String nNumber) {
        String result = "";
        String q = """
                    SELECT F_NAME, L_NAME
                    FROM PERSON
                    WHERE NNUMBER = ?""";

        try {
            PreparedStatement pstmt = conn.prepareStatement(q);
            pstmt.setString(1, nNumber);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("F_NAME");
                String lastName = resultSet.getString("L_NAME");
                result = firstName + ",   " + lastName + ",   " + nNumber;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + nNumber);
            return "";
        }
        return result;
    }

    // Get the courses offered by a department
    public static String queryCourses(String dept, int type) {
        // Make sure to differentiate between dept_code and dept_name as they are both valid inputs
        if (type == ViewDialog.DEPT_NAME) {
            System.out.println("NAME");
        } else {
            System.out.println("CODE");
        }

        return "dummy";
    }

    // Get the sections a given instructor teaches
    public static String querySections(String nNumber) {
        // Instructor nNumber is input
        return "dummy";
    }

    // Get quality hours for GPA calculation
    private static int getQualityHours(String course) {
        try {
            String q = """
                    SELECT SEM_HOURS
                    FROM COURSE
                    WHERE COURSE_NUM = ?
                    """;
            PreparedStatement pstmt = conn.prepareStatement(q);
            pstmt.setString(1, course);
            ResultSet resultSet = pstmt.executeQuery();

            int hours = -1;
            while (resultSet.next())
                hours = resultSet.getInt("SEM_HOURS");

            return hours;
        } catch (SQLException e) {
            return -1;
        }
    }

    /*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
     *    USE THESE METHODS TO SEND ENTITY DATA TO PROGRAM    *
     *           (Return false if entry is invalid)           *
     *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  */

    private static void addPerson(String[] personData) throws SQLException {
        /* Insert into PERSON
         * 'nNumber', 'ssn', 'phone', 'F_name', 'M_init', 'L_name', 'Address'
         * personData[0], personData[1], ..., personData[7]
         * Prepare to insert new person into the PERSON table
         */
        String nNumber = personData[0];
        String ssn = personData[1];
        String phone = personData[2];
        String fName = personData[3];
        String mInit = personData[4];
        String lName = personData[5];
        String address = personData[6];

        String q = "INSERT INTO PERSON(NNUMBER, SSN, PHONE, F_NAME, M_INIT, L_NAME, ADDRESS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstmt = conn.prepareStatement(q);

        pstmt.setString(1, nNumber); // Cannot be null
        pstmt.setString(2, ssn); // Cannot be null
        pstmt.setString(3, phone);
        pstmt.setString(4, fName);
        pstmt.setString(5, mInit);
        pstmt.setString(6, lName);
        pstmt.setString(7, address);

        pstmt.executeUpdate();
    }

    // Process student data and add to database... (Front-end task #1)
    public static boolean addStudentToDatabase(ArrayList<String> studentData) {
        // Retrieve data from array (DIFFERENT ORDER FROM INSERTION ORDER -- SEE GUI)
        String fName = studentData.get(0);
        String mInit = studentData.get(1);
        String lName = studentData.get(2);
        String address = studentData.get(3);
        String permCity = studentData.get(4);
        String permState = studentData.get(5);
        String permZip = studentData.get(6);
        String phone = studentData.get(7);
        String permPhone = studentData.get(8);
        String birthdate = studentData.get(9);
        String sex = studentData.get(10);
        String ssn = studentData.get(11);
        String nNumber = studentData.get(12);
        String stdClass = studentData.get(13);
        String major = studentData.get(14);
        String minor = studentData.get(15);
        String degree = studentData.get(16);

        try {
            /* Insert into PERSON
             * 'nNumber', 'ssn', 'phone', 'F_name', 'M_init', 'L_name', 'Address'
             */
            addPerson(new String[]{nNumber, ssn, phone, fName, mInit, lName, address});

            /* Insert into STUDENT
             * 'nNumber', 'birthdate', 'sex', 'perm_phone', 'perm_state', 'perm_city', 'perm_zip', 'class', 'degree'
             * Prepare to insert new student into the STUDENT table
             */
            PreparedStatement pstmt =
                    conn.prepareStatement ("INSERT INTO STUDENT(NNUMBER, BIRTHDATE, SEX, PERM_PHONE, " +
                            "PERM_STATE, PERM_CITY, PERM_ZIP, CLASS_LEVEL, DEGREE_PROGRAM) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, nNumber); // Cannot be null
            pstmt.setDate(2, Date.valueOf(birthdate)); //(yyyy-mm-dd)
            pstmt.setString(3, sex);
            pstmt.setString(4, permPhone);
            pstmt.setString(5, permState);
            pstmt.setString(6, permCity);
            pstmt.setInt(7, Integer.parseInt(permZip));
            pstmt.setString(8, stdClass);
            pstmt.setString(9, degree);

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");

            // Add the major
            if (!major.equals("")) {
                pstmt = conn.prepareStatement("INSERT INTO MAJORS_IN(NNUMBER, DEPT_CODE) VALUES (?, ?)");

                pstmt.setString(1, nNumber); // Cannot be null
                pstmt.setString(2, major); // Cannot be null

                pstmt.executeUpdate();
            }

            // Add any minors
            if (!minor.equals("")) {
                pstmt = conn.prepareStatement("INSERT INTO MINORS_IN(NNUMBER, DEPT_CODE) VALUES (?, ?)");

                pstmt.setString(1, nNumber); // Cannot be null
                pstmt.setString(2, minor); // Cannot be null

                pstmt.executeUpdate();
            }
        } catch (SQLException | NumberFormatException e) {
            return false;
        }

        logger.logStudent(studentData.toString());
        return true;
    }

    // Process instructor data and add to database... (Front-end task #1)
    public static boolean addInstructor(ArrayList<String> instructorData) {
        // Call addPerson()
        String fName = instructorData.get(0);
        String mInit = instructorData.get(1);
        String lName = instructorData.get(2);
        String address = instructorData.get(3);
        String phone = instructorData.get(4);
        String ssn = instructorData.get(5);
        String age = instructorData.get(6);
        String department = instructorData.get(7);
        String nNumber = instructorData.get(8);
        String officeNo = instructorData.get(9);

        try{
            addPerson(new String[]{nNumber, ssn, phone, fName, mInit, lName, address});

            PreparedStatement pstmt =
                    conn.prepareStatement("INSERT INTO INSTRUCTOR (ASSOC_DEPART, NNUMBER, AGE, OFFICE_NUM)" +
                            " VALUES (?, ?, ?, ?)");
            pstmt.setString(1, department);
            pstmt.setString(2, nNumber);
            pstmt.setInt(3, Integer.parseInt(age));
            pstmt.setInt(4, Integer.parseInt(officeNo));

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");

        } catch (SQLException | NumberFormatException e){return false;}

        logger.logInstructor(instructorData.toString());
        return true;
    }

    // Process department data and add to database
    public static boolean addDepartment(ArrayList<String> departmentData) {
        String deptName = departmentData.get(0);
        String deptCode = departmentData.get(1);
        String college  = departmentData.get(2);
        int offNum = Integer.parseInt(departmentData.get(3));
        String offPhone = departmentData.get(4);
        try {
            PreparedStatement pstmt =
                    conn.prepareStatement ("INSERT INTO DEPARTMENT(DEPT_CODE, DEPT_NAME, OFFICE_PHONE, " +
                            "OFFICE_NUM, COLLEGE) VALUES(?, ?, ?, ?, ?)");

            pstmt.setString(1, deptCode);
            pstmt.setString(2, deptName);
            pstmt.setString(3, offPhone);
            pstmt.setInt(4, offNum);
            pstmt.setString(5, college);

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");
        } catch (SQLException e) {
            return false;
        }
        logger.logDepartment(departmentData.toString());
        return true;
    }

    // Process course data and add to database... (Front-end task #1)
    public static boolean addCourse(ArrayList<String> courseData) {
        String name = courseData.get(0);
        String description = courseData.get(1);
        String courseNum = courseData.get(2);
        String deptCode = courseData.get(3);
        int level = Integer.parseInt(courseData.get(4));
        int hours = Integer.parseInt(courseData.get(5));

        try {
            PreparedStatement pstmt =
                    conn.prepareStatement ("INSERT INTO COURSE(COURSE_NAME, COURSE_DESC, COURSE_NUM, SEM_HOURS, " +
                            "COURSE_LVL, OFFERING_DEPT) VALUES (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, courseNum);
            pstmt.setInt(4, hours);
            pstmt.setInt(5, level);
            pstmt.setString(6, deptCode);

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");
        } catch (SQLException e) {
            return false;
        }

        logger.logCourse(courseData.toString());
        return true;
    }

    // Process section data and add to database... (Front-end task #1)
    public static boolean addSection(ArrayList<String> sectionData) {
        String nNumber = sectionData.get(0);
        String courseNo = sectionData.get(1);
        String sectionNo = sectionData.get(2);
        String semester = sectionData.get(3);
        String year = sectionData.get(4);

        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO SECTION " +
                    "(SECTION_NUM,SEMESTER,YEAR,COURSE_NUM,INSTRUCTOR) VALUES (?, ?, ?, ?, ?)");

            pstmt.setInt(1, Integer.parseInt(sectionNo));
            pstmt.setString(2, semester) ;
            pstmt.setInt(3, Integer.parseInt(year));
            pstmt.setString(4, courseNo);
            pstmt.setString(5, nNumber);

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");
        } catch (SQLException e) {return false;}

        logger.logSection(sectionData.toString());
        return true;
    }

    // Process section student data and add to database... (Front-end task #2)
    public static boolean addStudentToSection(ArrayList<String> studentData) {
        String nNumber = studentData.get(0);
        String courseNo = studentData.get(1);
        String sectionNo = studentData.get(2);
        String semester = studentData.get(3);
        String year = studentData.get(4);

        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ASSIGNED_TO " +
                    "(NNUMBER,COURSE_NUM, SECTION_NUM, SEMESTER, YEAR) VALUES " +
                    "(?, ?, ?, ?, ?)");

            pstmt.setString(1, nNumber);
            pstmt.setString(2, courseNo);
            pstmt.setInt(3,Integer.parseInt(sectionNo));
            pstmt.setString(4, semester);
            pstmt.setInt(5, Integer.parseInt(year));

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");

        } catch(SQLException e) {return false;}

        logger.logSectionStudent(studentData.toString());
        return true;
    }

    // Process grade data and add to database... (Front-end task #6)
    public static boolean addGrade(ArrayList<String> gradeData) {
        String nNumber = gradeData.get(0);
        String grade = gradeData.get(1);
        String courseNo = gradeData.get(2);
        String sectionNo = gradeData.get(3);
        String semester = gradeData.get(4);
        String year = gradeData.get(5);

        try{
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ASSIGNED_TO " +
                    "(NNUMBER,COURSE_NUM, SECTION_NUM, SEMESTER, YEAR, GRADE) VALUES " +
                    "(?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, nNumber);
            pstmt.setString(2, courseNo);
            pstmt.setInt(3,Integer.parseInt(sectionNo));
            pstmt.setString(4, semester);
            pstmt.setInt(5, Integer.parseInt(year));
            pstmt.setString(6, grade);

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");

        } catch(SQLException e) {return false;}

        logger.logGrade(gradeData.toString());
        return true;
    }
}