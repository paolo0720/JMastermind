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
import java.awt.event.MouseListener;

public class Row {

    private final JPanel row;

    public Row(Icon smallHoleIcon, Icon largeHoleIcon) {
        row = new JPanel(new GridBagLayout());
        row.setOpaque(false);
        addLargeHole(row, 0, 0, largeHoleIcon);
        addLargeHole(row, 1, 0, largeHoleIcon);
        addLargeHole(row, 2, 0, largeHoleIcon);
        addLargeHole(row, 3, 0, largeHoleIcon);
        addSmallHole(row, 4, 0, smallHoleIcon);
        addSmallHole(row, 5, 0, smallHoleIcon);
        addSmallHole(row, 4, 1, smallHoleIcon);
        addSmallHole(row, 5, 1, smallHoleIcon);
        row.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    private void addSmallHole(JPanel row, int gridx, int gridy, Icon smallHoleIcon) {
        row.add(new JLabel(smallHoleIcon), new GridBagConstraints(gridx, gridy, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 8, 8));
    }

    private void addLargeHole(JPanel row, int gridx, int gridy, Icon largeHoleIcon) {
        JLabel label = new JLabel(largeHoleIcon);
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPopupMenu popupMenu = new JPopupMenu();
                Icon redPeg = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/redpeg36.png"));
                popupMenu.add(new JMenuItem(redPeg));
                popupMenu.show(label, e.getX(), e.getY());
            }
        });
        row.add(label, new GridBagConstraints(gridx, gridy, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 16, 16));
    }

    public JComponent getRow() {
        return row;
    }
}
