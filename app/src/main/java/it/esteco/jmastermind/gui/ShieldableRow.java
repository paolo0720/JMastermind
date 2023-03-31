package it.esteco.jmastermind.gui;

import it.esteco.jmastermind.logic.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

public class ShieldableRow {

    private final JComponent row;
    private final JPanel secretPanel;
    private final CardLayout layout;

    public ShieldableRow(Icon gripper) {
        layout = new CardLayout();
        row = new JPanel(layout);
        row.setOpaque(false);

        JLabel shield = new JLabel(gripper);
        shield.setVerticalAlignment(SwingConstants.TOP);
        shield.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        row.add(shield, "shield");

        secretPanel = new JPanel(new GridLayout(1, 0));
        secretPanel.setOpaque(false);
        row.add(secretPanel, "secret");
    }

    public JComponent getRow() {
        return row;
    }

    public void show(Pattern secret) {
        secretPanel.add(new CodeHole(secret.peg1()).getComponent());
        secretPanel.add(new CodeHole(secret.peg2()).getComponent());
        secretPanel.add(new CodeHole(secret.peg3()).getComponent());
        secretPanel.add(new CodeHole(secret.peg4()).getComponent());
        layout.show(row, "secret");
    }

}
