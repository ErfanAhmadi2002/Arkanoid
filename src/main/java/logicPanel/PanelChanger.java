package logicPanel;

import java.io.IOException;

public class PanelChanger {
    MyFrame myFrame;
    GamePlayPanel gamePlayPanel;
    MainMenuPanel mainMenuPanel;
    PausePanel pausePanel;

    public PanelChanger() throws IOException {
        myFrame = new MyFrame();
        gamePlayPanel = new GamePlayPanel(this);
        mainMenuPanel = new MainMenuPanel(this);
        pausePanel = new PausePanel(this);
    }

    public void start(){
        myFrame.setContentPane(mainMenuPanel);
        myFrame.getContentPane().requestFocus();
        myFrame.repaint();
        myFrame.revalidate();
    }

    public void CreateNewGame(){
        myFrame.setContentPane(gamePlayPanel);
        myFrame.getContentPane().requestFocus();
        myFrame.repaint();
        myFrame.revalidate();
    }

    public void loadPreviousGame (GamePlayPanel gamePlayPanel1) throws IOException {
        this.gamePlayPanel = gamePlayPanel1;
        gamePlayPanel.LoadPrizes();
        this.gamePlayPanel.setPanelChanger(this);
        myFrame.setContentPane(gamePlayPanel);
        myFrame.getContentPane().requestFocus();
        myFrame.repaint();
        myFrame.revalidate();
    }

    public void PauseTheGame (){
        myFrame.setContentPane(pausePanel);
        myFrame.getContentPane().requestFocus();
        myFrame.repaint();
        myFrame.revalidate();
    }

    public void ResumeTheGame () throws IOException {
        gamePlayPanel.getTimer().start();
        gamePlayPanel.ResumeTimers();
        myFrame.setContentPane(gamePlayPanel);
        myFrame.getContentPane().requestFocus();
        myFrame.repaint();
        myFrame.revalidate();
    }

    public void RestartTheGame () throws IOException {
        this.gamePlayPanel = new GamePlayPanel(this);
        myFrame.setContentPane(gamePlayPanel);
        myFrame.getContentPane().requestFocus();
        myFrame.repaint();
        myFrame.revalidate();
    }
}
