import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuzzleGameManager {

    private final JButton[] puzzleButtons;
    private int emptyButtonIndex = 15;

    public PuzzleGameManager(JButton[] puzzleButtons) {
        this.puzzleButtons = puzzleButtons;
    }

    public void exitGame() {
        System.exit(0);
    }

    public void solvePuzzle() {
        for (int i = 0; i < puzzleButtons.length; i++) {
            if (i < 15) {
                puzzleButtons[i].setText(String.valueOf(i + 1));
            } else {
                puzzleButtons[i].setText("");
            }
        }
        emptyButtonIndex = 15;
    }

    public void shufflePuzzle() {
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

    public void moveButton(int index) {
        if (buttonCanMove(index)) {
            String temp = puzzleButtons[index].getText();
            puzzleButtons[index].setText(puzzleButtons[emptyButtonIndex].getText());
            puzzleButtons[emptyButtonIndex].setText(temp);
            emptyButtonIndex = index;
            checkIfSolved();
        }
    }

    public boolean buttonCanMove(int index) {
        return (index == emptyButtonIndex - 1 && index % 4 != 3) ||
               (index == emptyButtonIndex + 1 && index % 4 != 0) ||
               (index == emptyButtonIndex - 4) ||
               (index == emptyButtonIndex + 4);
    }

    public void checkIfSolved() {
        int counter = 0;
        for (int i = 0; i < puzzleButtons.length; i++) {
            if (puzzleButtons[i].getText().equals(String.valueOf(i + 1))) {
                counter++;
                if (counter == 15) {
                    JOptionPane.showMessageDialog(null, "Grattis, du har vunnit!");
                    moveButton(i);
                }
            }
        }
    }
}