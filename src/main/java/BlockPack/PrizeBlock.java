package BlockPack;

import PrizePack.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class PrizeBlock extends Block{
    private final Prize prize;
    public PrizeBlock(int block_x, int block_y) throws IOException {
        super(block_x, block_y);
        this.type = 5;
        this.image = ImageIO.read(new File("./src/main/resources/Icons/BlockIcon/PrizeImage.png"));
        this.prize = MakeRandomPrize(false);
    }

    public PrizeBlock(int block_x, int block_y, Prize prize) throws IOException {
        super(block_x, block_y);
        this.type = 5;
        this.image = ImageIO.read(new File("./src/main/resources/Icons/BlockIcon/PrizeImage.png"));
        this.prize = prize;
    }

    public Prize getPrize() {
        return prize;
    }

    private Prize MakeRandomPrize (boolean IsRandom) throws IOException {
        Random random = new Random();
        int type = random.nextInt(8) + 1;
        switch (type){
            case 1:
                return new BigPaddlePrize(block_X , block_Y , IsRandom);
            case 2:
                return new SmallPaddlePrize(block_X , block_Y , IsRandom);
            case 3:
                return new FastBallPrize(block_X , block_Y , IsRandom);
            case 4:
                return new SlowBallPrize(block_X , block_Y , IsRandom);
            case 5:
                return new StunPaddlePrize(block_X , block_Y , IsRandom);
            case 6:
                return new FireBallPrize(block_X , block_Y , IsRandom);
            case 7:
                return new TripleBallPrize(block_X , block_Y , IsRandom);
            case 8:
                return MakeRandomPrize(true);
        }
        return null;
    }




}
