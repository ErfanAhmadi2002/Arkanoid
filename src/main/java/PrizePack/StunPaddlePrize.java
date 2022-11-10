package PrizePack;

import logicPanel.GamePlayPanel;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StunPaddlePrize extends Prize{
    public StunPaddlePrize(int prize_X, int prize_Y, boolean IsRandom) throws IOException {
        super(prize_X, prize_Y, IsRandom);
        if (IsRandom){
            this.type = 0;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/randomPrize.png"));
        }
        else {
            this.type = 5;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/48-Breakout-Tiles.png"));
        }
    }

    public StunPaddlePrize() {
        super();
    }

    @Override
    public void applyPrize(GamePlayPanel gamePlayPanel, int time) {
        gamePlayPanel.getPaddle().setStunned(true);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePlayPanel.getPaddle().setStunned(false);
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(time * 1000 , actionListener);
        timer.setRepeats(false);
        gamePlayPanel.getAllPrizeTimers().add(timer);
        gamePlayPanel.getSecondsLeft().put(5 , time);
        timer.start();
    }

    @Override
    public void applyPrize2(GamePlayPanel gamePlayPanel, int time) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePlayPanel.getPaddle().setStunned(false);
            }
        };
        javax.swing.Timer timer = new javax.swing.Timer(time * 1000 , actionListener);
        timer.setRepeats(false);
        gamePlayPanel.getAllPrizeTimers().add(timer);
        gamePlayPanel.getSecondsLeft().put(5 , time);
        timer.start();
    }

}
