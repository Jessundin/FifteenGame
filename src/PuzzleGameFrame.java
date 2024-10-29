import javax.swing.*;
import java.awt.*;

public class PuzzleGameFrame extends JFrame {

    JPanel borderPanel = new JPanel(new BorderLayout());
    JPanel gridPanel = new JPanel(new GridLayout(4, 4));
    JButton[] buttons = new JButton[15];
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JButton newGameButton = new JButton("New Game");
    JButton exitButton = new JButton("Exit");
    JButton solveButton = new JButton("Solve");

    public PuzzleGameFrame() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("15 Puzzle Game");
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        add(borderPanel);
        borderPanel.add(gridPanel, BorderLayout.CENTER);
        borderPanel.add(buttonPanel, BorderLayout.SOUTH);

        buttonPanel.add(solveButton);
        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(i+1));
            buttons[i].setFocusable(Boolean.FALSE);
            gridPanel.add(buttons[i]);
        }
        gridPanel.add(new JButton());

        gridPanel.setPreferredSize(new Dimension(350, 350));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        setSize(400, 500);

//        pack();

        this.setVisible(true);
    }
}