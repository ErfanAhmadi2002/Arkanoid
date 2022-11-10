package PrizePack;

import logicPanel.GamePlayPanel;
import Models.Ball;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TripleBallPrize extends Prize{
    public TripleBallPrize(int prize_X, int prize_Y, boolean IsRandom) throws IOException {
        super(prize_X, prize_Y, IsRandom);
        if (IsRandom){
            this.type = 0;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/randomPrize.png"));
        }
        else {
            this.type = 7;
            this.image = ImageIO.read(new File("./src/main/resources/Icons/PrizeIcons/43-Breakout-Tiles.png"));
        }
    }

    @Override
    public void applyPrize(GamePlayPanel gamePlayPanel, int time) {
       Ball main = gamePlayPanel.getBalls().get(0);
       gamePlayPanel.getBalls().add(new Ball(main.getBall_x(), main.getBall_y(), main.getBall_X_Speed(), -main.getBall_Y_Speed()));
       gamePlayPanel.getBalls().add(new Ball(main.getBall_x(), main.getBall_y(), -main.getBall_X_Speed(), main.getBall_Y_Speed()));
    }

    @Override
    public void applyPrize2(GamePlayPanel gamePlayPanel, int time) {

    }

}
