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

        StringBuilder data = new StringBuilder();

        // Surround strings with double-quotes
        for (int i = 0; i < fields.size(); i++) {
            String value = fields.get(i).getText();

            boolean isString = false;
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) {
                isString = true;
                data.append('"');
            }

            data.append(value);
            if (isString)
                data.append('"');
            if (i != fields.size() - 1)
                data.append(",");
        }

        boolean valid = false;
        switch (type) {
            case STUDENTS_TO_DB -> valid = Main.addStudentToDatabase(data.toString());
            case INSTRUCTORS -> valid = Main.addInstructor(data.toString());
            case DEPARTMENTS -> valid = Main.addDepartment(data.toString());
            case COURSES -> valid = Main.addCourse(data.toString());
            case SECTIONS -> valid = Main.addSection(data.toString());
            case GRADE_REPORT -> valid = Main.addGrade(data.toString());
            case STUDENTS_TO_SECTION -> valid = Main.addStudentToSection(data.toString());
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
