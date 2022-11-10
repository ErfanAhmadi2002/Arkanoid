package Button;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    public MyButton(String name , int x , int y) {
        this.setText(name);
        this.setBounds(x , y , 200 , 80);
        this.setFocusable(false);
        this.setFont(new Font("Comic Sans" , Font.BOLD , 25));
        this.setBackground(new Color(0xD47211));
        this.setForeground(Color.WHITE);
    }
}
