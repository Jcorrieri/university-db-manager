package gui.dialogs;

import gui.CustomJFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static api.Main.logger;
import static gui.CustomJPanel.*;

public class EntityLog extends JDialog {

    private final GridBagConstraints gbc = new GridBagConstraints();

    public EntityLog(CustomJFrame frame, int type) {
        super(frame, "Entity Log", true);
        setSize(595, 350);
        setResizable(false);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        gbc.gridy = 1; gbc.gridwidth = 2;

        switch (type) {
            case STUDENTS_TO_DB -> initLog("Student Log", logger.getStudentLog());
            case INSTRUCTORS -> initLog("Instructor Log", logger.getInstructorLog());
            case DEPARTMENTS -> initLog("Department Log", logger.getDeptLog());
            case COURSES -> initLog("Course Log", logger.getCourseLog());
            case SECTIONS -> initLog("Section Log", logger.getSectionLog());
            case STUDENTS_TO_SECTION -> initLog("Section Student Log", logger.getSectStudentLog());
            case GRADE_REPORT -> initLog("Grade Log", logger.getGradeLog());
            default -> { return; }
        }

        setVisible(true);
    }

    private void initLog(String title, ArrayList<String> logData) {
        setTitle(title);
        StringBuilder output = new StringBuilder();

        for (String str : logData)
            output.append(str).append("\n");

        JTextArea outputArea = new JTextArea(output.toString(), 12, 40);
        outputArea.setFont( new Font(Font.SANS_SERIF, Font.PLAIN, 16) );
        outputArea.setEditable(false);

        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        add(outputScrollPane, gbc);
    }
}
