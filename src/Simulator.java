import javafx.scene.paint.Color;

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
        time+=1;
        for (Car car : cars) {
            if (car.hasFinished()) continue; // This prevents adding the same car multiple times.
            int x = car.getX();
            int y = car.getY();
            grid.put(new Pair(x, y), Tile.EMPTY_TILE); // Replace the old location with empty tile.
            // make the cars move:
            //TODO: Check the return of racer.drive(), and add to leaderboard if its false.
            car.drive();
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
    public final static Simulator generateRace(HashMap<Pair, Tile> gameBoard) {
        Car car = new Car(0, 0, new ArrayList<>(Arrays.asList(
                new Pair(0, 7), new Pair(3, 1)
        )), gameBoard, "Car1", Color.RED, 2);
        ArrayList<Car> cars = new ArrayList<>(Arrays.asList(
                car,
                new Car(0, 7, new ArrayList(Arrays.asList(new Pair(6,5), new Pair(8, 2))), gameBoard, "Car2",Color.BLUE, 1),
                new Car(0, 8, new ArrayList(Arrays.asList(new Pair(6, 7), new Pair(3,3))), gameBoard, "Car3",Color.GREEN, 1)
        ));

        return generateHelper(10, 10, cars, gameBoard);
    }

    public final static Simulator generateHelper(int rows, int cols, ArrayList<Car> cars, HashMap<Pair, Tile> map) {
        // Fill map with empty tiles
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++)
                map.put(new Pair(i,j), Tile.EMPTY_TILE);
        }
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

    public ArrayList<BoardEntry> getLeaderBoard() {

        return leaderBoard;
    }
}
