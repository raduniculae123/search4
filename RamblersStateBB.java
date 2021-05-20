/*
*	State in a map search
*	Phil Green 2013 version
* Heidi Christensen (heidi.christensen@sheffield.ac.uk) 2021 version
*/

import java.util.*;
import java.math.*;

public class RamblersStateBB extends SearchState {

    private Coords currentCoords;

    // constructor
    public RamblersStateBB(Coords coords, int lc) {
        currentCoords = coords;
        localCost = lc;
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
            if (map.getTmap()[coord.gety()][coord.getx()] <= map.getTmap()[currentCoords.gety()][currentCoords
                    .getx()]) {
                succs.add((SearchState) new RamblersStateBB(coord, 1));
            } else {
                succs.add(
                        (SearchState) new RamblersStateBB(coord, 1 + Math.abs(map.getTmap()[coord.gety()][coord.getx()]
                                - map.getTmap()[currentCoords.gety()][currentCoords.getx()])));
            }
        }

        return succs;
    }

    // sameState

    public boolean sameState(SearchState s2) {
        RamblersStateBB rs2 = (RamblersStateBB) s2;
        return (currentCoords.getx() == rs2.getCurrentCoords().getx()
                && currentCoords.gety() == rs2.getCurrentCoords().gety());
    }

    // euclid
    public int estEuclideanDistance(int startY, int startX, int goalY, int goalX) {
        int estCost;

        estCost = (int) Math.sqrt((goalY - startY) * (goalY - startY) + (goalX - startX) * (goalX - startX));

        return estCost;
    }

    // manhatan
    public int estManhattanDistance(int startY, int startX, int goalY, int goalX) {
        int estCost = Math.abs(goalY - startY) + Math.abs(goalX - startX);

        return estCost;
    }

    // toString
    public String toString() {
        return ("Coords State: " + currentCoords.gety() + " " + currentCoords.getx());
    }

}
