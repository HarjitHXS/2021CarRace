import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Map;

public class GuiGrid extends GridPane {
    private HashMap<Pair, Tile> simMap;
    private HashMap<Pair, Node> nodesMap; // This lets us access nodes in our GridPane (GridPane does not include that.)

    public GuiGrid(HashMap<Pair, Tile> simMap) {
        // Iterate on the venueGrid, to generate our GuiGrid.
        super();
        this.simMap = simMap;
        nodesMap = new HashMap<>();
        for (Map.Entry<Pair, Tile> entry: simMap.entrySet()){
            Pair pair = entry.getKey();
            Tile tile = entry.getValue();
            add(new GuiTile(tile.getType()), pair.getX(), pair.getY());
        }
    }

    public void update() {
        this.getChildren().clear();
        nodesMap = new HashMap<>();
        for (Map.Entry<Pair, Tile> entry: simMap.entrySet()){
            Pair pair = entry.getKey();
            Tile tile = entry.getValue();
            add(new GuiTile(tile.getType()), pair.getX(), pair.getY());
        }
    }

    @Override
    public void add(Node child, int columnIndex, int rowIndex) {
        super.add(child, columnIndex, rowIndex);
        nodesMap.put(new Pair(rowIndex, columnIndex), child);
    }


}
