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

    private final JPanel component;

    public Row(Icon smallHoleIcon, Icon largeHoleIcon) {
        component = new JPanel(new GridBagLayout());
        component.setOpaque(false);
        addLargeHole(component, 0, largeHoleIcon);
        addLargeHole(component, 1, largeHoleIcon);
        addLargeHole(component, 2, largeHoleIcon);
        addLargeHole(component, 3, largeHoleIcon);
        addSmallHole(component, 4, 0, smallHoleIcon);
        addSmallHole(component, 5, 0, smallHoleIcon);
        addSmallHole(component, 4, 1, smallHoleIcon);
        addSmallHole(component, 5, 1, smallHoleIcon);
        component.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    private void addSmallHole(JPanel row, int gridx, int gridy, Icon smallHoleIcon) {
        row.add(new JLabel(smallHoleIcon), new GridBagConstraints(gridx, gridy, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 8, 8));
    }

    private void addLargeHole(JPanel row, int gridx, Icon largeHoleIcon) {
        JLabel label = new JLabel(largeHoleIcon);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu popupMenu = new JPopupMenu();

                ImageIcon redPegIcon = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/redpeg24.png"));
                addSetPegAction(popupMenu, redPegIcon, label);

                ImageIcon bluePegIcon = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/bluepeg24.png"));
                addSetPegAction(popupMenu, bluePegIcon, label);

                ImageIcon greenPegIcon = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/greenpeg24.png"));
                addSetPegAction(popupMenu, greenPegIcon, label);

                ImageIcon yellowPegIcon = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/yellowpeg24.png"));
                addSetPegAction(popupMenu, yellowPegIcon, label);

                ImageIcon blackPegIcon = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/blackpeg24.png"));
                addSetPegAction(popupMenu, blackPegIcon, label);

                ImageIcon whitePegIcon = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/whitepeg24.png"));
                addSetPegAction(popupMenu, whitePegIcon, label);

                popupMenu.show(label, e.getX(), e.getY());
            }
        });
        row.add(label, new GridBagConstraints(gridx, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 16, 16));
    }

    private static void addSetPegAction(JPopupMenu popupMenu, ImageIcon pegIcon, JLabel label) {
        JMenuItem setRedPeg = new JMenuItem(pegIcon);
        setRedPeg.addActionListener(e -> label.setIcon(pegIcon));
        popupMenu.add(setRedPeg);
    }

    public JComponent getRow() {
        return component;
    }
}
