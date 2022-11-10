package logicPanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends javax.swing.JFrame {

    public MyFrame() throws HeadlessException, IOException {
        super();
        this.setSize(new Dimension(1010, 1000));
        this.setResizable(false);
        this.setTitle("Arkanoid");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon("./src/main/resources/Icons/MainIcon/photo_2020-11-27_21-12-43.jpg");
        this.setIconImage(imageIcon.getImage());
        this.setVisible(true);
    }


}
