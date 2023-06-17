package gui;

import api.Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CustomJPanel extends JPanel {

    public static final int STUDENTS_TO_DB = 0, INSTRUCTORS = 1, DEPARTMENTS = 2, COURSES = 3, SECTIONS = 4;
    public static final int STUDENTS_TO_SECTION = 5, GRADE_REPORT = 6, GRD_STD_INFO = 7;

    public CustomJPanel() {
        super();
        setLayout(new GridBagLayout());
    }

    public void clearFields(ArrayList<JTextField> fields) {
        for (JTextField field : fields)
            field.setText("");
    }

    // Add form data to Main (comma-separated format)
    public void confirmAdd(String message, ArrayList<JTextField> fields, int type) {
        int option = JOptionPane.showConfirmDialog(this, message, "Confirm Action", JOptionPane.YES_NO_OPTION);

        if (option != JOptionPane.OK_OPTION)
            return;

        ArrayList<String> data = new ArrayList<>();

        // Add data
        for (JTextField field : fields) data.add(field.getText());

        boolean valid = false;
        switch (type) {
            case STUDENTS_TO_DB -> valid = Main.addStudentToDatabase(data);
            case INSTRUCTORS -> valid = Main.addInstructor(data);
            case DEPARTMENTS -> valid = Main.addDepartment(data);
            case COURSES -> valid = Main.addCourse(data);
            case SECTIONS -> valid = Main.addSection(data);
            case GRADE_REPORT -> valid = Main.addGrade(data);
            case STUDENTS_TO_SECTION -> valid = Main.addStudentToSection(data);
            default -> System.out.println(data);
        }

        if (valid) {
            clearFields(fields);
            JOptionPane.showMessageDialog(this, "Entity added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Entry", "Entity not added", JOptionPane.ERROR_MESSAGE);
        }
    }
}
