package BlockPack;


import Models.Ball;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Block {
    protected int block_X;
    protected int block_Y;
    protected int type;
    protected final int width = 70;
    protected final int Height = 27;
    protected Color color;
    protected BufferedImage image;

    public Block(int block_x, int block_y) {
        block_X = block_x;
        block_Y = block_y;
        this.color = new Color(186, 181, 181, 133);
    }

    public int getBlock_X() {
        return block_X;
    }

    public void setBlock_X(int block_X) {
        this.block_X = block_X;
    }

    public int getBlock_Y() {
        return block_Y;
    }

    public void setBlock_Y(int block_Y) {
        this.block_Y = block_Y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return Height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    ///////////////////////////////////////////////////////////////

    public void  drawBlock (Graphics2D g){
        g.drawImage(this.image , this.block_X , this.block_Y , this.width , this.Height , null);
    }

    public void handleTheIntersection (ArrayList<Block> blocks , Ball ball)throws IOException {
        if (!ball.isFireball()) {
            if ((ball.getBall_y() + ball.getBall_r()) >= this.getBlock_Y() || ball.getBall_y() <= (this.getBlock_Y() + this.getHeight()) ) {
                ball.setBall_Y_Speed(-ball.getBall_Y_Speed());
            }
        }
        blocks.remove(this);
    }
}
