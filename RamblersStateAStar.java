import java.util.*;
import java.lang.Math;
import javax.swing.table.TableModel;

public class RamblersStateAStar extends SearchState {

    private Coords currentCoords;

    // constructor
    public RamblersStateAStar(Coords coords, int lc, int remCost) {
        currentCoords = coords;
        localCost = lc;
        estRemCost = remCost;

    }

    // accessor
    public Coords getCurrentCoords() {
        return currentCoords;
    }

    // goalPredicate
    public boolean goalPredicate(Search searcher) {
        RamblersSearch msearcher = (RamblersSearch) searcher;
        Coords tar = msearcher.getGoal(); // get target city
        return (currentCoords.getx() == tar.getx() && currentCoords.gety() == tar.gety());
    }

    // getSuccessors
    public ArrayList<SearchState> getSuccessors(Search searcher) {
        RamblersSearch rsearcher = (RamblersSearch) searcher;
        TerrainMap map = rsearcher.getMap();
        ArrayList<Coords> succsCoords = new ArrayList<Coords>();
        ArrayList<SearchState> succs = new ArrayList<SearchState>();

        // EAST
        if (currentCoords.getx() + 1 < map.getWidth() - 1) {
            Coords newCoords = new Coords(currentCoords.gety(), currentCoords.getx() + 1);
            succsCoords.add(newCoords);

        }

        // WEST
        if (currentCoords.getx() - 1 >= 0) {
            Coords newCoords = new Coords(currentCoords.gety(), currentCoords.getx() - 1);
            succsCoords.add(newCoords);
        }

        // SOUTH
        if (currentCoords.gety() + 1 < map.getDepth()) {
            Coords newCoords = new Coords(currentCoords.gety() + 1, currentCoords.getx());
            succsCoords.add(newCoords);
        }

        // NORTH
        if (currentCoords.gety() - 1 >= 0) {
            Coords newCoords = new Coords(currentCoords.gety() - 1, currentCoords.getx());
            succsCoords.add(newCoords);
        }

        for (Coords coord : succsCoords) {
            
              estRemCost = estHeightDiff(currentCoords.gety(), currentCoords.getx(),
              rsearcher.getGoal().gety(), rsearcher.getGoal().getx(), rsearcher);
             
/*
            estRemCost = estEuclideanDistance(currentCoords.gety(), currentCoords.getx(), rsearcher.getGoal().gety(),
                    rsearcher.getGoal().getx());
*/
            /*
              estRemCost = estManhattanDistance(currentCoords.gety(), currentCoords.getx(),
              rsearcher.getGoal().gety(), rsearcher.getGoal().getx());
             */
            /*
             * // Combination between all three methods (mean average of them) estRemCost =
             * (estManhattanDistance(currentCoords.gety(), currentCoords.getx(),
             * rsearcher.getGoal().gety(), rsearcher.getGoal().getx()) +
             * estEuclideanDistance(currentCoords.gety(), currentCoords.getx(),
             * rsearcher.getGoal().gety(), rsearcher.getGoal().getx()) +
             * estHeightDiff(currentCoords.gety(), currentCoords.getx(),
             * rsearcher.getGoal().gety(), rsearcher.getGoal().getx(), rsearcher)) / 3;
             */
            if (map.getTmap()[coord.gety()][coord.getx()] <= map.getTmap()[currentCoords.gety()][currentCoords
                    .getx()]) {
                succs.add((SearchState) new RamblersStateAStar(coord, 1, estRemCost));
            } else {
                succs.add((SearchState) new RamblersStateAStar(coord,
                        1 + Math.abs(map.getTmap()[coord.gety()][coord.getx()]
                                - map.getTmap()[currentCoords.gety()][currentCoords.getx()]),
                        estRemCost));
            }
        }

        return succs;
    }

    // euclidean
    public int estEuclideanDistance(int startY, int startX, int goalY, int goalX) {
        int estCost;

        estCost = (int) Math.sqrt((goalY - startY) * (goalY - startY) + (goalX - startX) * (goalX - startX));

        return estCost;
    }

    // manhattan
    public int estManhattanDistance(int startY, int startX, int goalY, int goalX) {
        int estCost;

        estCost = Math.abs(goalY - startY) + Math.abs(goalX - startX);

        return estCost;
    }

    // height
    public int estHeightDiff(int startY, int startX, int goalY, int goalX, Search searcher) {
        int estCost;
        RamblersSearch rsearcher = (RamblersSearch) searcher;
        TerrainMap map = rsearcher.getMap();

        estCost = (int) Math.abs(map.getTmap()[goalY][goalX] - map.getTmap()[startY][startX]);

        return estCost;
    }

    // sameState

    public boolean sameState(SearchState s2) {
        RamblersStateAStar rs2 = (RamblersStateAStar) s2;
        return (currentCoords.getx() == rs2.getCurrentCoords().getx()
                && currentCoords.gety() == rs2.getCurrentCoords().gety());
    }

    // toString
    public String toString() {
        return ("Coords State: " + currentCoords.gety() + " " + currentCoords.getx());
    }

}
