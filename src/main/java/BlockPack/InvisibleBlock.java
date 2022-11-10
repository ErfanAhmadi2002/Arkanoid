package BlockPack;



import java.awt.*;

public class InvisibleBlock extends Block{
    private final boolean IsInvisible;
    public InvisibleBlock(int block_x, int block_y) {
        super(block_x, block_y);
        this.type = 3;
        this.IsInvisible = false;
    }

    @Override
    public void drawBlock(Graphics2D g) {
    }

}
