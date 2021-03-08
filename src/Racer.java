import java.util.ArrayList;

public class Racer extends Tile {
    private int x,y;
    private ArrayList<Pair> goalPath;
    private int pathIndex; // Keeps track of which checkpoint is next.

    public Racer(int x, int y) {
        super(types.CAR);
        this.x = x;
        this.y = y;
    }

    public boolean hasFinished() {
        return pathIndex == goalPath.size();
    }

    /**
     * moves the racer to a new location
     * @return true if the racer has moved, false otherwise (racer already finished.)
     */
    public boolean drive() {
        if (hasFinished()) return false;
        // Temporarily, we just add +1 in x
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
