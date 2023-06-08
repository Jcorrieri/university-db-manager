package gui;

import javax.swing.*;
import java.awt.*;

public class HorizontalRule extends JSeparator {

    public HorizontalRule() {
        super();
        setPreferredSize(new Dimension(780, 1));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }

    public HorizontalRule(int width) {
        super();
        setPreferredSize(new Dimension(width, 1));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
    }
}
