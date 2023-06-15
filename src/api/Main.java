package api;

import gui.CustomJFrame;
import static gui.CustomJPanel.GRADE_REPORT;
import static gui.CustomJPanel.COURSES;
import static gui.CustomJPanel.SECTIONS;

public class Main {

    public static void main(String[] args) {
        DataHandler dataHandler = new DataHandler();

        CustomJFrame base = new CustomJFrame(dataHandler); // Set dataHandler for panels and dialogs
        base.setLocationRelativeTo(null);
        base.setVisible(true);
    }

    // 'type' can be GRADE_REPORT, COURSES, or SECTIONS
    public static String generateQuery(int type) {
        String query, result;
        result = "";

        return result;
    }
}