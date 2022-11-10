package PrizePack;


import logicPanel.GamePlayPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Prize {
    protected BufferedImage image;
    protected int type;
    protected int Prize_X;
    protected int Prize_Y;
    protected final int width = 60;
    protected final int height = 25;
    private final int Prize_YSpeed = 3;

    public Prize(int prize_X, int prize_Y , boolean IsRandom) {
        Prize_X = prize_X;
        Prize_Y = prize_Y;
    }

    public  Prize() {
    }

    public int getType() {
        return type;
    }

    public int getPrize_X() {
        return Prize_X;
    }

    public int getPrize_Y() {
        return Prize_Y;
    }

    public void setPrize_Y(int prize_Y) {
        Prize_Y = prize_Y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    //////////////////////////////////////////////////////////////////

    public abstract void applyPrize(GamePlayPanel gamePlayPanel , int time);

    public abstract void applyPrize2(GamePlayPanel gamePlayPanel , int time);

    public final void drawPrize(Graphics2D g){
        g.drawImage(this.image , this.Prize_X , this.Prize_Y , this.width , this.height , null);
    }

    public final void movePrize (){
        Prize_Y += Prize_YSpeed;
    }

    public static Prize GeneratePrize (int Id) throws IOException {
        switch (Id){
            case 1:return new BigPaddlePrize();
            case 2:return new SmallPaddlePrize();
            case 3:return new FastBallPrize();
            case 4:return new SlowBallPrize();
            case 5:return new StunPaddlePrize();
            case 6:return new FireBallPrize();
            default:
                return null;
        }
    }

}
