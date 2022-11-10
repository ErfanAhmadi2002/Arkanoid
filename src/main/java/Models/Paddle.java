package Models;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Paddle{
    private int PaddleX;
    private final int PaddleY;
    private final Color color = new Color(141 , 5 , 5);
    private int width;
    private int height;
    BufferedImage image;
    private boolean isStunned;

    public Paddle() throws IOException {
        this.PaddleX = 430;
        this.PaddleY = 850;
        this.width = 280;
        this.height = 25;
        this.isStunned = false;
        this.image = ImageIO.read(new File("./src/main/resources/Icons/PaddleIcon/50-Breakout-Tiles.png"));
    }

    public Paddle(int paddleX , int paddleY) throws IOException {
        this.PaddleX = paddleX;
        this.PaddleY = paddleY;
        this.width = 140;
        this.height = 25;
        this.isStunned = false;
        this.image = ImageIO.read(new File("./src/main/resources/Icons/PaddleIcon/50-Breakout-Tiles.png"));
    }


    public int getPaddleX() {
        return PaddleX;
    }

    public void setPaddleX(int paddleX) {
        PaddleX = paddleX;
    }

    public int getPaddleY() {
        return PaddleY;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isStunned() {
        return !isStunned;
    }

    public void setStunned(boolean stunned) {
        isStunned = stunned;
    }

    ///////////////////////////////////////////////////////

    public void drawPaddle (Graphics2D g){
        g.drawImage(this.image , PaddleX , PaddleY , width , height , null);
    }

    public void MoveThePaddleRight () {
        this.setPaddleX(this.getPaddleX() + 25);
    }

    public void MoveThePaddleLeft () {
        this.setPaddleX(this.getPaddleX() - 25);
    }
}
