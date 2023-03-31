package it.esteco.jmastermind.gui;

import it.esteco.jmastermind.logic.PegColor;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EnumMap;
import java.util.Map;

public class CodeHole {

    private static final Icon RED_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/redpeg24.png"));
    private static final Icon BLUE_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/bluepeg24.png"));
    private static final Icon GREEN_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/greenpeg24.png"));
    private static final Icon YELLOW_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/yellowpeg24.png"));
    private static final Icon BLACK_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/blackpeg24.png"));
    private static final Icon WHITE_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/whitepeg24.png"));
    private static final Icon CODE_HOLE_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/hole24.png"));

    private static final Map<PegColor, Icon> PEG_ICONS = new EnumMap<>(PegColor.class);

    static {
        PEG_ICONS.put(PegColor.RED, RED_PEG_ICON);
        PEG_ICONS.put(PegColor.GREEN, GREEN_PEG_ICON);
        PEG_ICONS.put(PegColor.BLUE, BLUE_PEG_ICON);
        PEG_ICONS.put(PegColor.YELLOW, YELLOW_PEG_ICON);
        PEG_ICONS.put(PegColor.BLACK, BLACK_PEG_ICON);
        PEG_ICONS.put(PegColor.WHITE, WHITE_PEG_ICON);
    }

    private final JLabel hole;
    private PegColor pegColor;
    private HoleMouseListener holeMouseListener;

    public CodeHole() {
        this.hole = new JLabel(CODE_HOLE_ICON);
        hole.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
    }

    public CodeHole(PegColor pegColor) {
        this();
        setPegColor(pegColor);
    }

    private void setPegColor(PegColor pegColor) {
        this.pegColor = pegColor;
        hole.setIcon(PEG_ICONS.get(pegColor));
    }

    public void setActive(boolean active) {
        if (active) {
            holeMouseListener = new HoleMouseListener();
            hole.addMouseListener(holeMouseListener);
        } else {
            hole.removeMouseListener(holeMouseListener);
        }
    }

    public PegColor getPegColor() {
        return pegColor;
    }

    public JComponent getComponent() {
        return hole;
    }

    private class HoleMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JPopupMenu popupMenu = new JPopupMenu();

            setPegWhenActionPerformed(popupMenu, PegColor.RED);
            setPegWhenActionPerformed(popupMenu, PegColor.BLUE);
            setPegWhenActionPerformed(popupMenu, PegColor.GREEN);
            setPegWhenActionPerformed(popupMenu, PegColor.YELLOW);
            setPegWhenActionPerformed(popupMenu, PegColor.BLACK);
            setPegWhenActionPerformed(popupMenu, PegColor.WHITE);

            popupMenu.show((Component) e.getSource(), e.getX(), e.getY());
        }

        private void setPegWhenActionPerformed(JPopupMenu popupMenu, PegColor pegColor) {
            JMenuItem setPegColor = new JMenuItem(PEG_ICONS.get(pegColor));
            setPegColor.addActionListener(e -> setPegColor(pegColor));
            popupMenu.add(setPegColor);
        }

    }
}
