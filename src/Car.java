import javafx.scene.paint.Color;

import java.util.*;

public class Car extends Tile {
    private int x,y;
    private ArrayList<Pair> goalPath; // the checkpoints we have to reach.
    private int pathIndex; // Keeps track of which checkpoint is next.
    // nextMoves is different from goalPath, this one is the step-by-step path to next checkpoint.
    private ArrayList<Pair> nextMoves = new ArrayList<>();
    private int nextMoveIndex = 0;
    private HashMap<Pair, Tile> grid;
    private Color carColor;
    private String name;


    public Car(int x, int y, ArrayList<Pair> goalPath, HashMap<Pair, Tile> grid, String name, Color color) {
        super(types.CAR);
        this.x = x;
        this.y = y;
        this.goalPath = goalPath;
        this.grid = grid;
        this.carColor = color;
        this.name = name;
    }


    public boolean hasFinished() {
        return pathIndex == goalPath.size();
    }

    private Pair nextPoint() {
        if (hasFinished()) return new Pair(x,y);
        return goalPath.get(pathIndex);
    }

    /**
     * moves the racer to a new location
     * @return true if the racer has moved, false otherwise (racer already finished.)
     */
    public boolean drive() {
        if (hasFinished()) return false;
        Pair nextMove = nextMove();
        x = nextMove.getX();
        y = nextMove.getY();
        if (nextPoint().equals(new Pair(x, y)))
            pathIndex += 1; // We now aim to the next checkpoint.

        return true;
    }

    /**
     * @return true if we have to recalculate the path to the next checkpoint, false otherwise.
     */
    private boolean shouldRecalculate() {
        if (nextMoves.size()==0 || nextMoveIndex == nextMoves.size())
            return true;
        Pair nextMove = nextMoves.get(nextMoveIndex);
        if (grid.get(nextMove).getType() != types.STREET)
            return true;
        return false;
    }

    private Pair nextMove() {
        if (shouldRecalculate())
            calculatePath();
        Pair nextMove = nextMoves.get(nextMoveIndex);
        nextMoveIndex += 1;
        return nextMove;
    }

    /**
     * Calculates shortest path to next checkPoint, stores result in nextMoves.
     */
    private void calculatePath() {
        // Reset moveIndex;
        nextMoveIndex = 0;
        // we will use a simple BFS, which is suitable for this type of pathFinding.
        // Note: from our start-point, we have to check validAdjacents (so we don't collide), but
        // for future points in the path, we don't worry about that (we don't know the future).
        Pair start = new Pair(x, y);
        HashMap<Pair, Pair> parents = new HashMap<>(); // parents.get(childPair) -> parentPair
        HashSet<Pair> seen = new HashSet<>();
        ArrayDeque<Pair> q = new ArrayDeque<>();
        q.addAll(findAdjacents(start, true));
        q.add(start); // In some cases, it will be best to stay in same place.

        // BFS loop
        while (q.size() != 0) {
            Pair curPoint = q.pop();
            seen.add(curPoint);
            for (Pair curNeighbour : findAdjacents(curPoint, false)) {
                if (seen.contains(curNeighbour) || curNeighbour.equals(start))
                    continue; // Already visited
                seen.add(curNeighbour);
                parents.put(curNeighbour, curPoint);
                q.add(curNeighbour);
                // Check if we reached the checkPoint.
                if (curNeighbour.equals(nextPoint()))
                {
                    nextMoves = constructPath(parents, curNeighbour);
                    return;
                }
            }
        }
    }

    private ArrayList<Pair> constructPath(HashMap<Pair, Pair> parents, Pair target) {
        ArrayList<Pair> path = new ArrayList<>();
        Pair cur = target;
        while (parents.get(cur) != null) // We go backwards, the 1st point will have null.
        {
            path.add(cur);
            cur = parents.get(cur); // going back to the previous point
        }
        path.add(cur); // This gets missed in the loop.
        Collections.reverse(path);
        return path;
    }

    /**
     * @param pair      The pair we are finding adjacents of.
     * @param validOnly true if we don't want to include occupied tiles.
     * @return the adjacent (x,y) pairs
     */
    private ArrayList<Pair> findAdjacents(Pair pair, boolean validOnly) {
        ArrayList<Pair> validAdjacents = new ArrayList<>();

        List<Pair> directions = Arrays.asList( // up,down,left,right
            new Pair(pair.getX(), pair.getY()-1),
            new Pair(pair.getX(), pair.getY()+1),
            new Pair(pair.getX()+1, pair.getY()),
            new Pair(pair.getX()-1, pair.getY()),
            new Pair(pair.getX()-1, pair.getY()-1),
            new Pair(pair.getX()+1, pair.getY()+1),
            new Pair(pair.getX()-1, pair.getY()+1),
            new Pair(pair.getX()-1, pair.getY()-1)

        );
        // Add the valid ones, or all if !validOnly.
        for (Pair p : directions) {
            if (grid.get(p) == null) continue; // usually means we are out of bounds
            boolean shouldAt = ((!validOnly || grid.get(p).getType() == types.STREET || !(grid.get(p).getType() == types.GRASS)));
            if (shouldAt)
                validAdjacents.add(p);



        }

        return validAdjacents;
    }

    public ArrayList<Pair> getNextMoves() {
        return nextMoves;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getCarColor(){ return carColor; }

    public String getName() {
        return name;
    }
}
