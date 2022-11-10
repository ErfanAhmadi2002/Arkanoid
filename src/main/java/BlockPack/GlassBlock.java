package BlockPack;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class GlassBlock extends Block{
    public GlassBlock(int block_x, int block_y) throws IOException {
        super(block_x, block_y);
        this.type = 1;
        this.image = ImageIO.read(new File("./src/main/resources/Icons/BlockIcon/13-Breakout-Tiles.png"));
    }


}
