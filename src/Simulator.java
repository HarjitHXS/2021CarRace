import java.util.*;

public class Simulator {
    private ArrayList<Car> cars;
    private HashMap<Pair, Tile> grid;
    private int time = 0;
    private ArrayList<BoardEntry> leaderBoard;

    public Simulator(ArrayList<Car> cars, HashMap<Pair, Tile> grid) {
        this.cars = cars;
        this.grid = grid;
        this.leaderBoard = new ArrayList<>();
    }

    /**
     * This is the method to update our race. Here all cars make a move and we update their position in grid.
     */
    public void step() {
        // Remove all cars from the map (so they can update their position.)
        //TODO: Consider changing this to check that cars don't make an invalid move (Maybe change the Racer.drive() method too)
        for (Car car : cars) {
            int x = car.getX();
            int y = car.getY();
            grid.put(new Pair(x, y), Tile.EMPTY_TILE); // Replace the old location with empty tile.
            // make the cars move:
            //TODO: Check the return of racer.drive(), and add to leaderboard if its false.
            car.drive();
            int newX = car.getX();
            int newY = car.getY();
            grid.put(new Pair(newX, newY), car);
        }
    }

    /**
     * We use this instead of a constructor for now.
     * (This is just temporary, but shows how the map is made.)
     * @return
     */
    public final static Simulator generateRace() {
        HashMap<Pair, Tile> map = new HashMap<Pair, Tile>();
        Car car = new Car(0, 0, new ArrayList<>(Arrays.asList(new Pair(5,0))));
        map.put(new Pair(0,0), car);
        map.put(new Pair(1,0), Tile.EMPTY_TILE);
        map.put(new Pair(2,0), Tile.EMPTY_TILE);
        map.put(new Pair(3,0), Tile.EMPTY_TILE);
        map.put(new Pair(0,1), Tile.EMPTY_TILE);
        map.put(new Pair(1,1), Tile.EMPTY_TILE);
        map.put(new Pair(2,1), Tile.EMPTY_TILE);
        map.put(new Pair(3,1), Tile.EMPTY_TILE);


        Simulator simulator = new Simulator(
                new ArrayList<>(Arrays.asList(car)),
                map
        );
        return simulator;
    }

    public HashMap<Pair, Tile> getGrid() {
        return grid;
    }
}
