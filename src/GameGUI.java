import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {

    JPanel panel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new GridLayout(4, 4));
    JButton[] buttons = new JButton[15];
    JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel westPanel = new JPanel(new FlowLayout());
    JButton newGameButton = new JButton("New Game");
    JButton exitButton = new JButton("Exit");
    JButton cheatButton = new JButton("Cheat");

    public GameGUI() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("15 Puzzle Game");
//        this.setResizable(false);
        this.setLocationRelativeTo(null);

        add(panel);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(eastPanel, BorderLayout.EAST);
        panel.add(westPanel, BorderLayout.WEST);

        westPanel.add(cheatButton);
        eastPanel.add(newGameButton);
        eastPanel.add(exitButton);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(i + 1));
            buttons[i].setFocusable(Boolean.FALSE);
            centerPanel.add(buttons[i], BorderLayout.CENTER);
        }
        centerPanel.add(new JButton());

        pack();
        this.setVisible(true);
    }
}
