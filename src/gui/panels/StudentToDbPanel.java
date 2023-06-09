package gui.panels;

import gui.CustomJPanel;
import gui.HorizontalRule;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentToDbPanel extends CustomJPanel {

    private final ArrayList<JTextField> textFields = new ArrayList<>();

    public StudentToDbPanel() {
        super();

        GridBagConstraints gbc = new GridBagConstraints();
        final int MAX_COLS = 3;

        // Header elements (title and gray divider)
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = MAX_COLS; // Center the title (title takes up entire row)
        JLabel addGeneral = new JLabel("Add Students to Database");
        addGeneral.setFont(new Font( Font.SANS_SERIF, Font.PLAIN, 30 ));
        add(addGeneral, gbc);

        gbc.insets = new Insets(15, 0, 0, 0);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new HorizontalRule(), gbc);
        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel subtitle = new JLabel("Personal Information:");
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
        textFields.add(firstName);
        add(firstName, gbc);

        gbc.gridx = 1;
        JTextField middleInitial = new JTextField(2);
        middleInitial.setName("Middle Initial");
        textFields.add(middleInitial);
        add(middleInitial, gbc);

        gbc.gridx = 2;
        JTextField lastName = new JTextField(20);
        lastName.setName("Last Name");
        textFields.add(lastName);
        add(lastName, gbc);

        // Current address labels
        gbc.gridy = 5;

        gbc.gridx = 0;
        add(new JLabel("Current Address"), gbc);

        // Current address fields
        gbc.gridy = 6;

        gbc.gridx = 0; gbc.gridwidth = 2;
        JTextField currentAddress = new JTextField(33);
        currentAddress.setName("Current Address");
        textFields.add(currentAddress);
        add(currentAddress, gbc);

        // Permanent address labels
        gbc.gridy = 7; gbc.gridwidth = 1;

        gbc.gridx = 0;
        add(new JLabel("Permanent City"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Permanent State"), gbc);

        gbc.gridx = 2;
        add(new JLabel("Permanent Zip Code"), gbc);

        // Permanent address fields
        gbc.gridy = 8;

        gbc.gridx = 0;
        JTextField permanentCity = new JTextField(20);
        permanentCity.setName("Permanent City");
        textFields.add(permanentCity);
        add(permanentCity, gbc);

        gbc.gridx = 1;
        JTextField permanentState = new JTextField(5);
        permanentState.setName("Permanent State");
        textFields.add(permanentState);
        add(permanentState, gbc);

        gbc.gridx = 2;
        JTextField permanentZip = new JTextField(8);
        permanentZip.setName("Permanent Zip Code");
        textFields.add(permanentZip);
        add(permanentZip, gbc);

        // Phone labels
        gbc.gridy = 9;

        gbc.gridx = 0;
        add(new JLabel("Current Phone"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Permanent Phone"), gbc);

        // Phone fields
        gbc.gridy = 10;

        gbc.gridx = 0;
        JTextField currentPhone = new JTextField(9);
        currentPhone.setName("Current Phone");
        textFields.add(currentPhone);
        add(currentPhone, gbc);

        gbc.gridx = 1;
        JTextField permanentPhone = new JTextField(9);
        permanentPhone.setName("Permanent Phone");
        textFields.add(permanentPhone);
        add(permanentPhone, gbc);

        // Birthdate, sex and ssn labels
        gbc.gridy = 11;

        gbc.gridx = 0;
        add(new JLabel("Birthdate"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Sex"), gbc);

        gbc.gridx = 2;
        add(new JLabel("SSN"), gbc);

        // Birthdate, sex and ssn fields
        gbc.gridy = 12;

        gbc.gridx = 0;
        JTextField birthdate = new JTextField(8);
        birthdate.setName("Birthdate");
        textFields.add(birthdate);
        add(birthdate, gbc);

        gbc.gridx = 1;
        JTextField sex = new JTextField(2);
        sex.setName("Sex");
        textFields.add(sex);
        add(sex, gbc);

        gbc.gridx = 2;
        JTextField ssn = new JTextField(8);
        ssn.setName("SSN");
        textFields.add(ssn);
        add(ssn, gbc);

        /*  *  *  *  *  *  *  *  *
         *      SCHOOL INFO      *
         *  *  *  *  *  *  *  *  */

        // Header
        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 13; gbc.insets = new Insets(5, 0, 0, 0);
        add(new HorizontalRule(), gbc);

        gbc.insets = new Insets(5, 0, 15, 0);

        gbc.gridx = 0; gbc.gridy = 14;
        subtitle = new JLabel("School Information:");
        subtitle.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 15));
        add(subtitle, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 15, 5, 15);

        // N_Number and class labels
        gbc.gridy = 15;

        gbc.gridx = 0;
        add(new JLabel("N_Number"), gbc);

        gbc.gridx = 1;
        add(new JLabel("Class"), gbc);

        // N_Number and class fields
        gbc.gridy = 16;

        gbc.gridx = 0;
        JTextField nNumber = new JTextField(8);
        nNumber.setName("N_Number");
        textFields.add(nNumber);
        add(nNumber, gbc);

        gbc.gridx = 1;
        JTextField studentClass = new JTextField(8);
        studentClass.setName("Class");
        textFields.add(studentClass);
        add(studentClass, gbc);

        // Degree info labels
        gbc.gridy = 17;

        gbc.gridx = 0;
        add(new JLabel("Major Dept."), gbc);

        gbc.gridx = 1;
        add(new JLabel("Minor Dept."), gbc);

        gbc.gridx = 2;
        add(new JLabel("Degree Program"), gbc);

        // Degree info fields
        gbc.gridy = 18;

        gbc.gridx = 0;
        JTextField major = new JTextField(20);
        major.setName("Major Dept."); // Test comment
        textFields.add(major);
        add(major, gbc);

        gbc.gridx = 1;
        JTextField minor = new JTextField(20);
        minor.setName("Minor Dept.");
        textFields.add(minor);
        add(minor, gbc);

        gbc.gridx = 2;
        JTextField program = new JTextField(20);
        program.setName("Degree Program");
        textFields.add(program);
        add(program, gbc);

        /*  *  *  *  *  *  *
         *      FOOTER     *
         *  *  *  *  *  *  */

        gbc.gridwidth = MAX_COLS; gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0; gbc.gridy = 19; gbc.insets = new Insets(5, 0, 15, 0);
        add(new HorizontalRule(), gbc);

        // Clear and submit buttons
        gbc.gridx = 1; gbc.gridy = 20; gbc.gridwidth = 1;

        gbc.insets = new Insets(0, 0, 30, 0); // Padding

        gbc.anchor = GridBagConstraints.LINE_START;
        JButton clearButton = new JButton("Clear");
        clearButton.setFocusPainted(false);
        clearButton.addActionListener(e -> clearFields(textFields));
        add(clearButton, gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        JButton submitButton = new JButton("Add");
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(e -> confirmAdd("ADD STUDENT", textFields, STUDENTS_TO_DB));
        add(submitButton, gbc);
    }
}
