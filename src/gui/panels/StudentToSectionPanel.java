package gui.panels;

import gui.CustomJPanel;
import gui.HorizontalRule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentToSectionPanel extends CustomJPanel {

    private final ArrayList<JTextField> studentFields = new ArrayList<>();
    private final ArrayList<JTextField> gradeFields = new ArrayList<>();

    public StudentToSectionPanel() {
        super();

        GridBagConstraints gbc = new GridBagConstraints();
        final int MAX_COLS = 4;

        // Header elements (title and gray divider)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = MAX_COLS; // Center the title (title takes up entire row)
        JLabel addGeneral = new JLabel("Add Students to Sections");
        addGeneral.setFont(new Font( Font.SANS_SERIF, Font.PLAIN, 30 ));
        add(addGeneral, gbc);

        gbc.insets = new Insets(15, 0, 0, 0);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new HorizontalRule(550), gbc);
        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel subtitle = new JLabel("Add Students:");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        add(subtitle, gbc);

        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.anchor = GridBagConstraints.CENTER;

        /*  *  *  *  *  *  *  *  *
         *  STUDENT/COURSE INFO  *
         *  *  *  *  *  *  *  *  */

        // Student/Course info labels
        gbc.gridy = 3;  gbc.gridwidth = 1;

        gbc.gridx = 1;
        add(new JLabel("N_Number"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Course No."), gbc);

        // Student/Course info fields
        gbc.gridy = 4;

        gbc.gridx = 1;
        JTextField nNumber = new JTextField(9);
        nNumber.setName("N_Number");
        studentFields.add(nNumber);
        add(nNumber, gbc);

        gbc.gridx = 2;
        JTextField courseNum = new JTextField(9);
        courseNum.setName("Course No.");
        studentFields.add(courseNum);
        add(courseNum, gbc);

        // Section info labels
        gbc.gridy = 5;

        gbc.gridx = 0; gbc.gridwidth = 2;
        add(new JLabel("Section No."), gbc);

        gbc.gridx = 0; gbc.gridwidth = MAX_COLS;
        add(new JLabel("Section Semester"), gbc);

        gbc.gridx = 2; gbc.gridwidth = 2;
        add(new JLabel("Section Year"), gbc);

        // Section info fields
        gbc.gridy = 6;

        gbc.gridx = 0;
        JTextField sectionNum = new JTextField(9);
        sectionNum.setName("Section No.");
        studentFields.add(sectionNum);
        add(sectionNum, gbc);

        gbc.gridx = 0; gbc.gridwidth = MAX_COLS;
        JTextField semester = new JTextField(9);
        semester.setName("Semester");
        studentFields.add(semester);
        add(semester, gbc);

        gbc.gridx = 2; gbc.gridwidth = 2;
        JTextField year = new JTextField(5);
        year.setName("Year");
        studentFields.add(year);
        add(year, gbc);

        // Clear and submit buttons
        gbc.gridwidth = MAX_COLS;
        gbc.gridx = 0; gbc.gridy = 7; gbc.insets = new Insets(5, 0, 15, 0);
        add(new HorizontalRule(550), gbc);

        gbc.gridy = 8; gbc.gridwidth = 1;

        gbc.gridx = 1;
        JButton clearBtnDept = new JButton("Clear");
        clearBtnDept.setFocusPainted(false);
        clearBtnDept.addActionListener(e -> clearFields(studentFields));
        add(clearBtnDept, gbc);

        gbc.gridx = 2;
        JButton submitBtnDept = new JButton("Add");
        submitBtnDept.setFocusPainted(false);
        submitBtnDept.addActionListener(e -> confirmAdd("ADD STUDENT TO SECTION", studentFields, STUDENTS_TO_SECTION));
        add(submitBtnDept, gbc);

        /*  *  *  *  *  *  *  *
         *     GRADE INFO     *
         *  *  *  *  *  *  *  */

        // Header
        gbc.gridwidth = MAX_COLS;
        gbc.gridx = 0; gbc.gridy = 9; gbc.insets = new Insets(15, 0, 0, 0);
        add(new HorizontalRule(550), gbc);

        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 10;
        subtitle = new JLabel("Add Grades:");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        add(subtitle, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 15, 5, 15);

        // Grade info labels
        gbc.gridy = 11;

        gbc.gridx = 1;
        add(new JLabel("Student N_Number"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Letter Grade"), gbc);

        // Grade info fields
        gbc.gridy = 12;

        gbc.gridx = 1;
        JTextField nNumberAddGrade = new JTextField(9);
        nNumberAddGrade.setName("Student N_Number");
        gradeFields.add(nNumberAddGrade);
        add(nNumberAddGrade, gbc);

        gbc.gridx = 2;
        JTextField grade = new JTextField(9);
        grade.setName("Letter Grade");
        gradeFields.add(grade);
        add(grade, gbc);

        // Course info labels
        gbc.gridy = 13;

        gbc.gridx = 0;
        add(new JLabel("Course No."), gbc);

        gbc.gridx = 1;
        add(new JLabel("Section No."), gbc);

        gbc.gridx = 2;
        add(new JLabel("Semester"), gbc);

        gbc.gridx = 3;
        add(new JLabel("Year"), gbc);

        // Course info fields
        gbc.gridy = 14;

        gbc.gridx = 0;
        JTextField courseNumAddGrade = new JTextField(9);
        courseNumAddGrade.setName("Course No.");
        gradeFields.add(courseNumAddGrade);
        add(courseNumAddGrade, gbc);

        gbc.gridx = 1;
        JTextField sectionNumAddGrade = new JTextField(9);
        sectionNumAddGrade.setName("Section No.");
        gradeFields.add(sectionNumAddGrade);
        add(sectionNumAddGrade, gbc);

        gbc.gridx = 2;
        JTextField semesterAddGrade = new JTextField(9);
        semesterAddGrade.setName("Semester");
        gradeFields.add(semesterAddGrade);
        add(semesterAddGrade, gbc);

        gbc.gridx = 3;
        JTextField yearAddGrade = new JTextField(9);
        yearAddGrade.setName("Year");
        gradeFields.add(yearAddGrade);
        add(yearAddGrade, gbc);

        /*  *  *  *  *  *  *
         *      FOOTER     *
         *  *  *  *  *  *  */

        // Clear and submit buttons
        gbc.gridwidth = MAX_COLS;
        gbc.gridx = 0; gbc.gridy = 15; gbc.insets = new Insets(5, 0, 15, 0);
        add(new HorizontalRule(550), gbc);

        gbc.gridy = 16; gbc.gridwidth = 1;

        gbc.gridx = 1;
        JButton clearBtnCourse = new JButton("Clear");
        clearBtnCourse.setFocusPainted(false);
        clearBtnCourse.addActionListener(e -> clearFields(gradeFields));
        add(clearBtnCourse, gbc);

        gbc.gridx = 2;
        JButton submitBtnCourse = new JButton("Add");
        submitBtnCourse.setFocusPainted(false);
        submitBtnCourse.addActionListener(e -> confirmAdd("ADD GRADE", gradeFields, GRADE_REPORT));
        add(submitBtnCourse, gbc);

        // Padding
        gbc.insets = new Insets(0, 0, 98, 0);

        gbc.gridy = 17;
        add(new JSeparator(), gbc);
    }
}
