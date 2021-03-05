public class Racer extends Tile {
    private int x,y;

    public Racer(int x, int y) {
        super(types.CAR);
        this.x = x;
        this.y = y;
    }

    /**
     * moves the racer to a new location
     */
    public void drive() {
        // Temporarily, we just add +1 in x
        x += 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
