package PrizePack;

import logicPanel.GamePlayPanel;
import Models.Ball;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SlowBallPrize extends Prize{
    public SlowBallPrize(int prize_X, int prize_Y, boolean IsRandom) throws IOException {
        super(prize_X, prize_Y, IsRandom);
        if (IsRandom){
            this.type = 0;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/randomPrize.png"));
        }
        else {
            this.type = 4;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/41-Breakout-Tiles.png"));
        }
    }

    public  SlowBallPrize() {
        super();
    }

    @Override
    public void applyPrize(GamePlayPanel gamePlayPanel, int time) {
        for (Ball ball: gamePlayPanel.getBalls()) {
            int PerX_Speed = ball.getBall_X_Speed();
            int PerY_Speed = ball.getBall_Y_Speed();
            ball.setBall_X_Speed(PerX_Speed / 2);
            ball.setBall_Y_Speed(PerY_Speed / 2);
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ball.setBall_Y_Speed(ball.getBall_Y_Speed() * 2);
                    ball.setBall_X_Speed(ball.getBall_X_Speed() * 2);
                }
            };
            javax.swing.Timer timer = new javax.swing.Timer(time * 1000 , actionListener);
            timer.setRepeats(false);
            gamePlayPanel.getAllPrizeTimers().add(timer);
            gamePlayPanel.getSecondsLeft().put(4 , time);
            timer.start();
        }

    }

    @Override
    public void applyPrize2(GamePlayPanel gamePlayPanel, int time) {
        for (Ball ball: gamePlayPanel.getBalls()) {
            if (Math.abs(ball.getBall_Y_Speed()) >= 8) {
                ActionListener actionListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ball.setBall_Y_Speed(ball.getBall_Y_Speed() * 2);
                        ball.setBall_X_Speed(ball.getBall_X_Speed() * 2);
                    }
                };
                javax.swing.Timer timer = new javax.swing.Timer(time * 1000, actionListener);
                timer.setRepeats(false);
                gamePlayPanel.getAllPrizeTimers().add(timer);
                gamePlayPanel.getSecondsLeft().put(4, time);
                timer.start();
            }
        }
    }

}
