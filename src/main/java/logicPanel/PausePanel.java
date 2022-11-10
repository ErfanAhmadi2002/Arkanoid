package logicPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import Button.MyButton;
import SaveAndLoad.Load;
import SaveAndLoad.Saver;

public class PausePanel extends JPanel implements ActionListener {
    private PanelChanger panelChanger;
    private MyButton resume;
    private MyButton Save;
    private MyButton restart;

    public PausePanel(PanelChanger panelChanger) {
        super();
        this.panelChanger = panelChanger;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1000 , 1000));
        this.setBackground(new Color(0xC2B192));
        this.resume = new MyButton("Resume" , 400 , 100);
        this.Save = new MyButton("Save" , 400 , 200);
        this.restart = new MyButton("Restart" , 400 , 300);
        this.add(resume);
        this.add(restart);
        this.add(Save);
        resume.addActionListener(this);
        restart.addActionListener(this);
        Save.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(resume)){
            try {
                panelChanger.ResumeTheGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        if (e.getSource().equals(restart)){
            try {
                panelChanger.RestartTheGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if (e.getSource().equals(Save)){
            String name = JOptionPane.showInputDialog("Enter the name you want to save with : ");
            ArrayList<String> AllNames = Load.loadAllNames();
            if (AllNames.contains(name + ".txt")){
                int answer = JOptionPane.showConfirmDialog(null ,
                        "There's a Game saved with this Name Do you want to ReSave on it ?" ,
                        "Warn" ,
                        JOptionPane.YES_NO_OPTION);
                System.out.println(answer);
                if (answer == 0){
                    try {
                        Saver.SaveTheGamePanel(panelChanger.gamePlayPanel , name);
                        System.out.println("yes");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
            else {
                try {
                    Saver.SaveTheGamePanel(panelChanger.gamePlayPanel , name);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
