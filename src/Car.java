import java.util.ArrayList;

public class Car extends Tile {
    private int x,y;
    private ArrayList<Pair> goalPath;
    private int pathIndex; // Keeps track of which checkpoint is next.

    public Car(int x, int y) {
        super(types.CAR);
        this.x = x;
        this.y = y;
        goalPath = new ArrayList<>();
    }

    public boolean hasFinished() {
        return pathIndex == goalPath.size();
    }

    private Pair nextPoint() {
        return goalPath.get(pathIndex);
    }

    /**
     * moves the racer to a new location
     * @return true if the racer has moved, false otherwise (racer already finished.)
     */
    public boolean drive() {
        if (hasFinished()) return false;
        // Temporarily, we just add +1 in x
        //TODO: calculate the shortest path from our (x,y) location, to the nextPoint
        // And update x/y to the next movement to make.
        x += 1;
        //TODO: Check if we have reached a checkpoint. (compare location with goalPath.get(pathIndex))
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
