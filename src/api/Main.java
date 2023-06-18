package api;

import gui.CustomJFrame;

import java.sql.*;
import java.util.ArrayList;

import static gui.CustomJPanel.*;

public class Main {

    // Store added entities for logging
    private static ArrayList<String> students, instructors, departments, courses, sections, sectStudents, grades;

    private static Connection conn;

    public static void main(String[] args) throws SQLException {
        String uid = "G02";
        String password = "tZIzV8tK";

        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        conn = DriverManager.getConnection(url, uid, password);

        // To log added entities
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        departments = new ArrayList<>();
        courses = new ArrayList<>();
        sections = new ArrayList<>();
        sectStudents = new ArrayList<>();
        grades = new ArrayList<>();

        CustomJFrame base = new CustomJFrame();
        base.setLocationRelativeTo(null);
        base.setVisible(true);
    }

    // 'type' can be GRADE_REPORT, GEN_STD_INFO, COURSES, or SECTIONS
    public static String generateQuery(int type, String input) {
        String result = "";
        String q = "";

        switch (type) {
            case GRD_STD_INFO -> q = """
                    SELECT F_name, L_name, Nnumber
                    FROM PERSON
                    WHERE Nnumber = ?""";
            case GRADE_REPORT -> q = "SELECT...";
            case COURSES -> q = "SELECT....";
            case SECTIONS -> q = "SELECT.....";
        }

        try {
            PreparedStatement pstmt = conn.prepareStatement(q);
            pstmt.setString(1, input);
        } catch (SQLException e) {
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

        // Must check if null
        if ((phone.equals("")))
            pstmt.setNull(3, Types.CHAR);
        else
            pstmt.setString(3, phone);

        if (fName.equals(""))
            pstmt.setNull(4, Types.VARCHAR);
        else
            pstmt.setString(4, fName);

        if (mInit.equals(""))
            pstmt.setNull(5, Types.CHAR);
        else
            pstmt.setString(5, mInit);

        if (lName.equals(""))
            pstmt.setNull(6, Types.VARCHAR);
        else
            pstmt.setString(6, lName);

        if (address.equals(""))
            pstmt.setNull(7, Types.VARCHAR);
        else
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

            // Must check if null
            if (birthdate.equals(""))
                pstmt.setNull(2, Types.DATE);
            else // this converts the String object into a Date object (yyyy-mm-dd)
                pstmt.setDate(2, Date.valueOf(birthdate));

            if (sex.equals(""))
                pstmt.setNull(3, Types.CHAR);
            else
                pstmt.setString(3, sex);

            if (permPhone.equals(""))
                pstmt.setNull(4, Types.CHAR);
            else
                pstmt.setString(4, permPhone);

            if (permState.equals(""))
                pstmt.setNull(5, Types.CHAR);
            else
                pstmt.setString(5, permState);

            if (permCity.equals(""))
                pstmt.setNull(6, Types.VARCHAR);
            else
                pstmt.setString(6, permCity);

            if (permZip.equals(""))
                pstmt.setNull(7, Types.INTEGER);
            else
                pstmt.setInt(7, Integer.parseInt(permZip));

            if (stdClass.equals(""))
                pstmt.setNull(8, Types.VARCHAR);
            else
                pstmt.setString(8, stdClass);

            if (degree.equals(""))
                pstmt.setNull(9, Types.VARCHAR);
            else
                pstmt.setString(9, degree);

            int numRows = pstmt.executeUpdate();
            System.out.println(numRows + " row(s) inserted");
        } catch (SQLException | NumberFormatException e) {
            return false;
        }

        students.add(studentData.toString());
        return true;
    }

    public static boolean addInstructor(ArrayList<String> instructorData) {
        // Process instructor data and add to database... (Front-end task #1)
        // Call addPerson()
        instructors.add(instructorData.toString());
        return true;
    }

    public static boolean addDepartment(ArrayList<String> departmentData) {
        // Process department data and add to database... (Front-end task #1)
        departments.add(departmentData.toString());
        return true;
    }

    public static boolean addCourse(ArrayList<String> courseData) {
        // Process course data and add to database... (Front-end task #1)
        courses.add(courseData.toString());
        return true;
    }

    public static boolean addSection(ArrayList<String> sectionData) {
        // Process section data and add to database... (Front-end task #1)
        sections.add(sectionData.toString());
        return true;
    }

    public static boolean addStudentToSection(ArrayList<String> studentData) {
        // Process section student data and add to database... (Front-end task #2)
        sectStudents.add(studentData.toString());
        return true;
    }

    public static boolean addGrade(ArrayList<String> gradeData) {
        // Process grade data and add to database... (Front-end task #6)
        grades.add(gradeData.toString());
        return true;
    }

    // Getter methods for logging purposes
    public static ArrayList<String> getStudentLog() { return students; }

    public static ArrayList<String> getInstructorLog() { return instructors; }

    public static ArrayList<String> getDeptLog() { return departments; }

    public static ArrayList<String> getCourseLog() { return courses; }

    public static ArrayList<String> getSectionLog() { return sections; }

    public static ArrayList<String> getSectStudentLog() { return sectStudents; }

    public static ArrayList<String> getGradeLog() { return grades; }
}