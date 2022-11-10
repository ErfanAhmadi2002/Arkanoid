package PrizePack;

import logicPanel.GamePlayPanel;
import Models.Ball;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class FastBallPrize extends Prize{
    public FastBallPrize(int prize_X, int prize_Y, boolean IsRandom) throws IOException {
        super(prize_X, prize_Y, IsRandom);
        if (IsRandom){
            this.type = 0;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/randomPrize.png"));
        }
        else {
            this.type = 3;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/42-Breakout-Tiles.png"));
        }
    }

    public  FastBallPrize() {
        super();
    }

    @Override
    public void applyPrize(GamePlayPanel gamePlayPanel, int time) {
        for (Ball ball: gamePlayPanel.getBalls()) {
            int PerX_Speed = ball.getBall_X_Speed();
            int PerY_Speed = ball.getBall_Y_Speed();
            ball.setBall_X_Speed(PerX_Speed * 2);
            ball.setBall_Y_Speed(PerY_Speed * 2);
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ball.setBall_Y_Speed(ball.getBall_Y_Speed() / 2);
                    ball.setBall_X_Speed(ball.getBall_X_Speed() / 2);
                }
            };
            javax.swing.Timer timer = new javax.swing.Timer(time * 1000 , actionListener);
            timer.setRepeats(false);
            gamePlayPanel.getAllPrizeTimers().add(timer);
            gamePlayPanel.getSecondsLeft().put(3 , time);
            timer.start();
        }
    }

    @Override
    public void applyPrize2(GamePlayPanel gamePlayPanel, int time) {
        for (Ball ball: gamePlayPanel.getBalls()) {
            if (Math.abs(ball.getBall_Y_Speed()) <= 9 ) {
                ActionListener actionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ball.setBall_Y_Speed(ball.getBall_Y_Speed() / 2);
                        ball.setBall_X_Speed(ball.getBall_X_Speed() / 2);
                    }
                };
                javax.swing.Timer timer = new javax.swing.Timer(time * 1000, actionListener);
                timer.setRepeats(false);
                gamePlayPanel.getAllPrizeTimers().add(timer);
                gamePlayPanel.getSecondsLeft().put(3, time);
                timer.start();
            }
        }
    }


}
