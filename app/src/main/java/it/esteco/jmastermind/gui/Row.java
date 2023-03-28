package it.esteco.jmastermind.gui;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Row {

    private static final Icon LARGE_HOLE_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/hole24.png"));
    private static final Icon SMALL_HOLE_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/hole16.png"));
    private static final Icon RED_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/redpeg24.png"));
    private static final Icon BLUE_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/bluepeg24.png"));
    private static final Icon GREEN_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/greenpeg24.png"));
    private static final Icon YELLOW_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/yellowpeg24.png"));
    private static final Icon BLACK_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/blackpeg24.png"));
    private static final Icon WHITE_PEG_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/whitepeg24.png"));

    private final JPanel component;

    public Row() {
        component = new JPanel(new GridBagLayout());
        component.setOpaque(false);
        addLargeHole(component, 0);
        addLargeHole(component, 1);
        addLargeHole(component, 2);
        addLargeHole(component, 3);
        addSmallHole(component, 4, 0);
        addSmallHole(component, 5, 0);
        addSmallHole(component, 4, 1);
        addSmallHole(component, 5, 1);
        component.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    private void addSmallHole(JPanel row, int gridx, int gridy) {
        row.add(new JLabel(SMALL_HOLE_ICON), new GridBagConstraints(gridx, gridy, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 8, 8));
    }

    private void addLargeHole(JPanel row, int gridx) {
        JLabel label = new JLabel(LARGE_HOLE_ICON);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu popupMenu = new JPopupMenu();
                setPegWhenActionPerformed(popupMenu, RED_PEG_ICON, label);
                setPegWhenActionPerformed(popupMenu, BLUE_PEG_ICON, label);
                setPegWhenActionPerformed(popupMenu, GREEN_PEG_ICON, label);
                setPegWhenActionPerformed(popupMenu, YELLOW_PEG_ICON, label);
                setPegWhenActionPerformed(popupMenu, BLACK_PEG_ICON, label);
                setPegWhenActionPerformed(popupMenu, WHITE_PEG_ICON, label);
                popupMenu.show(label, e.getX(), e.getY());
            }
        });
        row.add(label, new GridBagConstraints(gridx, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 16, 16));
    }

    private static void setPegWhenActionPerformed(JPopupMenu popupMenu, Icon pegIcon, JLabel label) {
        JMenuItem setRedPeg = new JMenuItem(pegIcon);
        setRedPeg.addActionListener(e -> label.setIcon(pegIcon));
        popupMenu.add(setRedPeg);
    }

    public JComponent getRow() {
        return component;
    }
}
