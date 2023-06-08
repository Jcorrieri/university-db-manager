package gui.panels;

import gui.CustomJPanel;
import gui.HorizontalRule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DepartmentCoursePanel extends CustomJPanel {

    private final ArrayList<JTextField> deptTextFields = new ArrayList<>();
    private final ArrayList<JTextField> courseTextFields = new ArrayList<>();

    public DepartmentCoursePanel() {
        super();

        GridBagConstraints gbc = new GridBagConstraints();
        final int MAX_COLS = 3;

        // Header elements (title and gray divider)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = MAX_COLS; // Center the title (title takes up entire row)
        JLabel addGeneral = new JLabel("Add Departments and Courses");
        addGeneral.setFont(new Font( Font.SANS_SERIF, Font.PLAIN, 30 ));
        add(addGeneral, gbc);

        gbc.insets = new Insets(15, 0, 0, 0);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new HorizontalRule(), gbc);
        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel subtitle = new JLabel("Add Departments:");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        add(subtitle, gbc);

        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.anchor = GridBagConstraints.LINE_START; // Start elements at left of grid cell
        gbc.gridwidth = 1;

        /*  *  *  *  *  *  *  *  *
         *    DEPARTMENT INFO    *
         *  *  *  *  *  *  *  *  */

        // Department info labels
        gbc.gridy = 3;

        gbc.gridx = 0;
        add(new JLabel("Name"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Code"), gbc);

        gbc.gridx = 2;
        add(new JLabel("College"), gbc);

        // Department info fields
        gbc.gridy = 4;

        gbc.gridx = 0;
        JTextField name = new JTextField(20);
        name.setName("Name");
        deptTextFields.add(name);
        add(name, gbc);

        gbc.gridx = 1;
        JTextField code = new JTextField(8);
        code.setName("Code");
        deptTextFields.add(code);
        add(code, gbc);

        gbc.gridx = 2;
        JTextField college = new JTextField(20);
        college.setName("College");
        deptTextFields.add(college);
        add(college, gbc);

        // Office info labels
        gbc.gridy = 5;

        gbc.gridx = 0;
        add(new JLabel("Office No."), gbc);

        gbc.gridx = 1;
        add(new JLabel("Office Phone"), gbc);

        // Office info fields
        gbc.gridy = 6;

        gbc.gridx = 0;
        JTextField officeNum = new JTextField(9);
        officeNum.setName("Office No.");
        deptTextFields.add(officeNum);
        add(officeNum, gbc);

        gbc.gridx = 1;
        JTextField officePhone = new JTextField(9);
        officePhone.setName("Office Phone");
        deptTextFields.add(officePhone);
        add(officePhone, gbc);

        // Clear and submit buttons
        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 7; gbc.insets = new Insets(5, 0, 15, 0);
        add(new HorizontalRule(), gbc);

        gbc.gridx = 1; gbc.gridy = 8; gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.LINE_START;
        JButton clearBtnDept = new JButton("Clear");
        clearBtnDept.setFocusPainted(false);
        clearBtnDept.addActionListener(e -> clearFields(deptTextFields));
        add(clearBtnDept, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        JButton submitBtnDept = new JButton("Add");
        submitBtnDept.setFocusPainted(false);
        submitBtnDept.addActionListener(e -> confirmAdd("ADD DEPARTMENT", deptTextFields, DEPARTMENTS));
        add(submitBtnDept, gbc);

        /*  *  *  *  *  *  *  *  *
         *      COURSE INFO      *
         *  *  *  *  *  *  *  *  */

        // Header
        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 9; gbc.insets = new Insets(15, 0, 0, 0);
        add(new HorizontalRule(), gbc);

        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 10;
        subtitle = new JLabel("Add Courses:");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        add(subtitle, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 15, 5, 15);

        // Course info labels
        gbc.gridy = 11;

        gbc.gridx = 0;
        add(new JLabel("Name"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Description"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Course No."), gbc);

        // Course info fields
        gbc.gridy = 12;

        gbc.gridx = 0;
        JTextField courseName = new JTextField(20);
        courseName.setName("Name");
        courseTextFields.add(courseName);
        add(courseName, gbc);

        gbc.gridx = 1;
        JTextField description = new JTextField(20);
        description.setName("Description");
        courseTextFields.add(description);
        add(description, gbc);

        gbc.gridx = 2;
        JTextField courseNum = new JTextField(8);
        courseNum.setName("Course No.");
        courseTextFields.add(courseNum);
        add(courseNum, gbc);

        // Department, level and hours labels
        gbc.gridy = 13;

        gbc.gridx = 0;
        add(new JLabel("Offering Department"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Level"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Semester Hours"), gbc);

        // Department, level and hours fields
        gbc.gridy = 14;

        gbc.gridx = 0;
        JTextField offeringDept = new JTextField(20);
        offeringDept.setName("Offering Department");
        courseTextFields.add(offeringDept);
        add(offeringDept, gbc);

        gbc.gridx = 1;
        JTextField level = new JTextField(20);
        level.setName("Level");
        courseTextFields.add(level);
        add(level, gbc);

        gbc.gridx = 2;
        JTextField semesterHours = new JTextField(5);
        semesterHours.setName("Semester Hours");
        courseTextFields.add(semesterHours);
        add(semesterHours, gbc);

        /*  *  *  *  *  *  *
         *      FOOTER     *
         *  *  *  *  *  *  */

        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 15; gbc.insets = new Insets(5, 0, 15, 0);
        add(new HorizontalRule(), gbc);

        // Clear and submit buttons
        gbc.gridx = 1; gbc.gridy = 16; gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.LINE_START;
        JButton clearBtnCourse = new JButton("Clear");
        clearBtnCourse.setFocusPainted(false);
        clearBtnCourse.addActionListener(e -> clearFields(courseTextFields));
        add(clearBtnCourse, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        JButton submitBtnCourse = new JButton("Add");
        submitBtnCourse.setFocusPainted(false);
        submitBtnCourse.addActionListener(e -> confirmAdd("ADD COURSE", courseTextFields, COURSES));
        add(submitBtnCourse, gbc);

        // Padding
        gbc.insets = new Insets(0, 0, 98, 0);

        gbc.gridy = 17;
        add(new JSeparator(), gbc);
    }
}
