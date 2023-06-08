package gui;

import api.DataHandler;
import gui.dialogs.EntityLog;
import gui.dialogs.ViewDialog;
import gui.panels.DepartmentCoursePanel;
import gui.panels.InstructorSectionPanel;
import gui.panels.StudentToSectionPanel;
import gui.panels.StudentToDbPanel;

import javax.swing.*;
import java.awt.*;

public class CustomJFrame extends JFrame {

    public static final int WIDTH = 1000, HEIGHT = 720;
    private JPanel currentPanel;
    private final DataHandler dataHandler;
    private final JMenuBar menuBar;

    private final CustomJPanel ADD_STUDENT = new StudentToDbPanel();
    private final CustomJPanel ADD_DEPT_COURSE = new DepartmentCoursePanel();
    private final CustomJPanel ADD_INST_SECT = new InstructorSectionPanel();
    private final CustomJPanel ADD_STD_COURSE = new StudentToSectionPanel();

    public CustomJFrame(DataHandler dataHandler) {
        super();
        setSize(WIDTH, HEIGHT);
        setTitle("Intro to Databases Project");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setResizable(false);

        ADD_STUDENT.setDataHandler(dataHandler);
        ADD_DEPT_COURSE.setDataHandler(dataHandler);
        ADD_INST_SECT.setDataHandler(dataHandler);
        ADD_STD_COURSE.setDataHandler(dataHandler);

        this.dataHandler = dataHandler;
        menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(WIDTH, 25));

        initEntityMenu();
        initViewMenu();
        initLogMenu();

        currentPanel = ADD_DEPT_COURSE;
        add(currentPanel, BorderLayout.CENTER);
        setJMenuBar(menuBar);
    }

    private void initEntityMenu() {
        // Add Entities
        JMenu addEntityMenu = new JMenu(" Add Entity ⌵ ");
        addEntityMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        JMenuItem deptAndCourses = new JMenuItem("DEPARTMENT and/or COURSES");
        JMenuItem instructorAndSection = new JMenuItem("INSTRUCTOR and/or SECTION");
        JMenuItem studentInfo = new JMenuItem("STUDENT to DATABASE");
        JMenuItem addStudentToCourse = new JMenuItem("STUDENT to COURSE/SECTION");

        deptAndCourses.addActionListener(e -> updatePanel(ADD_DEPT_COURSE));
        instructorAndSection.addActionListener(e -> updatePanel(ADD_INST_SECT));
        studentInfo.addActionListener(e -> updatePanel(ADD_STUDENT));
        addStudentToCourse.addActionListener(e -> updatePanel(ADD_STD_COURSE));

        addEntityMenu.add(deptAndCourses);
        addEntityMenu.add(instructorAndSection);
        addEntityMenu.add(studentInfo);
        addEntityMenu.add(addStudentToCourse);
        menuBar.add(addEntityMenu);
    }

    private void initViewMenu() {
        // Get Views
        JMenu getViewMenu = new JMenu(" Get View ⌵ ");
        getViewMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        JMenuItem gradeReport = new JMenuItem("Grade Report");
        JMenuItem getCourses = new JMenuItem("Courses by Department");
        JMenuItem getSections = new JMenuItem("Sections by Instructor");

        gradeReport.addActionListener(e -> new ViewDialog(this, CustomJPanel.GRADE_REPORT));
        getCourses.addActionListener(e -> new ViewDialog(this, CustomJPanel.COURSES));
        getSections.addActionListener(e -> new ViewDialog(this, CustomJPanel.SECTIONS));

        getViewMenu.add(gradeReport);
        getViewMenu.add(getCourses);
        getViewMenu.add(getSections);
        menuBar.add(getViewMenu);
    }

    private void initLogMenu() {
        // Log
        JMenu logMenu = new JMenu(" Log ⌵ ");
        logMenu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        JMenuItem studentLog = new JMenuItem("Students");
        JMenuItem instructorLog = new JMenuItem("Instructors");
        JMenuItem deptLog = new JMenuItem("Departments");
        JMenuItem courseLog = new JMenuItem("Courses");
        JMenuItem sectionLog = new JMenuItem("Sections");
        JMenuItem sectionStudentLog = new JMenuItem("Section Students");
        JMenuItem gradeLog = new JMenuItem("Grades");

        studentLog.addActionListener(e -> new EntityLog(this, CustomJPanel.STUDENTS_TO_DB, dataHandler));
        instructorLog.addActionListener(e -> new EntityLog(this, CustomJPanel.INSTRUCTORS, dataHandler));
        deptLog.addActionListener(e -> new EntityLog(this, CustomJPanel.DEPARTMENTS, dataHandler));
        courseLog.addActionListener(e -> new EntityLog(this, CustomJPanel.COURSES, dataHandler));
        sectionLog.addActionListener(e -> new EntityLog(this, CustomJPanel.SECTIONS, dataHandler));
        sectionStudentLog.addActionListener(e -> new EntityLog(this, CustomJPanel.STUDENTS_TO_SECTION, dataHandler));
        gradeLog.addActionListener(e -> new EntityLog(this, CustomJPanel.GRADE_REPORT, dataHandler));

        logMenu.add(studentLog);
        logMenu.add(instructorLog);
        logMenu.add(deptLog);
        logMenu.add(courseLog);
        logMenu.add(sectionLog);
        logMenu.add(sectionStudentLog);
        logMenu.add(gradeLog);
        menuBar.add(logMenu);
    }

    private void updatePanel(JPanel panel) {
        remove(currentPanel);
        currentPanel = panel;
        add(currentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
}
