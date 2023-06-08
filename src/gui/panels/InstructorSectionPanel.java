package gui.panels;

import gui.CustomJPanel;
import gui.HorizontalRule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InstructorSectionPanel extends CustomJPanel {

    private final ArrayList<JTextField> instructorFields = new ArrayList<>();
    private final ArrayList<JTextField> sectionFields = new ArrayList<>();

    public InstructorSectionPanel() {
        super();

        GridBagConstraints gbc = new GridBagConstraints();
        final int MAX_COLS = 3;

        // Header elements (title and gray divider)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = MAX_COLS; // Center the title (title takes up entire row)
        JLabel addGeneral = new JLabel("Add Instructors and Sections");
        addGeneral.setFont(new Font( Font.SANS_SERIF, Font.PLAIN, 30 ));
        add(addGeneral, gbc);

        gbc.insets = new Insets(15, 0, 0, 0);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new HorizontalRule(), gbc);
        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel subtitle = new JLabel("Add Instructors:");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        add(subtitle, gbc);

        gbc.insets = new Insets(5, 15, 5, 15);
        gbc.anchor = GridBagConstraints.LINE_START; // Start elements at left of grid cell
        gbc.gridwidth = 1;

        /*  *  *  *  *  *  *  *  *
         *     PERSONAL INFO     *
         *  *  *  *  *  *  *  *  */

        // Name labels
        gbc.gridy = 3;

        gbc.gridx = 0;
        add(new JLabel("First Name"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Middle Initial"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Last Name"), gbc);

        // Name fields
        gbc.gridy = 4;

        gbc.gridx = 0;
        JTextField firstName = new JTextField(20);
        firstName.setName("First Name");
        instructorFields.add(firstName);
        add(firstName, gbc);

        gbc.gridx = 1;
        JTextField middleInitial = new JTextField(2);
        middleInitial.setName("Middle Initial");
        instructorFields.add(middleInitial);
        add(middleInitial, gbc);

        gbc.gridx = 2;
        JTextField lastName = new JTextField(20);
        lastName.setName("Last Name");
        instructorFields.add(lastName);
        add(lastName, gbc);

        // Current address labels
        gbc.gridy = 5;

        gbc.gridx = 0;
        add(new JLabel("Current Address"), gbc);

        // Current address fields
        gbc.gridy = 6;

        gbc.gridx = 0; gbc.gridwidth = 2;
        JTextField address = new JTextField(33);
        address.setName("Current Address");
        instructorFields.add(address);
        add(address, gbc);

        // Phone, SSN and age labels
        gbc.gridy = 7; gbc.gridwidth = 1;

        gbc.gridx = 0;
        add(new JLabel("Phone No."), gbc);

        gbc.gridx = 1;
        add(new JLabel("SSN"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Age"), gbc);

        // Phone, SSN and age fields
        gbc.gridy = 8;

        gbc.gridx = 0;
        JTextField currentPhone = new JTextField(10);
        currentPhone.setName("Phone No.");
        instructorFields.add(currentPhone);
        add(currentPhone, gbc);

        gbc.gridx = 1;
        JTextField ssn = new JTextField(10);
        ssn.setName("SSN");
        instructorFields.add(ssn);
        add(ssn, gbc);

        gbc.gridx = 2;
        JTextField age = new JTextField(3);
        age.setName("Age");
        instructorFields.add(age);
        add(age, gbc);

        // School info labels
        gbc.gridy = 9;

        gbc.gridx = 0;
        add(new JLabel("Department"), gbc);

        gbc.gridx = 1;
        add(new JLabel("N_Number"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Office No."), gbc);

        // School info fields
        gbc.gridy = 10;

        gbc.gridx = 0;
        JTextField department = new JTextField(10);
        department.setName("Department");
        instructorFields.add(department);
        add(department, gbc);

        gbc.gridx = 1;
        JTextField nNumber = new JTextField(10);
        nNumber.setName("N_Number");
        instructorFields.add(nNumber);
        add(nNumber, gbc);

        gbc.gridx = 2;
        JTextField officeNum = new JTextField(8);
        officeNum.setName("Office No.");
        instructorFields.add(officeNum);
        add(officeNum, gbc);

        // Clear and submit buttons
        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 11; gbc.insets = new Insets(5, 0, 15, 0);
        add(new HorizontalRule(), gbc);

        gbc.gridx = 1; gbc.gridy = 12; gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.LINE_START;
        JButton clearBtnInstructor = new JButton("Clear");
        clearBtnInstructor.setFocusPainted(false);
        clearBtnInstructor.addActionListener(e -> clearFields(instructorFields));
        add(clearBtnInstructor, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        JButton submitBtnInstructor = new JButton("Add");
        submitBtnInstructor.setFocusPainted(false);
        submitBtnInstructor.addActionListener(e -> confirmAdd("ADD INSTRUCTOR", instructorFields, INSTRUCTORS));
        add(submitBtnInstructor, gbc);

        /*  *  *  *  *  *  *  *  *  *
         *       SECTION INFO       *
         *  *  *  *  *  *  *  *  *  */

        // Header
        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 13; gbc.insets = new Insets(15, 0, 0, 0);
        add(new HorizontalRule(), gbc);

        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 14;
        subtitle = new JLabel("Add Sections:");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        add(subtitle, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 15, 5, 15);

        // Section instructor, course and section labels
        gbc.gridy = 15;

        gbc.gridx = 0;
        add(new JLabel("Instructor N_Number"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Course No."), gbc);

        gbc.gridx = 2;
        add(new JLabel("Section No."), gbc);

        // Section instructor, course and section fields
        gbc.gridy = 16;

        gbc.gridx = 0;
        JTextField instructor = new JTextField(20);
        instructor.setName("Instructor N_Number");
        sectionFields.add(instructor);
        add(instructor, gbc);

        gbc.gridx = 1;
        JTextField course = new JTextField(20);
        course.setName("Course No,");
        sectionFields.add(course);
        add(course, gbc);

        gbc.gridx = 2;
        JTextField sectionNum = new JTextField(8);
        sectionNum.setName("Section No.");
        sectionFields.add(sectionNum);
        add(sectionNum, gbc);

        // Semester and year labels
        gbc.gridy = 17;

        gbc.gridx = 0;
        add(new JLabel("Semester"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Year"), gbc);

        // Semester and year fields
        gbc.gridy = 18;

        gbc.gridx = 0;
        JTextField semester = new JTextField(10);
        semester.setName("Semester");
        sectionFields.add(semester);
        add(semester, gbc);

        gbc.gridx = 1;
        JTextField year = new JTextField(5);
        year.setName("Year");
        sectionFields.add(year);
        add(year, gbc);

        /*  *  *  *  *  *  *
         *      FOOTER     *
         *  *  *  *  *  *  */

        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 19; gbc.insets = new Insets(5, 0, 15, 0);
        add(new HorizontalRule(), gbc);

        // Clear and submit buttons
        gbc.gridx = 1; gbc.gridy = 20; gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 0, 4, 0);

        gbc.anchor = GridBagConstraints.LINE_START;
        JButton clearBtnSection = new JButton("Clear");
        clearBtnSection.setFocusPainted(false);
        clearBtnSection.addActionListener(e -> clearFields(sectionFields));
        add(clearBtnSection, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        JButton submitBtnSection = new JButton("Add");
        submitBtnSection.setFocusPainted(false);
        submitBtnSection.addActionListener(e -> confirmAdd("ADD SECTION", sectionFields, SECTIONS));
        add(submitBtnSection, gbc);
    }
}
