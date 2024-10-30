import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameGUI extends JFrame implements ActionListener {

    JPanel panel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new GridLayout(4, 4));
    JButton[] buttons = new JButton[16];
    JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel westPanel = new JPanel(new FlowLayout());
    JButton newGameButton = new JButton("New Game");
    JButton exitButton = new JButton("Exit");
    JButton solveButton = new JButton("Solve");
    OtherButtons otherButtons = new OtherButtons();
    int emptyIndex = 15;

    public GameGUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("15 Puzzle Game");
        this.setLocationRelativeTo(null);

        add(panel);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(eastPanel, BorderLayout.EAST);
        panel.add(westPanel, BorderLayout.WEST);

        westPanel.add(solveButton);
        eastPanel.add(newGameButton);
        eastPanel.add(exitButton);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(i == 15 ? "" : String.valueOf(i + 1));
            buttons[i].setFocusable(false);
            centerPanel.add(buttons[i]);
            buttons[i].addActionListener(this);
        }

        newGameButton.addActionListener(this);
        exitButton.addActionListener(this);
        solveButton.addActionListener(this);

        shuffleButtons();
        pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            otherButtons.exitButton();
        }
        if (e.getSource() == solveButton) {
            centerPanel.removeAll();
            otherButtons.solve(buttons, centerPanel);
            revalidate();
            repaint();
        }
        if (e.getSource() == newGameButton) {
            resetGame();
        } else {
            for (int i = 0; i < buttons.length; i++) {
                if (e.getSource() == buttons[i]) {
                    moveButton(i);
                    break;
                }
            }
        }
    }

    private void resetGame() {
        centerPanel.removeAll();
        shuffleButtons();
        for (JButton button : buttons) {
            centerPanel.add(button);
        }
        revalidate();
        repaint();
    }

    private void shuffleButtons() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }
        numbers.add(null);

        Collections.shuffle(numbers);

        for (int i = 0; i < buttons.length; i++) {
            if (numbers.get(i) == null) {
                buttons[i].setText("");
                emptyIndex = i;
            } else {
                buttons[i].setText(String.valueOf(numbers.get(i)));
            }
        }
    }

    private boolean buttonCanMove(int index) {
        return (index == emptyIndex - 1 && index % 4 != 3) ||
               (index == emptyIndex + 1 && index % 4 != 0) ||
               (index == emptyIndex - 4) || (index == emptyIndex + 4);
    }

    private void moveButton(int index) {
        if (buttonCanMove(index)) {
            String temp = buttons[index].getText();
            buttons[index].setText(buttons[emptyIndex].getText());
            buttons[emptyIndex].setText(temp);
            emptyIndex = index;
        }
    }
}
