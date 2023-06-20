package gui.dialogs;

import api.Main;
import gui.CustomJFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static gui.CustomJPanel.*;

public class ViewDialog extends JDialog {

    private final GridBagConstraints gbc = new GridBagConstraints();
    public static final int DEPT_NAME = 8, DEPT_CODE = 9;
    private int deptInputType;
    private final JTextArea outputArea = new JTextArea(12, 45);

    public ViewDialog(CustomJFrame frame, int type) {
        super(frame, "View Dialog", true);
        setSize(595, 350);
        setResizable(false);
        setLocationRelativeTo(frame);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        gbc.insets = new Insets(0, 0, 15, 0);

        String[] text;
        switch (type) {
            case GRADE_REPORT -> text = new String[] {
                    "View Student Grade Report", "Student N_Number: ", "Generate Report"
            };
            case COURSES -> text = new String[] {
                    "View Courses by Department", "Department Name", "Department Code", "Find Courses"
            };
            case SECTIONS -> text = new String[] {
                    "View Sections by Instructor", "Instructor N_Number: ", "Find Sections"
            };
            default -> { return; }
        }

        initView(text, type);
        gbc.insets = new Insets(0, 0, 0, 0);

        gbc.gridy = 3; gbc.gridwidth = 2;
        outputArea.setEditable(false);

        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        this.add(outputScrollPane, gbc);

        setVisible(true);
    }

    private void initView(String[] text, int type) {
        setTitle(text[0]);

        // For courses by department
        JComboBox<String> inputComboBox = new JComboBox<>(new String[]{text[1], text[2]});
        inputComboBox.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        inputComboBox.setFocusable(false);

        // For others
        JComponent inputLabel = new JLabel(text[1]);
        inputLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        gbc.gridx = 0; gbc.gridy = 0;
        if (type == COURSES)
            add(inputComboBox, gbc);
        else
            add(inputLabel, gbc);

        gbc.gridx = 0; gbc.gridwidth = 2;
        JTextField inputField = new JTextField(15);
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        JButton submit = new JButton( text[text.length - 1] );
        submit.setFocusPainted(false);
        submit.addActionListener(e -> {
            if (type == COURSES) {
                deptInputType = Objects.equals(inputComboBox.getSelectedItem(), text[1]) ? DEPT_NAME : DEPT_CODE;
            }
            generateView(type, inputField.getText());
        });
        add(submit, gbc);
    }

    private void generateView(int type, String input) {
        // Very real very legit code
        String lineBreak = "-----------------------------------------------------------------------------------------";

        switch (type) {
            case GRADE_REPORT -> {
                String header1 = "FIRST_NAME, LAST_NAME, N_NUMBER";
                String header2 = "COURSE NO., SECTION NO., LETTER GRADE, GRADE POINT VALUE";
                outputArea.setText(header1 + "\n" + lineBreak + "\n");
                outputArea.append(Main.queryStudent(input) + "\n\n");
                outputArea.append(header2 + "\n" + lineBreak + "\n");

                String[] gradeQuery = Main.queryGrade(input); // IDK if this works tbh plz check

                outputArea.append(gradeQuery[0] + "\nTOTAL GPA: " + gradeQuery[1]);
            }
            case COURSES -> {
                String header = "NAME, DESCRIPTION, NUMBER, HOURS, LEVEL";
                outputArea.setText(header + "\n" + lineBreak + "\n");
                outputArea.append(Main.queryCourses(input, deptInputType));
            }
            case SECTIONS -> {
                String header = "COURSE, SECTION NO., SEMESTER, YEAR";
                outputArea.setText(header + "\n" + lineBreak + "\n");
                outputArea.append(Main.querySections(input));
            }
        }
    }
}
