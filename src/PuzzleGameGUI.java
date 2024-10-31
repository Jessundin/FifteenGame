import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleGameGUI extends JFrame implements ActionListener {

    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel gridPanel = new JPanel(new GridLayout(4, 4));
    JButton[] puzzleButtons = new JButton[16];
    JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel westPanel = new JPanel(new FlowLayout());

    JButton startNewGameButton = new JButton("New Game");
    JButton exitGameButton = new JButton("Exit");
    JButton solvePuzzleButton = new JButton("Solve");

    PuzzleGameManager puzzleManager;

    public PuzzleGameGUI() {
        puzzleManager = new PuzzleGameManager(puzzleButtons);
        setupUI();
        resetPuzzle();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("15 Puzzle Game");
        setLocationRelativeTo(null);

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
            puzzleButtons[i].addActionListener(this);
            gridPanel.add(puzzleButtons[i]);
        }

        startNewGameButton.addActionListener(this);
        exitGameButton.addActionListener(this);
        solvePuzzleButton.addActionListener(this);

        pack();
        this.setVisible(true);
    }

    private void resetPuzzle() {
        puzzleManager.shufflePuzzle();
        for (JButton button : puzzleButtons) {
            gridPanel.add(button);
        }
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitGameButton) {
            puzzleManager.exitGame();
        }
        if (e.getSource() == solvePuzzleButton) {
            puzzleManager.solvePuzzle();
        }
        if (e.getSource() == startNewGameButton) {
            resetPuzzle();
        } else {
            for (int i = 0; i < puzzleButtons.length; i++) {
                if (e.getSource() == puzzleButtons[i]) {
                    puzzleManager.moveButton(i);
                    break;
                }
            }
        }
    }
}