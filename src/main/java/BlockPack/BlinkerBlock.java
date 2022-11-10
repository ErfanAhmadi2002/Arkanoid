package BlockPack;

import Models.Ball;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BlinkerBlock extends Block {
    private boolean IsInvisible;

    public BlinkerBlock(int block_x, int block_y) throws IOException {
        super(block_x, block_y);
        this.type = 4;
        this.IsInvisible = false;
        this.image = ImageIO.read(new File("./src/main/resources/Icons/BlockIcon/03-Breakout-Tiles.png"));
    }

    public boolean isInvisible() {
        return IsInvisible;
    }

    public void setInvisible(boolean invisible) {
        IsInvisible = invisible;
    }

    @Override
    public void drawBlock(Graphics2D g) {
        if (this.IsInvisible){
            g.drawImage(this.image , this.block_X , this.block_Y , this.width , this.Height , null);
        }
        else {}
    }

    @Override
    public void handleTheIntersection(ArrayList<Block> blocks, Ball ball) throws IOException {
        if (this.IsInvisible) {
            super.handleTheIntersection(blocks, ball);
        }
    }
}
