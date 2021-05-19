import java.util.concurrent.ThreadLocalRandom;

public class RunRamblersBB {
    public static void main(String args[]) {
        TerrainMap tm = new TerrainMap("tmc.pgm");
        int maxWidth = tm.getWidth();
        int maxDepth = tm.getDepth();
        float sum=0;
        for (int i = 0; i < 100; i++) {

            int randomX = ThreadLocalRandom.current().nextInt(0, maxWidth);
            int randomY = ThreadLocalRandom.current().nextInt(0, maxDepth);
            Coords start = new Coords(randomY, randomX);

            randomX = ThreadLocalRandom.current().nextInt(0, maxWidth);
            randomY = ThreadLocalRandom.current().nextInt(0, maxDepth);
            Coords goal = new Coords(randomY, randomX);

            RamblersSearch searcher = new RamblersSearch(tm, goal);
            SearchState iniState = (SearchState) new RamblersState(start, 0);

            float res_bb = searcher.runSearchE(iniState, "branchAndBound");
            sum += res_bb;
        }

        System.out.println("Efficiency after 100 random cases is: " + sum/100);

    }
}