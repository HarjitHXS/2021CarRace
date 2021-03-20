import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GuiGrid extends GridPane {
    private HashMap<Pair, Tile> simMap;
    private HashMap<Pair, Node> nodesMap; // This lets us access nodes in our GridPane (GridPane does not include that.)
    private Car selectedCar;

    public GuiGrid(HashMap<Pair, Tile> simMap) {
        // Iterate on the venueGrid, to generate our GuiGrid.
        super();
        this.simMap = simMap;
        nodesMap = new HashMap<>();
        update();
    }

    private void highlightSelectedPath() {
        if (selectedCar == null) return;
        for (Pair p : selectedCar.getNextMoves()) {
            Node node = nodesMap.get(p);
            node.getStyleClass().add("highlight-blue");
        }
    }

    public void update() {
        this.getChildren().clear();
        nodesMap = new HashMap<>();
        for (Map.Entry<Pair, Tile> entry: simMap.entrySet()){
            Pair pair = entry.getKey();
            Tile tile = entry.getValue();
            Node child = new GuiTile((tile.getType()));
            add(child, pair.getX(), pair.getY());
            child.setOnMouseClicked(event -> {
                if (!(tile instanceof Car)) return;
                selectedCar = (Car) tile;
                highlightSelectedPath();
            });
        }
        highlightSelectedPath();
    }


    @Override
    public void add(Node child, int columnIndex, int rowIndex) {
        super.add(child, columnIndex, rowIndex);
        nodesMap.put(new Pair(columnIndex, rowIndex), child);
    }


}
