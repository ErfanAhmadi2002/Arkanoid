package logicPanel;

import BlockPack.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class BlockGenerator {
    ArrayList<Block> Blocks;

    public BlockGenerator() throws IOException {
        this.Blocks = new ArrayList<>();
        DoTheProcess();
    }

    public BlockGenerator(ArrayList<Block> blocks) {
        Blocks = blocks;
    }

    public ArrayList<Block> getBlocks() {
        return Blocks;
    }

    public void DoTheProcess () throws IOException {
        moveToNextLine();
        generateNewBlockLine();
    }

    public void generateNewBlockLine () throws IOException {
        int start_X = 10;
        int start_Y = 50;
        Random random = new Random();
        while (start_X < 930) {
            int type = random.nextInt(5)+1;
            switch (type){
                case 1:
                    GlassBlock glassBlock = new GlassBlock(start_X , start_Y);
                    Blocks.add(glassBlock);
                    break;
                case 2:
                    WoodenBlock woodenBlock = new WoodenBlock(start_X , start_Y);
                    Blocks.add(woodenBlock);
                    break;
                case 3:
                    InvisibleBlock invisibleBlock = new InvisibleBlock(start_X ,start_Y);
                    Blocks.add(invisibleBlock);
                    break;
                case 4:
                    BlinkerBlock blinkerBlock = new BlinkerBlock(start_X , start_Y);
                    Blocks.add(blinkerBlock);
                    break;
                case 5:
                    PrizeBlock prizeBlock = new PrizeBlock(start_X , start_Y);
                    Blocks.add(prizeBlock);
                    break;
            }
            start_X += 80;
        }
    }

    public void moveToNextLine (){
        try {
            for (Block block : Blocks) {
                block.setBlock_Y(block.getBlock_Y() + 100);
                if (block.getClass().equals(PrizeBlock.class)){
                    ((PrizeBlock) block).getPrize().setPrize_Y(((PrizeBlock) block).getPrize().getPrize_Y()+ 100);
                }
            }
        }catch (Throwable throwable){}
    }

    public void draw(Graphics2D g){
        for (Block block:Blocks) {
            block.drawBlock(g);
        }
    }


}
