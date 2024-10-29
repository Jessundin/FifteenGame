import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OtherButtons
{
    public void exitButton(){
        System.exit(0);
    }
    public void solve(JButton[] buttons,JButton emptyButton, JPanel centerPanel)
    {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(i + 1));
            buttons[i].setFocusable(Boolean.FALSE);
            emptyButton.setFocusable(Boolean.FALSE);
            centerPanel.add(buttons[i], BorderLayout.CENTER);
            centerPanel.add(emptyButton, BorderLayout.CENTER);
        }
    }
    public JButton[] newGame(JButton[] buttons, JButton emptyButton)
    {
        List<JButton> newButtonList = new ArrayList<>();

        for (JButton button : buttons) {
            newButtonList.add(button);
        }
        newButtonList.add(emptyButton);
        Collections.shuffle(newButtonList);
        return newButtonList.toArray(new JButton[newButtonList.size()]) ;
    }
}
