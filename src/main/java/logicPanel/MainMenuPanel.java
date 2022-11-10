package logicPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Button.MyButton;

public class MainMenuPanel extends JPanel implements ActionListener {
    private PanelChanger panelChanger;
    private MyButton Load;
    private MyButton NewGame;
    private MyButton LeaderBoard;
    private HashMap<String , Integer> AllScores;

    public MainMenuPanel(PanelChanger panelChanger) throws IOException {
        super();
        this.AllScores = SaveAndLoad.Load.loadAllScores();
        this.panelChanger = panelChanger;
        this.setLayout(null);
        this.setSize(new Dimension(1000 , 1000));
        this.setBackground(new Color(0xC2B192));
        this.NewGame = new MyButton("NewGame" , 400 , 400);
        this.Load = new MyButton("Load" , 400 , 550);
        this.LeaderBoard = new MyButton("LeaderBoard" , 400 , 700);
        this.add(NewGame);
        this.add(Load);
        this.add(LeaderBoard);
        NewGame.addActionListener(this);
        Load.addActionListener(this);
        LeaderBoard.addActionListener(this);
    }

    public HashMap<String, Integer> getAllScores() {
        return AllScores;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(NewGame)){
            panelChanger.CreateNewGame();
        }
        if (e.getSource().equals(Load)){
            boolean b = true;
            String name = JOptionPane.showInputDialog("Enter the name you want to continue with : ");
            ArrayList<String> AllNames = SaveAndLoad.Load.loadAllNames();
            if (AllNames.contains(name + ".txt")){
                try {
                    GamePlayPanel SavedGamePlayPanel = SaveAndLoad.Load.loadTheGamePanel(name);
                    panelChanger.loadPreviousGame(SavedGamePlayPanel);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            else {
                JOptionPane.showConfirmDialog(null ,
                        "There is nothing saved with this name." ,
                        "warn" ,
                        JOptionPane.DEFAULT_OPTION ,
                        JOptionPane.ERROR_MESSAGE);
            }
        }

        if (e.getSource().equals(LeaderBoard)){
            JOptionPane.showMessageDialog(null , leaderBoardToString() , "Leaders" , JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public String leaderBoardToString (){
        StringBuilder LeaderBoards = new StringBuilder();
        int Number = 1;
        for (String name:AllScores.keySet()) {
            LeaderBoards.append(Number + " " + name + " " + AllScores.get(name) + "\n");
            Number++;
        }
        return LeaderBoards.toString();
    }
}
