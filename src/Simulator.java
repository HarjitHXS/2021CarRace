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
            if (car.hasFinished()) continue; // This prevents adding the same car multiple times.
            int x = car.getX();
            int y = car.getY();
            grid.put(new Pair(x, y), Tile.EMPTY_TILE); // Replace the old location with empty tile.
            // make the cars move:
            //TODO: Check the return of racer.drive(), and add to leaderboard if its false.
            car.drive();
            time+=1;
            int newX = car.getX();
            int newY = car.getY();
            grid.put(new Pair(newX, newY), car);
            if(car.hasFinished())leaderBoard.add(new BoardEntry(car,time));
        }
    }

    /**
     * We use this instead of a constructor for now.
     * (This is just temporary, but shows how the map is made.)
     * @return
     */
    public final static Simulator generateRace() {
        HashMap<Pair, Tile> map = new HashMap<>();
        Car car = new Car(5, 8, new ArrayList<>(Arrays.asList(
                new Pair(2, 2), new Pair(3, 1)
        )), map, "Car1");
        ArrayList<Car> cars = new ArrayList<>(Arrays.asList(
                car,
                new Car(0, 0, new ArrayList(Arrays.asList(new Pair(5,5), new Pair(8, 2))), map, "Car2"),
                new Car(5, 3, new ArrayList(Arrays.asList(new Pair(1, 2), new Pair(3,3))), map, "Car3")
        ));
        return generateHelper(10, 10, cars, map);
    }

    public final static Simulator generateHelper(int rows, int cols, ArrayList<Car> cars, HashMap<Pair, Tile> map) {
        // Fill map with empty tiles
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++)
                map.put(new Pair(i,j), Tile.EMPTY_TILE);
        }

        // Make a grass patch down the middle
        for (int i=0; i<rows/2; i++)
            map.put(new Pair(cols/2, i), Tile.GRASS_TILE);
        // Add cars
        for (Car car : cars)
            map.put(new Pair(car.getX(), car.getY()), car);

        return new Simulator(
                cars,
                map
        );
    }

    public HashMap<Pair, Tile> getGrid() {
        return grid;
    }

    public static ArrayList<Pair> getPath(Pair inputPair, HashMap<Pair, Tile> grid) {
        Tile tile =  grid.get(inputPair);
        if ((tile instanceof Car)) {
            Car car = (Car) tile;
            System.out.println("x : " + ((Car) tile).getX() + " y: " + ((Car) tile).getY());
            return car.getNextMoves();
        }
        else
            return null;


    }
    public ArrayList<BoardEntry> getLeaderBoard() {

        return leaderBoard;
    }
}
