import java.util.concurrent.ThreadLocalRandom;
import java.io.FileWriter;
import java.io.IOException;

public class RunRamblersAStar {
    public static void main(String args[]) {
        TerrainMap tm = new TerrainMap("tmc.pgm");
        int maxWidth = tm.getWidth();
        int maxDepth = tm.getDepth();
        int numOfTests = 100;
        float sumAStar = 0;
        float sumBB = 0;
        float effAStar;
        float effBB;
        try {
            FileWriter myWriter = new FileWriter("dataset.txt");

            for (int i = 0; i < numOfTests; i++) {

                int randomX = ThreadLocalRandom.current().nextInt(0, maxWidth);
                int randomY = ThreadLocalRandom.current().nextInt(0, maxDepth);
                Coords start = new Coords(randomY, randomX);

                myWriter.write("Start Coord: y=" + randomY + "; x= " + randomX + "\n");

                randomX = ThreadLocalRandom.current().nextInt(0, maxWidth);
                randomY = ThreadLocalRandom.current().nextInt(0, maxDepth);
                Coords goal = new Coords(randomY, randomX);

                myWriter.write("Goal Coord: y=" + randomY + "; x= " + randomX + "\n");

                myWriter.write("----------------------------- \n");

                RamblersSearch searcher = new RamblersSearch(tm, goal);
                SearchState iniStateAStar = (SearchState) new RamblersStateAStar(start, 0, 0);
                SearchState iniStateBB = (SearchState) new RamblersStateBB(start, 0);

                float res_AStar = searcher.runSearchE(iniStateAStar, "AStar");
                float res_BB = searcher.runSearchE(iniStateBB, "branchAndBound");
                sumAStar += res_AStar;
                sumBB += res_BB;
            }
            effAStar = sumAStar / numOfTests;
            effBB = sumBB / numOfTests;

            System.out.println("Efficiency after " + numOfTests + " random cases using AStar is: " + effAStar);
            System.out.println("Efficiency after " + numOfTests + " random cases using BB is: " + effBB);
            System.out.println("Efficiency difference between AStar and BB is: " + (effAStar - effBB));
            System.out.println("Check the created file (dataset) in order to view every set of coordinates");
            myWriter.write("CONCLUSIONS \n");
            myWriter.write("Efficiency after " + numOfTests + " random cases using AStar is: " + effAStar + "\n");
            myWriter.write("Efficiency after " + numOfTests + " random cases using BB is: " + effBB + "\n");
            myWriter.write("Efficiency difference between AStar and BB is: " + (effAStar - effBB) + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}