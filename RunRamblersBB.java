import java.util.concurrent.ThreadLocalRandom;

public class RunRamblersBB {
    public static void main(String args[]) {
        TerrainMap tm = new TerrainMap("tmc.pgm");
        int maxWidth = tm.getWidth();
        int maxDepth = tm.getDepth();
        int numOfTests = 50;
        float sum=0;
        float eff;
        for (int i = 0; i < numOfTests; i++) {

            int randomX = ThreadLocalRandom.current().nextInt(0, maxWidth);
            int randomY = ThreadLocalRandom.current().nextInt(0, maxDepth);
            Coords start = new Coords(randomY, randomX);

            randomX = ThreadLocalRandom.current().nextInt(0, maxWidth);
            randomY = ThreadLocalRandom.current().nextInt(0, maxDepth);
            Coords goal = new Coords(randomY, randomX);

            RamblersSearch searcher = new RamblersSearch(tm, goal);
            SearchState iniState = (SearchState) new RamblersStateBB(start, 0);

            float res_bb = searcher.runSearchE(iniState, "branchAndBound");
            sum += res_bb;
        }
        eff = sum / numOfTests;

        System.out.println("Efficiency after " + numOfTests + " random cases using branch and bound is: " + eff);

    }
}