package api;

import java.util.ArrayList;

public class DataHandler {

    // Store added entities for logging
    private final ArrayList<String> studentLog, instructorLog, deptLog, courseLog, sectionLog, sectStudentLog, gradeLog;

    public DataHandler() {
        studentLog = new ArrayList<>();
        instructorLog = new ArrayList<>();
        deptLog = new ArrayList<>();
        courseLog = new ArrayList<>();
        sectionLog = new ArrayList<>();
        gradeLog = new ArrayList<>();
        sectStudentLog = new ArrayList<>();
    }

    // Getter methods for logging purposes
    public ArrayList<String> getStudentLog() { return studentLog; }

    public ArrayList<String> getInstructorLog() { return instructorLog; }

    public ArrayList<String> getDeptLog() { return deptLog; }

    public ArrayList<String> getCourseLog() { return courseLog; }

    public ArrayList<String> getSectionLog() { return sectionLog; }

    public ArrayList<String> getSectStudentLog() { return sectStudentLog; }

    public ArrayList<String> getGradeLog() { return gradeLog; }

    /*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
     *    USE THESE METHODS TO SEND ENTITY DATA TO PROGRAM    *
     *           (Return false if entry is invalid)           *
     *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  */

    public boolean addStudent(String studentData) {
        // Process student data and add to database... (Front-end task #1)
        studentLog.add(studentData);
        return true;
    }

    public boolean addInstructor(String instructorData) {
        // Process instructor data and add to database... (Front-end task #1)
        instructorLog.add(instructorData);
        return true;
    }

    public boolean addDepartment(String departmentData) {
        // Process department data and add to database... (Front-end task #1)
        deptLog.add(departmentData);
        return true;
    }

    public boolean addCourse(String courseData) {
        // Process course data and add to database... (Front-end task #1)
        courseLog.add(courseData);
        return true;
    }

    public boolean addSection(String sectionData) {
        // Process section data and add to database... (Front-end task #1)
        sectionLog.add(sectionData);
        return true;
    }

    public boolean addStudentToSection(String studentData) {
        // Process section student data and add to database... (Front-end task #2)
        sectStudentLog.add(studentData);
        return true;
    }

    public boolean addGrade(String gradeData) {
        // Process grade data and add to database... (Front-end task #6)
        gradeLog.add(gradeData);
        return true;
    }
}
