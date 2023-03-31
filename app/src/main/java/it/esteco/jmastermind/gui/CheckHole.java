package it.esteco.jmastermind.gui;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class CheckHole {

    private static final Icon SMALL_HOLE_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/hole16.png"));
    private static final Icon BLACK_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/blackpeg16.png"));
    private static final Icon WHITE_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/whitepeg16.png"));

    private final JLabel hole;

    public CheckHole() {
        hole = new JLabel(SMALL_HOLE_ICON);
        hole.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    public JComponent getComponent() {
        return hole;
    }

    public void setBlack() {
        hole.setIcon(BLACK_PEG_ICON);
    }

    public void setWhite() {
        hole.setIcon(WHITE_PEG_ICON);
    }

}
