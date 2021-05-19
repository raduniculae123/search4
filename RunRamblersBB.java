public class RunRamblersBB {
    public static void main(String args[]) {
        TerrainMap tm = new TerrainMap("diablo.pgm");
        Coords start = new Coords(7, 0);
        Coords goal = new Coords(5, 8);

        RamblersSearch searcher = new RamblersSearch(tm, goal);
        SearchState iniState = (SearchState) new RamblersState(start, 0);

        String res_bb = searcher.runSearch(iniState, "branchAndBound");
        System.out.print(res_bb);
    }
}