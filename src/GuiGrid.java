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

    public GuiGrid(HashMap<Pair, Tile> simMap) {
        // Iterate on the venueGrid, to generate our GuiGrid.
        super();
        this.simMap = simMap;
        nodesMap = new HashMap<>();
        for (Map.Entry<Pair, Tile> entry: simMap.entrySet()){
            Pair pair = entry.getKey();
            Tile tile = entry.getValue();
            GuiTile child = new GuiTile(tile.getType());
            add(child, pair.getX(), pair.getY());
            child.setOnMouseClicked(event -> {
                ArrayList<Pair> pathList = Simulator.getPath(pair, simMap);

            });

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
            child.setOnMouseClicked(event -> Simulator.getPath(pair, simMap));
        }
    }

    public void highlightPath(ArrayList<Pair> pathList, Color color){
        this.getChildren().clear();
        nodesMap = new HashMap<>();
        for (Map.Entry<Pair, Tile> entry: simMap.entrySet()){
            Pair pair = entry.getKey();
            Tile tile = entry.getValue();
            if(tile.getType() != types.STREET && tile.getType() != types.GRASS && tile.getType() != types.CAR) {
                Node highlightTile = new GuiTile(color);
            }
            else{
                Node child = new GuiTile((tile.getType()));
                add(child, pair.getX(), pair.getY());
                child.setOnMouseClicked(event -> Simulator.getPath(pair, simMap));

            }




        }
    }

    @Override
    public void add(Node child, int columnIndex, int rowIndex) {
        super.add(child, columnIndex, rowIndex);
        nodesMap.put(new Pair(rowIndex, columnIndex), child);
    }


}
