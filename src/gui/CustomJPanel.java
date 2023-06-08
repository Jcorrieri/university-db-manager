package gui;

import api.DataHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CustomJPanel extends JPanel {

    public static final int STUDENTS_TO_DB = 0, INSTRUCTORS = 1, DEPARTMENTS = 2, COURSES = 3, SECTIONS = 4;
    public static final int STUDENTS_TO_SECTION = 5, GRADE_REPORT = 6;
    protected DataHandler dataHandler;

    public CustomJPanel() {
        super();
        setLayout(new GridBagLayout()); // Using GridBag layout -- see Oracle tutorial
    }

    public void clearFields(ArrayList<JTextField> fields) {
        for (JTextField field : fields)
            field.setText("");
    }

    // Add form data to dataHandler and send to program for processing
    public void confirmAdd(String message, ArrayList<JTextField> fields, int type) {
        int option = JOptionPane.showConfirmDialog(this, message, "Confirm Action", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            StringBuilder data = new StringBuilder();

            for (int i = 0; i < fields.size() - 1; i++) {
                String value = fields.get(i).getText();
                if (value.contains(",")) { // NO COMMAS ALLOWED!!!! WE ARE USING CSV FORMAT!!!
                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid Character: ' , ' in " + fields.get(i).getName(),
                            "Entity Not Added",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                data.append(value).append(",");
            }
            data.append( fields.get(fields.size() - 1).getText() );

            boolean valid = false;
            switch (type) {
                case STUDENTS_TO_DB -> valid = dataHandler.addStudent(data.toString());
                case INSTRUCTORS -> valid = dataHandler.addInstructor(data.toString());
                case DEPARTMENTS -> valid = dataHandler.addDepartment(data.toString());
                case COURSES -> valid = dataHandler.addCourse(data.toString());
                case SECTIONS -> valid = dataHandler.addSection(data.toString());
                case GRADE_REPORT -> valid = dataHandler.addGrade(data.toString());
                case STUDENTS_TO_SECTION -> valid = dataHandler.addStudentToSection(data.toString());
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

    public void setDataHandler(DataHandler dataHandler) { this.dataHandler = dataHandler; }
}
