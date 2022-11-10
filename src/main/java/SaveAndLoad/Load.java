package SaveAndLoad;

import BlockPack.*;
import Models.Ball;
import Models.Paddle;
import PrizePack.*;
import logicPanel.BlockGenerator;
import logicPanel.GamePlayPanel;

import java.io.File;
import java.io.IOException;
import java.util.*;

public abstract class Load {

    public static GamePlayPanel loadTheGamePanel (String name) throws IOException {
        File playerFile = new File("./src/main/resources/PlayersGamePanel" + "\\" + name + ".txt");
        Scanner scanner = new Scanner(playerFile);
        scanner.next();
        int paddle_X = scanner.nextInt();
        int paddle_Y = scanner.nextInt();
        Paddle paddle = new Paddle(paddle_X , paddle_Y);
        scanner.next();
        int ballNumbers = scanner.nextInt();
        ArrayList<Ball> balls = new ArrayList<>();
        for (int i = 0; i < ballNumbers; i++) {
            int Ball_X = scanner.nextInt();
            int Ball_Y = scanner.nextInt();
            balls.add(new Ball(Ball_X , Ball_Y , -6 , -8));
        }
        scanner.next();
        int blockNumbers = scanner.nextInt();
        ArrayList<Block> AllBlocks = new ArrayList<>();
        for (int i = 0; i < blockNumbers; i++) {
            int type = scanner.nextInt();
            int Block_X = scanner.nextInt();
            int Block_Y = scanner.nextInt();
            switch (type){
                case 1:
                    AllBlocks.add(new GlassBlock(Block_X , Block_Y));
                    break;
                case 2:
                    AllBlocks.add(new WoodenBlock(Block_X ,Block_Y));
                    break;
                case 3:
                    AllBlocks.add(new InvisibleBlock(Block_X , Block_Y));
                    break;
                case 4:
                    AllBlocks.add(new BlinkerBlock(Block_X , Block_Y));
                    break;
                case 5:
                    int prizeType = scanner.nextInt();
                    Prize prize = MakePrizeByType(prizeType , Block_X , Block_Y , false);
                    AllBlocks.add(new PrizeBlock(Block_X , Block_Y , prize));
                    break;
            }
        }
        BlockGenerator blockGenerator = new BlockGenerator(AllBlocks);
        scanner.next();
        int visiblePrizesNumbers = scanner.nextInt();
        ArrayList<Prize> VisiblePrizes = new ArrayList<>();
        for (int i = 0; i < visiblePrizesNumbers; i++) {
            int prizeType = scanner.nextInt();
            int prize_X = scanner.nextInt();
            int prize_Y = scanner.nextInt();
            VisiblePrizes.add(MakePrizeByType(prizeType , prize_X , prize_Y , false));
        }
        scanner.next();
        int Score = scanner.nextInt();
        scanner.next();
        int Health = scanner.nextInt();
        scanner.next();
        int SecondsLeftSize = scanner.nextInt();
        HashMap<Integer , Integer> SecondsLeft = new HashMap<>();
        for (int i = 0; i < SecondsLeftSize; i++) {
            int type = scanner.nextInt();
            int Seconds = scanner.nextInt();
            SecondsLeft.put(type , Seconds);
        }

        return new GamePlayPanel(paddle , Health , blockGenerator , VisiblePrizes , balls , Score , SecondsLeft);
    }

    public static Prize MakePrizeByType (int type , int Prize_X , int Prize_Y , boolean IsRandom) throws IOException {
        switch (type){
            case 0:
                Random random = new Random();
                int type2 = random.nextInt(7)+1;
                return MakePrizeByType(type2 , Prize_X , Prize_Y , true);
            case 1:
                return new BigPaddlePrize(Prize_X , Prize_Y , IsRandom);
            case 2:
                return new SmallPaddlePrize(Prize_X , Prize_Y , IsRandom);
            case 3:
                return new FastBallPrize(Prize_X , Prize_Y , IsRandom);
            case 4:
                return new SlowBallPrize(Prize_X , Prize_Y , IsRandom);
            case 5:
                return new StunPaddlePrize(Prize_X , Prize_Y , IsRandom);
            case 6:
                return new FireBallPrize(Prize_X , Prize_Y , IsRandom);
            case 7:
                return new TripleBallPrize(Prize_X , Prize_Y , IsRandom);
        }
        return null;
    }

    public static ArrayList<String> loadAllNames (){
        File playerFolder = new File("./src/main/resources/PlayersGamePanel");
        ArrayList<String> AllNames = new ArrayList<>();
        for (File playerFile:playerFolder.listFiles()) {
            AllNames.add(playerFile.getName());
        }
        return AllNames;
    }

    public static HashMap<String , Integer> loadAllScores() throws IOException {
        File file = new File("./src/main/resources/LeaderBoards" + "\\" + "Scores");
        HashMap<String , Integer> AllScores = new HashMap<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String name = scanner.next();
            int Score = scanner.nextInt();
            AllScores.put(name , Score);
        }
        return sortByValue(AllScores);
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer> > list = new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
        Collections.sort(list, (o1, o2) -> (o2.getValue()).compareTo(o1.getValue()));
        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
