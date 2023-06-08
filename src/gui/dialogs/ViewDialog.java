package gui.dialogs;

import gui.CustomJFrame;

import javax.swing.*;
import java.awt.*;

import static gui.CustomJPanel.*;

public class ViewDialog extends JDialog {

    private final GridBagConstraints gbc = new GridBagConstraints();

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
        JTextArea outputArea = new JTextArea(12, 30);
        outputArea.setEditable(false);

        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        this.add(outputScrollPane, gbc);

        setVisible(true);
    }

    private void initView(String[] text, int type) {
        setTitle(text[0]);

        JComponent inputLabel;
        if (type == COURSES) {
            inputLabel = new JComboBox<>( new String[]{ text[1], text[2] } );
            inputLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
            inputLabel.setFocusable(false);
        } else {
            inputLabel = new JLabel(text[1]);
            inputLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        }

        gbc.gridx = 0; gbc.gridy = 0;
        add(inputLabel, gbc);

        gbc.gridx = 1;
        JTextField inputField = new JTextField(14);
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        add(inputField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton submit = new JButton( text[text.length - 1] );
        submit.setFocusPainted(false);
        add(submit, gbc);
    }
}
