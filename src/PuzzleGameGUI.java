import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleGameGUI extends JFrame implements ActionListener {

    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel gridPanel = new JPanel(new GridLayout(4, 4));
    JButton[] puzzleButtons = new JButton[16];
    JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel westPanel = new JPanel(new FlowLayout());

    JButton startNewGameButton = new JButton("New Game");
    JButton exitGameButton = new JButton("Exit");
    JButton solvePuzzleButton = new JButton("Solve");

    OtherButtons otherButtons = new OtherButtons();
    int emptyButtonIndex = 15;

    public PuzzleGameGUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("15 Puzzle Game");
        this.setLocationRelativeTo(null);

        add(mainPanel);
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(eastPanel, BorderLayout.EAST);
        mainPanel.add(westPanel, BorderLayout.WEST);

        westPanel.add(solvePuzzleButton);
        eastPanel.add(startNewGameButton);
        eastPanel.add(exitGameButton);

        for (int i = 0; i < puzzleButtons.length; i++) {
            puzzleButtons[i] = new JButton(i == 15 ? "" : String.valueOf(i + 1));
            puzzleButtons[i].setFocusable(false);
            gridPanel.add(puzzleButtons[i]);
            puzzleButtons[i].addActionListener(this);
        }

        startNewGameButton.addActionListener(this);
        exitGameButton.addActionListener(this);
        solvePuzzleButton.addActionListener(this);

        shufflePuzzle();

        pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitGameButton) {
            otherButtons.exitButton();
        }
        if (e.getSource() == solvePuzzleButton) {
            solvePuzzle();
        }
        if (e.getSource() == startNewGameButton) {
            resetPuzzle();
        } else {
            for (int i = 0; i < puzzleButtons.length; i++) {
                if (e.getSource() == puzzleButtons[i]) {
                    moveButton(i);
                    break;
                }
            }
        }
    }

    private void resetPuzzle() {
        gridPanel.removeAll();
        shufflePuzzle();

        for (JButton button : puzzleButtons) {
            gridPanel.add(button);
        }

        revalidate();
        repaint();
    }

    private void shufflePuzzle() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }
        numbers.add(null);

        Collections.shuffle(numbers);

        for (int i = 0; i < puzzleButtons.length; i++) {
            if (numbers.get(i) == null) {
                puzzleButtons[i].setText("");
                emptyButtonIndex = i;
            } else {
                puzzleButtons[i].setText(String.valueOf(numbers.get(i)));
            }
        }
    }

    private boolean buttonCanMove(int index) {
        return (index == emptyButtonIndex - 1 && index % 4 != 3) ||
               (index == emptyButtonIndex + 1 && index % 4 != 0) ||
               (index == emptyButtonIndex - 4) ||
               (index == emptyButtonIndex + 4);
    }

    private void moveButton(int index) {
        if (buttonCanMove(index)) {
            String temp = puzzleButtons[index].getText();
            puzzleButtons[index].setText(puzzleButtons[emptyButtonIndex].getText());
            puzzleButtons[emptyButtonIndex].setText(temp);
            emptyButtonIndex = index;
            checkIfSolved();
        }
    }

    public void checkIfSolved()
    {
        int counter = 0;
        for (int i = 0; i < puzzleButtons.length; i++)
        {
            if (puzzleButtons[i].getText().equals(String.valueOf(i + 1)))
            {
                counter++;
                if (counter == 15)
                {
                    JOptionPane.showMessageDialog(null, "Grattis, du har vunnit!");
                    moveButton(i);
                }
            }
        }
    }

    private void solvePuzzle() {
        for (int i = 0; i < puzzleButtons.length; i++) {
            if (i < 15) {
                puzzleButtons[i].setText(String.valueOf(i + 1));
            } else {
                puzzleButtons[i].setText("");
            }
        }
        emptyButtonIndex = 15;
    }
}
