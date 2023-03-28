package it.esteco.jmastermind.gui;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ShieldableRow {

    private final JLabel row;

    public ShieldableRow(Icon gripper) {
        row = new JLabel(gripper);
        row.setVerticalAlignment(SwingConstants.TOP);
        row.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    public JComponent getRow() {
        return row;
    }
}
