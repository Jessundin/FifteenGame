import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OtherButtons
{
    public void exitButton(){
        System.exit(0);
    }
    public void solve(JButton[] buttons, JPanel centerPanel)
    {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(String.valueOf(i + 1));
            buttons[i].setFocusable(Boolean.FALSE);
            centerPanel.add(buttons[i], BorderLayout.CENTER);
        }
    }
}
