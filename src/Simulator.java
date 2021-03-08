import java.util.*;

public class Simulator {
    private ArrayList<Racer> racers;
    private HashMap<Pair, Tile> grid;
    private int time = 0;
    private ArrayList<BoardEntry> leaderBoard;

    public Simulator(ArrayList<Racer> racers, HashMap<Pair, Tile> grid) {
        this.racers = racers;
        this.grid = grid;
        this.leaderBoard = new ArrayList<>();
    }

    /**
     * This is the method to update our race. Here all racers make a move and we update their position in grid.
     */
    public void step() {
        // Remove all racers from the map (so they can update their position.)
        //TODO: Consider changing this to check that racers don't make an invalid move (Maybe change the Racer.drive() method too)
        for (Racer racer : racers) {
            int x = racer.getX();
            int y = racer.getY();
            grid.put(new Pair(x, y), Tile.EMPTY_TILE); // Replace the old location with empty tile.
            // make the racers move:
            //TODO: Check the return of racer.drive(), and add to leaderboard if its false.
            racer.drive();
            int newX = racer.getX();
            int newY = racer.getY();
            grid.put(new Pair(newX, newY), racer);
        }
    }

    /**
     * We use this instead of a constructor for now.
     * (This is just temporary, but shows how the map is made.)
     * @return
     */
    public final static Simulator generateRace() {
        HashMap<Pair, Tile> map = new HashMap<Pair, Tile>();
        map.put(new Pair(0,0), new Racer(0,0));
        map.put(new Pair(1,0), Tile.EMPTY_TILE);
        map.put(new Pair(2,0), Tile.EMPTY_TILE);
        map.put(new Pair(3,0), Tile.EMPTY_TILE);
        map.put(new Pair(0,1), Tile.EMPTY_TILE);
        map.put(new Pair(1,1), Tile.EMPTY_TILE);
        map.put(new Pair(2,1), Tile.EMPTY_TILE);
        map.put(new Pair(3,1), Tile.EMPTY_TILE);


        Simulator simulator = new Simulator(
                new ArrayList<>(Arrays.asList(new Racer(0, 0))),
                map
        );
        return simulator;
    }

    public HashMap<Pair, Tile> getGrid() {
        return grid;
    }
}
