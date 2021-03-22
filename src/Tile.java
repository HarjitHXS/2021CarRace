/**
 * Class written by Saif and edited by Alex
 *
 * Represents the tiles in our 2-d map
 * {
 * Tile(car), Tile(street)
 * Tile, Tile
 * ...}
 */

public class Tile {
    public final static Tile EMPTY_TILE = new Tile(types.STREET);

    public final static Tile GRASS_TILE = new Tile(types.GRASS);

    public final static Tile CHECKPOINT_TILE = new Tile(types.CHECKPOINT);

    private types type;

    public Tile(types type) {
        this.type = type;
    }

    public types getType() {
        return type;
    }
}

enum types {CAR, STREET, GRASS, CHECKPOINT};
