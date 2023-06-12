package api;

import gui.CustomJFrame;

public class Main {

    public static void main(String[] args) {

        DataHandler dataHandler = new DataHandler();

        CustomJFrame base = new CustomJFrame(dataHandler); // Set dataHandler for panels and dialogs
        base.setLocationRelativeTo(null);
        base.setVisible(true);

        String plarb = "plarb";
    }
}