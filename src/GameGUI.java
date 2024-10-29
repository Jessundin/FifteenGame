import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame implements ActionListener
{

    JPanel panel = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel(new GridLayout(4, 4));
    JButton[] buttons = new JButton[15];
    JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel westPanel = new JPanel(new FlowLayout());
    JButton newGameButton = new JButton("New Game");
    JButton exitButton = new JButton("Exit");
    JButton cheatButton = new JButton("Cheat");
    OtherButtons otherButtons = new OtherButtons();

    public GameGUI()
    {

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

        for (int i = 0; i < buttons.length; i++)
        {
            buttons[i] = new JButton(String.valueOf(i + 1));
            buttons[i].setFocusable(Boolean.FALSE);
            centerPanel.add(buttons[i], BorderLayout.CENTER);
        }
        centerPanel.add(new JButton());

        newGameButton.addActionListener(this);
        exitButton.addActionListener(this);
        cheatButton.addActionListener(this);

        pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == exitButton){
            otherButtons.exitButton();
        }
        if(e.getSource() == cheatButton){
            centerPanel.removeAll();
            otherButtons.solve(buttons,centerPanel);
            revalidate();
            repaint();
        }
        if(e.getSource() == newGameButton){
            centerPanel.removeAll();
            otherButtons.newGame(buttons);
            for (JButton button : otherButtons.newGame(buttons))
            {
                centerPanel.add(button);
            }
            revalidate();
            repaint();
        }
    }
}
