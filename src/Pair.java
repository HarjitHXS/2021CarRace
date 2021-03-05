/**
 * Pair class to allow using x,y (row, col) pairs as key in a HashMap.
 */
public class Pair {

    private final int x;
    private final int y;

    public Pair(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pair)) {
            return false;
        }

        final Pair pair = (Pair) o;

        if (x != pair.x) {
            return false;
        }
        return y == pair.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}