import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GuiGrid extends GridPane {
    private HashMap<Pair, Tile> simMap;
    private HashMap<Pair, Node> nodesMap; // This lets us access nodes in our GridPane (GridPane does not include that.)
    private Car selectedCar;
    private ArrayList<Pair> checkpoints;

    public GuiGrid(HashMap<Pair, Tile> simMap, ArrayList<Pair> checkpoints) {
        // Iterate on the venueGrid, to generate our GuiGrid.
        super();
        this.checkpoints = checkpoints;
        this.simMap = simMap;
        nodesMap = new HashMap<>();
        update();
    }


    public HashMap<Pair, Node> getNodesMap() {
        return nodesMap;
    }

    //Written by Alex with Saif's help
    private void highlightSelectedPath() {
        if (selectedCar == null) return;

        for (Pair p : selectedCar.getNextMoves()) {
            Node node = nodesMap.get(p);
            if(selectedCar.getCarColor().equals("green"))
                node.getStyleClass().add("highlight-green");
            if(selectedCar.getCarColor().equals("red"))
                node.getStyleClass().add("highlight-red");
            if(selectedCar.getCarColor().equals("blue"))
                node.getStyleClass().add("highlight-blue");

        }
    }

    /** Written by Saif with edits by Alex
     * This function is used to update the GUI to show the cars moving in real time.
     */
    public void update() {
        this.getChildren().clear();
        nodesMap = new HashMap<>();
        for (Map.Entry<Pair, Tile> entry: simMap.entrySet()){
            Pair pair = entry.getKey();
            Tile tile = entry.getValue();
            Node child;
            if(tile.getType() == types.CAR) {
                Car car = (Car) tile;
                child = new GuiTile(tile.getType(), car.getCarColor());
            }
            else {
                child = new GuiTile((tile.getType()), "");
                if (checkpoints.contains(pair))
                    child = new GuiTile((tile.getType()), "yellow");

            }
            add(child, pair.getX(), pair.getY());
            child.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                if (!(tile instanceof Car)) return;
                selectedCar = (Car) tile;
                highlightSelectedPath();
            });
        }
        highlightSelectedPath();
    }

    //Written by Saif
    @Override
    public void add(Node child, int columnIndex, int rowIndex) {
        super.add(child, columnIndex, rowIndex);
        nodesMap.put(new Pair(columnIndex, rowIndex), child);
    }
}
