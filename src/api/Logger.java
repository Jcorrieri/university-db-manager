package api;

import java.util.ArrayList;

public class Logger {

    // Store added entities for logging
    private final ArrayList<String> students, instructors, departments, courses, sections, sectStudents, grades;

    public Logger() {
        // To log added entities
        students = new ArrayList<>();
        instructors = new ArrayList<>();
        departments = new ArrayList<>();
        courses = new ArrayList<>();
        sections = new ArrayList<>();
        sectStudents = new ArrayList<>();
        grades = new ArrayList<>();
    }

    // Getter methods for logging purposes
    public ArrayList<String> getStudentLog() { return students; }

    public ArrayList<String> getInstructorLog() { return instructors; }

    public ArrayList<String> getDeptLog() { return departments; }

    public ArrayList<String> getCourseLog() { return courses; }

    public ArrayList<String> getSectionLog() { return sections; }

    public ArrayList<String> getSectStudentLog() { return sectStudents; }

    public ArrayList<String> getGradeLog() { return grades; }

    // Setter methods for logging purposes
    public void logStudent(String student) { students.add(student); }

    public void logInstructor(String instructor) { instructors.add(instructor); }

    public void logDepartment(String department) { departments.add(department); }

    public void logCourse(String course) { courses.add(course); }

    public void logSection(String section) { sections.add(section); }

    public void logSectionStudent(String sectStudent) { sectStudents.add(sectStudent); }

    public void logGrade(String gradeData) { grades.add(gradeData); }
}
