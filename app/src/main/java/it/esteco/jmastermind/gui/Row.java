package it.esteco.jmastermind.gui;

import it.esteco.jmastermind.logic.Feedback;
import it.esteco.jmastermind.logic.Pattern;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

public class Row {

    private static final Icon ACTIVE_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/rightarrow24.png"));
    private static final Icon NOT_ACTIVE_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/empty24.png"));

    private final JPanel component;
    private final CodeHole[] codeHoles = new CodeHole[4];
    private final CheckHole[] checkHoles = new CheckHole[4];
    private final JLabel activeLabel;

    public Row() {
        component = new JPanel(new GridBagLayout());
        component.setOpaque(false);
        activeLabel = new JLabel(NOT_ACTIVE_ICON);
        component.add(activeLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 4, 0));
        component.add(createCodePanel(), new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        component.add(createCheckPanel(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        component.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
    }

    private JComponent createCodePanel() {
        JPanel codePanel = new JPanel(new GridLayout(1, 0));
        codePanel.setOpaque(false);
        for (int i = 0; i < 4; i++) {
            CodeHole codeHole = new CodeHole();
            codeHoles[i] = codeHole;
            codePanel.add(codeHole.getComponent());
        }
        return codePanel;
    }

    private JComponent createCheckPanel() {
        JPanel checkPanel = new JPanel(new GridLayout(2, 2));
        checkPanel.setOpaque(false);
        for (int i = 0; i < 4; i++) {
            CheckHole checkHole = new CheckHole();
            checkHoles[i] = checkHole;
            checkPanel.add(checkHole.getComponent());
        }
        return checkPanel;
    }

    public void setActive(boolean active) {
        for (CodeHole codeHole : codeHoles) {
            codeHole.setActive(active);
        }
        activeLabel.setIcon(active ? ACTIVE_ICON : NOT_ACTIVE_ICON);
    }

    public JComponent getRow() {
        return component;
    }

    private void setFeedback(Feedback feedback) {
        int checkIndex = 0;
        for (int i = 0; i < feedback.correct(); i++) {
            checkHoles[checkIndex].setBlack();
            checkIndex++;
        }
        for (int i = 0; i < feedback.wrong(); i++) {
            checkHoles[checkIndex].setWhite();
            checkIndex++;
        }
    }

    public Feedback check(Pattern secret) {
        Pattern pattern = new Pattern(codeHoles[0].getPegColor(), codeHoles[1].getPegColor(), codeHoles[2].getPegColor(), codeHoles[3].getPegColor());
        Feedback feedback = secret.match(pattern);
        setFeedback(feedback);
        return feedback;
    }
}
