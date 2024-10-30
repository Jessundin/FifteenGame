import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame implements ActionListener {

    JPanel panel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new GridLayout(4, 4));
    JButton[] buttons = new JButton[16];
    JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel westPanel = new JPanel(new FlowLayout());
    JButton newGameButton = new JButton("New Game");
    JButton exitButton = new JButton("Exit");
    JButton cheatButton = new JButton("Cheat");
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

        westPanel.add(cheatButton);
        eastPanel.add(newGameButton);
        eastPanel.add(exitButton);

        for (int i = 0; i < buttons.length; i++)
        {
            if (i == 15)
            {
                buttons[i] = new JButton("");
            } else
            {
                buttons[i] = new JButton(String.valueOf(i + 1));
            }
            buttons[i].setFocusable(Boolean.FALSE);
            centerPanel.add(buttons[i], BorderLayout.CENTER);
            buttons[i].addActionListener(this);
        }

        for (JButton button : otherButtons.newGame(buttons))
        {
            centerPanel.add(button);
        }

        newGameButton.addActionListener(this);
        exitButton.addActionListener(this);
        cheatButton.addActionListener(this);

        pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            otherButtons.exitButton();
        }
        if (e.getSource() == cheatButton) {
            centerPanel.removeAll();
            otherButtons.solve(buttons, centerPanel);
            revalidate();
            repaint();
        }
        if (e.getSource() == newGameButton) {
            centerPanel.removeAll();
            otherButtons.newGame(buttons);
            for (JButton button : otherButtons.newGame(buttons)) {
                centerPanel.add(button);
            }

            revalidate();
            repaint();
        } else {
            for (int i = 0; i < buttons.length; i++) {
                if (e.getSource() == buttons[i]) {
                    moveButton(i);
                    break;
                }
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
