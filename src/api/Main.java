package api;

import gui.CustomJFrame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static gui.CustomJPanel.*;

public class Main {

    // Store added entities for logging
    private static ArrayList<String> students, instructors, departments, courses, sections, sectStudents, grades;

    private static Connection conn;

    public static void main(String[] args) throws SQLException {
        String uid = "G02";
        String pword = "tZIzV8tK";

        String url = "jdbc:oracle:thin:@cisvm-oracle.unfcsd.unf.edu:1521:orcl";

        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

        conn = DriverManager.getConnection(url, uid, pword);

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

    public static boolean addStudentToDatabase(String studentData) {
        // Process student data and add to database... (Front-end task #1)
        students.add(studentData);
        return true;
    }

    public static boolean addInstructor(String instructorData) {
        // Process instructor data and add to database... (Front-end task #1)
        instructors.add(instructorData);
        return true;
    }

    public static boolean addDepartment(String departmentData) {
        // Process department data and add to database... (Front-end task #1)
        departments.add(departmentData);
        return true;
    }

    public static boolean addCourse(String courseData) {
        // Process course data and add to database... (Front-end task #1)
        courses.add(courseData);
        return true;
    }

    public static boolean addSection(String sectionData) {
        // Process section data and add to database... (Front-end task #1)
        sections.add(sectionData);
        return true;
    }

    public static boolean addStudentToSection(String studentData) {
        // Process section student data and add to database... (Front-end task #2)
        sectStudents.add(studentData);
        return true;
    }

    public static boolean addGrade(String gradeData) {
        // Process grade data and add to database... (Front-end task #6)
        grades.add(gradeData);
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