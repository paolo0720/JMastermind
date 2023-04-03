/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package it.esteco.jmastermind.gui;

import it.esteco.jmastermind.logic.Feedback;
import it.esteco.jmastermind.logic.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class JMastermind {

    private static final ImageIcon GRIPPER_ICON = new ImageIcon(ClassLoader.getSystemResource("it/esteco/jmastermind/gripper.png"));
    private final JFrame frame;
    private final Row[] rows = new Row[10];
    private final Pattern secret;
    private final ShieldableRow shieldableRow;
    private int activeRowIndex = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JMastermind().show());
    }

    public JMastermind() {
        secret = Pattern.createRandomPattern(new Random());

        frame = new JFrame("JMastermind");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
        contentPane.setBackground(new Color(164, 116, 73));

        JLabel title = new JLabel("JMastermind");
        title.setFont(title.getFont().deriveFont(Font.ITALIC).deriveFont(24.0f));
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(title, BorderLayout.NORTH);

        JPanel decodingBoard = new JPanel(new GridLayout(0, 1));
        decodingBoard.setOpaque(false);
        shieldableRow = new ShieldableRow(GRIPPER_ICON);
        decodingBoard.add(shieldableRow.getRow());
        for (int i = 0; i < rows.length; i++) {
            Row row = new Row();
            decodingBoard.add(row.getRow());
            rows[i] = row;
        }
        rows[activeRowIndex].setActive(true);

        contentPane.add(decodingBoard, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton check = new JButton("Check");
        check.addActionListener(e -> checkActiveRow());
        buttonPanel.add(check);
        buttonPanel.add(new JButton("Quit"));
        buttonPanel.add(new JButton("Help"));
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        frame.setContentPane(contentPane);
    }

    private void checkActiveRow() {
        if (guessIsCorrect()) {
            showSecretCode();
            showVictoryPopup();
        } else {
            activateNextRow();
        }
    }

    private boolean guessIsCorrect() {
        Feedback feedback = rows[activeRowIndex].check(secret);
        return feedback.correctGuess();
    }

    private void showVictoryPopup() {
        JOptionPane.showMessageDialog(frame, "You won!");
    }

    private void showSecretCode() {
        shieldableRow.show(secret);
    }

    private void activateNextRow() {
        rows[activeRowIndex].setActive(false);
        activeRowIndex++;
        rows[activeRowIndex].setActive(true);
    }

    private void show() {
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
