import java.util.*;

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
            
        }

        return succs;
    }

    // sameState

    public boolean sameState(SearchState s2) {
        RamblersStateBB rs2 = (RamblersStateBB) s2;
        return (currentCoords.getx() == rs2.getCurrentCoords().getx()
                && currentCoords.gety() == rs2.getCurrentCoords().gety());
    }

    // toString
    public String toString() {
        return ("Coords State: " + currentCoords.gety() + " " + currentCoords.getx());
    }

}

