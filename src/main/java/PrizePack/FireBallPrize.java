package PrizePack;

import logicPanel.GamePlayPanel;
import Models.Ball;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

public class FireBallPrize extends Prize{
    public FireBallPrize(int prize_X, int prize_Y, boolean IsRandom) throws IOException {
        super(prize_X, prize_Y, IsRandom);
        if (IsRandom){
            this.type = 0;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/randomPrize.png"));
        }
        else {
            this.type = 6;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/44-Breakout-Tiles.png"));
        }
    }
    public  FireBallPrize() {
        super();
    }

    @Override
    public void applyPrize(GamePlayPanel gamePlayPanel, int time) {
        for (Ball ball: gamePlayPanel.getBalls()) {
            ball.setFireball(true);
            ball.setColor(new Color(0xE74212));
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ball.setFireball(false);
                    ball.setColor(new Color(226, 241, 21));
                }
            };
            javax.swing.Timer timer = new javax.swing.Timer(time * 1000 , actionListener);
            timer.setRepeats(false);
            gamePlayPanel.getAllPrizeTimers().add(timer);
            gamePlayPanel.getSecondsLeft().put(6 , time);
            timer.start();
        }

    }

    @Override
    public void applyPrize2(GamePlayPanel gamePlayPanel, int time) {
        for (Ball ball: gamePlayPanel.getBalls()) {
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ball.setFireball(false);
                    ball.setColor(new Color(226, 241, 21));
                }
            };
            javax.swing.Timer timer = new javax.swing.Timer(time * 1000 , actionListener);
            timer.setRepeats(false);
            gamePlayPanel.getAllPrizeTimers().add(timer);
            gamePlayPanel.getSecondsLeft().put(6 , time);
            timer.start();
        }
    }


}
