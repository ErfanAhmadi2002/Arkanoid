package logicPanel;

import BlockPack.BlinkerBlock;
import BlockPack.Block;
import BlockPack.PrizeBlock;
import Models.Ball;
import Models.Paddle;
import PrizePack.Prize;
import SaveAndLoad.Saver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GamePlayPanel extends JPanel implements KeyListener , ActionListener {
    private ArrayList<Timer> allPrizeTimers;
    private HashMap<Integer , Integer> SecondsLeft;
    private PanelChanger panelChanger;
    private boolean IsStarted;
    private boolean IsGameOver;
    private Paddle paddle;
    private int Health;
    private BlockGenerator blockGenerator;
    private ArrayList<Prize> VisiblePrizes;
    private ArrayList<Ball> balls;
    private int Score = 0;
    private Timer timer;
    private final int delay = 5;
    private int loopCompleted;

    public GamePlayPanel(PanelChanger panelChanger) throws IOException {
        this.addKeyListener(this);
        this.allPrizeTimers = new ArrayList<>();
        this.SecondsLeft = new HashMap<>();
        this.panelChanger = panelChanger;
        this.blockGenerator = new BlockGenerator();
        this.setPreferredSize(new Dimension(1000 , 1000));
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.loopCompleted = 0;
        this.Health = 3;
        this.IsStarted = false;
        this.IsGameOver = false;
        this.VisiblePrizes = new ArrayList<>();
        this.balls = new ArrayList<>();
        this.balls.add(new Ball(490 , 830 , -6 , -8));
        this.paddle = new Paddle();
        timer = new Timer(delay, this);
        timer.start();
    }

    public GamePlayPanel(Paddle paddle, int health, BlockGenerator blockGenerator, ArrayList<Prize> visiblePrizes, ArrayList<Ball> balls, int score , HashMap<Integer , Integer> SecondsLeft) throws IOException {
        this.paddle = paddle;
        Health = health;
        this.SecondsLeft = SecondsLeft;
        this.allPrizeTimers = new ArrayList<>();
        this.blockGenerator = blockGenerator;
        this.VisiblePrizes = visiblePrizes;
        this.balls = balls;
        Score = score;
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(1000 , 1000));
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.loopCompleted = 0;
        this.IsStarted = false;
        this.IsGameOver = false;
        timer = new Timer(delay, this);
        timer.start();
    }

    public ArrayList<Timer> getAllPrizeTimers() {
        return allPrizeTimers;
    }

    public void setAllPrizeTimers(ArrayList<Timer> allPrizeTimers) {
        this.allPrizeTimers = allPrizeTimers;
    }

    public HashMap<Integer, Integer> getSecondsLeft() {
        return SecondsLeft;
    }

    public void setSecondsLeft(HashMap<Integer, Integer> secondsLeft) {
        SecondsLeft = secondsLeft;
    }

    public void setPanelChanger(PanelChanger panelChanger) {
        this.panelChanger = panelChanger;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Timer getTimer() {
        return timer;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    public BlockGenerator getBlockGenerator() {
        return blockGenerator;
    }

    public void setBlockGenerator(BlockGenerator blockGenerator) {
        this.blockGenerator = blockGenerator;
    }

    public ArrayList<Prize> getVisiblePrizes() {
        return VisiblePrizes;
    }

    public void setVisiblePrizes(ArrayList<Prize> visiblePrizes) {
        VisiblePrizes = visiblePrizes;
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void setBalls(ArrayList<Ball> balls) {
        this.balls = balls;
    }



    ///////////////////////////////////////////////////////////////////////
    @Override
    public void paint(Graphics g){
        if (!this.IsGameOver) {
            //game background :
            g.setColor(new Color(0x123456));
            g.fillRect(0, 0, 1000, 1000);

            //game paddle :
            paddle.drawPaddle((Graphics2D) g);

            //game ball :
            for (Ball ball : balls) {
                g.setColor(ball.getColor());
                g.fillOval(ball.getBall_x(), ball.getBall_y(), ball.getBall_r(), ball.getBall_r());
            }

            //player score :
            g.setColor(Color.white);
            g.setFont(new Font("serif", Font.PLAIN, 20));
            g.drawString("Score : " + Score, 880, 920);

            //game blocks :
            blockGenerator.draw((Graphics2D) g);

            //game Prizes :
            for (Prize prize : VisiblePrizes) {
                prize.drawPrize((Graphics2D) g);
            }

            g.dispose();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (!IsGameOver) {
            if (keyCode == KeyEvent.VK_LEFT) {
                this.IsStarted = true;
                if (paddle.isStunned()) {
                    if (paddle.getPaddleX() >= 1) {
                        paddle.MoveThePaddleLeft();
                    }
                } else {
                    if (paddle.getPaddleX() <= 1000 - paddle.getWidth()) {
                        paddle.MoveThePaddleRight();
                    }
                }
            }
            if (keyCode == KeyEvent.VK_RIGHT) {
                this.IsStarted = true;
                if (paddle.isStunned()) {
                    if (paddle.getPaddleX() <= 1000 - paddle.getWidth()) {
                        paddle.MoveThePaddleRight();
                    }
                } else {
                    if (paddle.getPaddleX() >= 1) {
                        paddle.MoveThePaddleLeft();
                    }
                }
            }
        }
        if (keyCode == KeyEvent.VK_ESCAPE){
            timer.stop();
            this.IsStarted = false;
            PauseTimers();
            panelChanger.PauseTheGame();
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        long ms = loopCompleted * delay;
        if (this.IsStarted){
            for (Ball ball:balls) {
                ball.setBall_x(ball.getBall_x() + ball.getBall_X_Speed());
                ball.setBall_y(ball.getBall_y() + ball.getBall_Y_Speed());
            }
            for (Prize prize:VisiblePrizes) {
                prize.movePrize();
            }
            try {
                chekTheIntersection();
                checkTimingFunctions(ms);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        loopCompleted++;
        repaint();
    }

    public void chekTheIntersection () throws IOException {
        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            //Bounds intersection:
            // 1-Left Border :
            if (ball.getBall_x() < 0) {
                ball.setBall_X_Speed(Math.abs(ball.getBall_X_Speed()));
            }
            // 2-Right Border :
            if (ball.getBall_x() > 1000 - ball.getBall_r()) {
                ball.setBall_X_Speed(-Math.abs(ball.getBall_X_Speed()));
            }
            // 3-Upper border :
            if (ball.getBall_y() < 0) {
                ball.setBall_Y_Speed(Math.abs(ball.getBall_Y_Speed()));
            }

            //paddle intersection:
            Rectangle ballREC = new Rectangle(ball.getBall_x(), ball.getBall_y(), ball.getBall_r(), ball.getBall_r());
            Rectangle paddleREC = new Rectangle(paddle.getPaddleX(), paddle.getPaddleY(), paddle.getWidth(), paddle.getHeight());
            if (ballREC.intersects(paddleREC)) {
                handlePaddleIntersection(ball , paddle);
            }

            //block intersection :
            for (Block block : blockGenerator.getBlocks()) {
                Rectangle blockREC = new Rectangle(block.getBlock_X(), block.getBlock_Y(), block.getWidth(), block.getHeight());
                if (ballREC.intersects(blockREC)) {
                    if (block.getClass().equals(PrizeBlock.class)) {
                        this.VisiblePrizes.add(((PrizeBlock) block).getPrize());
                    }
                    block.handleTheIntersection(blockGenerator.getBlocks(), ball);
                    this.Score += 5;
                    break;
                }
            }


            //Prize intersection :
            for (int j = 0; j < VisiblePrizes.size(); j++) {
                Prize prize = VisiblePrizes.get(j);
                Rectangle prizeREC = new Rectangle(prize.getPrize_X(), prize.getPrize_Y(), prize.getWidth(), prize.getHeight());
                if (prizeREC.intersects(paddleREC)) {
                    prize.applyPrize(this,15 );
                    VisiblePrizes.remove(prize);
                    j--;
                }
            }
        }

            //Lose Prize or ball :
        for (int i = 0; i < VisiblePrizes.size(); i++) {
            Prize prize = VisiblePrizes.get(i);
            if (prize.getPrize_Y() > 920){
                VisiblePrizes.remove(prize);
                i--;
            }
        }

        for (int i = 0; i < balls.size(); i++) {
            Ball ball = balls.get(i);
            if (ball.getBall_y() > 980){
                int Speed_X = ball.getBall_X_Speed();
                int Speed_Y = ball.getBall_Y_Speed();
                balls.remove(ball);
                i--;
                if (balls.size() == 0){
                    Health--;
                    if (Health == 0){
                        RunGameOver();
                    }
                    else {
                        this.IsStarted = false;
                        balls.add(new Ball(490 , 830 , Speed_X , Speed_Y));
                        this.paddle.setPaddleX(430);
                    }
                }
            }
        }
    }

    public void checkTimingFunctions (long ms) throws IOException {
        if (ms % 6000 == 0){
            blockGenerator.DoTheProcess();
        }


        if (ms % 1000 > 700 ){
            for (Block block: blockGenerator.Blocks) {
                if (block.getClass().equals(BlinkerBlock.class)){
                    ((BlinkerBlock) block).setInvisible(true);
                }
            }
        }
        else {
            for (Block block: blockGenerator.Blocks) {
                if (block.getClass().equals(BlinkerBlock.class)){
                    ((BlinkerBlock) block).setInvisible(false);
                }
            }
        }

        if (ms % 700 == 0){
            Iterator<Map.Entry<Integer, Integer>> iterator = SecondsLeft.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<Integer, Integer> prizeType =  iterator.next();
                if (prizeType.getValue() > 0 ) {
                    SecondsLeft.put(prizeType.getKey() , prizeType.getValue()-1);
                }
                else {
                    iterator.remove();
                }
            }
        }
    }

    public void handlePaddleIntersection (Ball ball , Paddle paddle){
        int center = ball.getBall_x() + ball.getBall_r();
        int rate = paddle.getWidth()/5;
        double coefficient = 0;
        if (Math.abs(ball.getBall_Y_Speed()) > 9){
            coefficient = 2;
        }
        if (Math.abs(ball.getBall_Y_Speed()) < 8){
            coefficient = 0.5;
        }
        if (Math.abs(ball.getBall_Y_Speed()) <= 9 && Math.abs(ball.getBall_Y_Speed()) >= 8){
            coefficient = 1;
        }
        if (center <= paddle.getPaddleX() + rate){
            ball.setBall_Y_Speed((int) (-8 * coefficient));
            ball.setBall_X_Speed((int) (-6 * coefficient));
        }
        if (center > paddle.getPaddleX() + rate && center <= paddle.getPaddleX() + (2*rate)){
            ball.setBall_Y_Speed((int) (-9 * coefficient));
            ball.setBall_X_Speed((int) (-5 * coefficient));
        }
        if (center > paddle.getPaddleX() + (2*rate) && center <= paddle.getPaddleX() + (3*rate)){
            ball.setBall_Y_Speed(-Math.abs(ball.getBall_Y_Speed()));
        }
        if (center > paddle.getPaddleX() + (3*rate) && center <= paddle.getPaddleX() + (4*rate)){
            ball.setBall_Y_Speed((int) (-9 * coefficient));
            ball.setBall_X_Speed((int) (5 * coefficient));
        }
        if (center > paddle.getPaddleX() + (4*rate)){
            ball.setBall_Y_Speed((int) (-8 * coefficient));
            ball.setBall_X_Speed((int) (6 * coefficient));
        }
    }

    public void RunGameOver () throws IOException {
        timer.stop();
        this.IsGameOver = true;
        String name = JOptionPane.showInputDialog("Enter Your Name Please : ");
        HashMap<String , Integer> AllScores = panelChanger.mainMenuPanel.getAllScores();
        if (AllScores.containsKey(name)){
            if (Score > AllScores.get(name)){
                AllScores.put(name , Score);
            }
        }
        else {
            AllScores.put(name , Score);
        }
        Saver.SaveLeaderBoards(AllScores);
        panelChanger.RestartTheGame();
    }

    public void ResumeTimers () throws IOException {
        for (Integer type:SecondsLeft.keySet()) {
            Prize prize = Prize.GeneratePrize(type);
            prize.applyPrize2(this , SecondsLeft.get(type));
        }
    }

    public void PauseTimers (){
        for (int i = 0; i < allPrizeTimers.size(); i++) {
            allPrizeTimers.get(i).stop();
            allPrizeTimers.remove(i);
            i--;
        }
    }

    public void LoadPrizes () throws IOException {
        for (Integer type:SecondsLeft.keySet()) {
            Prize prize = Prize.GeneratePrize(type);
            prize.applyPrize(this , SecondsLeft.get(type));
        }
    }

}
