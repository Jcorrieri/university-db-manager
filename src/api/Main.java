package api;

import gui.CustomJFrame;

import java.sql.*;
import java.util.ArrayList;

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

    public static String[] queryGrade(String nNumber) {
        String result = "";
        String q = """
                    SELECT COURSE_NUM, SECTION_NUM, SEMESTER, YEAR, GRADE
                    FROM ASSIGNED_TO
                    WHERE NNUMBER = ?""";

        double totalGpa = 0;
        double gpa = 0;
        int count = 0;
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
                switch (grade) {
                    case "A" -> gpa = 4.0;
                    case "A-" -> gpa = 3.7;
                    case "B+" -> gpa = 3.3;
                    case "B" -> gpa = 3.0;
                    case "B-" -> gpa = 2.7;
                    case "C+" -> gpa = 2.3;
                    case "C" -> gpa = 2.0;
                    case "D" -> gpa = 1.0;
                    case "F" -> gpa = 0.0;
                }
                totalGpa = (totalGpa + gpa) / ++count;

                result = course + ", " + section + ", " + semester + ", " + year + ", " + gpa + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + nNumber);
            return new String[]{"",""};
        }
        return new String[]{result, "" + totalGpa};
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
                result = firstName + " " + lastName + ", " + nNumber;
            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + nNumber);
            return "";
        }
        return result;
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

    public static boolean addStudentToDatabase(ArrayList<String> studentData) {
        // Process student data and add to database... (Front-end task #1)

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
                pstmt = conn.prepareStatement("INSERT INTO MAJORS_IN(NNUMBER, DEPT_CODE) " +
                        "VALUES (?, ?)");

                pstmt.setString(1, nNumber); // Cannot be null
                pstmt.setString(2, major); // Cannot be null

                pstmt.executeUpdate();
            }

            // Add any minors
            if (!minor.equals("")) {
                pstmt = conn.prepareStatement("INSERT INTO MINORS_IN(NNUMBER, DEPT_CODE) " +
                        "VALUES (?, ?)");

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

    public static boolean addInstructor(ArrayList<String> instructorData) {
        // Process instructor data and add to database... (Front-end task #1)
        // Call addPerson()
        logger.logInstructor(instructorData.toString());
        return true;
    }

    public static boolean addDepartment(ArrayList<String> departmentData) {
        // Process department data and add to database... (Front-end task #1)
        logger.logDepartment(departmentData.toString());
        return true;
    }

    public static boolean addCourse(ArrayList<String> courseData) {
        // Process course data and add to database... (Front-end task #1)
        logger.logCourse(courseData.toString());
        return true;
    }

    public static boolean addSection(ArrayList<String> sectionData) {
        // Process section data and add to database... (Front-end task #1)
        logger.logSection(sectionData.toString());
        return true;
    }

    public static boolean addStudentToSection(ArrayList<String> studentData) {
        // Process section student data and add to database... (Front-end task #2)
        logger.logSectionStudent(studentData.toString());
        return true;
    }

    public static boolean addGrade(ArrayList<String> gradeData) {
        // Process grade data and add to database... (Front-end task #6)
        logger.logGrade(gradeData.toString());
        return true;
    }
}