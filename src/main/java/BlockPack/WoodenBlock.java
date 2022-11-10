package BlockPack;


import Models.Ball;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class WoodenBlock extends Block{
    private int Health;
    public WoodenBlock(int block_x, int block_y) throws IOException {
        super(block_x, block_y);
        this.type = 2;
        this.Health = 2;
        this.image = ImageIO.read(new File("./src/main/resources/Icons/BlockIcon/19-Breakout-Tiles.png"));
    }


    @Override
    public void handleTheIntersection(ArrayList<Block> blocks, Ball ball) throws IOException {
        if (!ball.isFireball()) {
            if (this.Health == 2) {
                if ((ball.getBall_y() + ball.getBall_r()) >= this.getBlock_Y() || ball.getBall_y() <= (this.getBlock_Y() + this.getHeight()) ) {
                    ball.setBall_Y_Speed(-ball.getBall_Y_Speed());
                }
                this.image = ImageIO.read(new File("./src/main/resources/Icons/BlockIcon/20-Breakout-Tiles.png"));
                Health--;
            } else {
                if ((ball.getBall_y() + ball.getBall_r()) >= this.getBlock_Y() || ball.getBall_y() <= (this.getBlock_Y() + this.getHeight()) ) {
                    ball.setBall_Y_Speed(-ball.getBall_Y_Speed());
                }
                blocks.remove(this);
            }
        }
        else {
            blocks.remove(this);
        }
    }
}
