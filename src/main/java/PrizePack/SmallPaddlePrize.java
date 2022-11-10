package PrizePack;

import logicPanel.GamePlayPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SmallPaddlePrize extends Prize{
    public SmallPaddlePrize(int prize_X, int prize_Y, boolean IsRandom) throws IOException {
        super(prize_X, prize_Y, IsRandom);
        if (IsRandom){
            this.type = 0;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/randomPrize.png"));
        }
        else {
            this.type = 2;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/46-Breakout-Tiles.png"));
        }
    }
    public  SmallPaddlePrize() {
        super();
    }

    @Override
    public void applyPrize(GamePlayPanel gamePlayPanel, int time) {
        if (gamePlayPanel.getPaddle().getWidth() >= 140) {
            int PerWidth = gamePlayPanel.getPaddle().getWidth();
            gamePlayPanel.getPaddle().setWidth(PerWidth / 2);
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gamePlayPanel.getPaddle().setWidth(gamePlayPanel.getPaddle().getWidth() * 2);
                }
            };
            Timer timer = new Timer(time * 1000, actionListener);
            timer.setRepeats(false);
            gamePlayPanel.getAllPrizeTimers().add(timer);
            gamePlayPanel.getSecondsLeft().put(2, time);
            timer.start();
        }
    }

    @Override
    public void applyPrize2(GamePlayPanel gamePlayPanel, int time) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePlayPanel.getPaddle().setWidth(gamePlayPanel.getPaddle().getWidth() * 2);
            }
        };
        Timer timer = new Timer(time * 1000 , actionListener);
        timer.setRepeats(false);
        gamePlayPanel.getAllPrizeTimers().add(timer);
        gamePlayPanel.getSecondsLeft().put(2 , time);
        timer.start();
    }

}
