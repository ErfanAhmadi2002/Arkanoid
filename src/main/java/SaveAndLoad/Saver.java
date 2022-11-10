package SaveAndLoad;

import BlockPack.Block;
import BlockPack.PrizeBlock;
import Models.Ball;
import Models.Paddle;
import PrizePack.Prize;
import logicPanel.GamePlayPanel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;

public abstract class Saver {

    public static void SaveTheGamePanel (GamePlayPanel gamePlayPanel , String name) throws IOException {
        File file = new File("./src/main/resources/PlayersGamePanel" + "\\" + name + ".txt");
        if (!file.exists()){
            file.createNewFile();
        }
        PrintStream printStream = new PrintStream(new FileOutputStream(file) , false);
        printStream.println("Padel:");
        Paddle paddle = gamePlayPanel.getPaddle();
        printStream.println(paddle.getPaddleX() + " " + paddle.getPaddleY());
        printStream.println("balls: " + gamePlayPanel.getBalls().size());
        for (Ball ball:gamePlayPanel.getBalls()) {
            printStream.println(ball.getBall_x() + " " + ball.getBall_y());
        }
        printStream.println("Blocks: " + gamePlayPanel.getBlockGenerator().getBlocks().size());
        for (Block block:gamePlayPanel.getBlockGenerator().getBlocks()) {
            if (block.getType() != 5) {
                printStream.println(block.getType() + " " + block.getBlock_X() + " " + block.getBlock_Y());
            }
            else {
                Prize prize = ((PrizeBlock)block).getPrize();
                printStream.println(block.getType() + " " + block.getBlock_X() + " " + block.getBlock_Y() + " " +  prize.getType());
            }
        }
        printStream.println("VisiblePrizes: " + gamePlayPanel.getVisiblePrizes().size());
        for (Prize prize:gamePlayPanel.getVisiblePrizes()) {
            printStream.println(prize.getType() + " " + prize.getPrize_X() + " " + prize.getPrize_Y());
        }

        printStream.println("Score: " + gamePlayPanel.getScore());

        printStream.println("Health: " + gamePlayPanel.getHealth());

        printStream.println("SecondsLeft: " + gamePlayPanel.getSecondsLeft().size());
        for (Integer type:gamePlayPanel.getSecondsLeft().keySet()) {
            printStream.println(type + " " + gamePlayPanel.getSecondsLeft().get(type));
        }

        printStream.flush();
        printStream.close();
    }

    public static void SaveLeaderBoards (HashMap<String , Integer> Scores) throws IOException {
        File file = new File("./src/main/resources/LeaderBoards/Scores");
        if (!file.exists()){
            file.createNewFile();
        }
        PrintStream printStream = new PrintStream(new FileOutputStream(file) , false);
        for (String name: Scores.keySet()) {
            printStream.println(name + "    " + Scores.get(name));
        }
        printStream.flush();
        printStream.close();
    }
}
