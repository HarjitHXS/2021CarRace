/*
 * Class was created by Alex with help by Saif
 */

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameCreator extends GridPane{

    private GuiGrid guiGrid;
    private int columns;
    private int rows;
    private Tile selectedTile = Tile.GRASS_TILE; // When user clicks on a tile, it will be replaced with this.
    private Button toggleSelection = new Button("Add Grass");
    private HashMap<Pair, Tile> tilesMap;
    private ArrayList<Pair> checkpoints = new ArrayList<>();

    public GameCreator(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
        tilesMap = createTileMap();
        this.guiGrid = new GuiGrid(tilesMap);
        add(guiGrid, 0, 0);
        add(toggleSelection,0,2);
        toggleSelection.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(toggleSelection.getText().equals("Add Grass")) {
                toggleSelection.setText("Add Street");
                selectedTile = Tile.EMPTY_TILE;
            }
            else if(toggleSelection.getText().equals("Add Street")) {
                toggleSelection.setText("Add Grass");
                selectedTile = Tile.GRASS_TILE;
            }
        });
        addOnClicks();
    }

    /** Edited by Saif
     * Adds event handlers to the tiles so we can fill them with the selectedTile
     */
    private void addOnClicks() {
        for (Map.Entry<Pair, Node> entry: guiGrid.getNodesMap().entrySet())
            entry.getValue().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                // 1.Update tileMap
                Pair pair = entry.getKey();
                if (selectedTile != null)
                    tilesMap.put(pair, selectedTile);
                // 2. Update Display (GuiGrid)
                guiGrid.update();
                //3. Re-add EventHandlers
                addOnClicks(); // GuiGrid's update will change the Nodes, and they don't have the handler
            });
    }

    public void unoccupiedTiles(HashMap<Pair, Tile> input){
        ArrayList<Pair> output = new ArrayList<>();
        for(Map.Entry<Pair, Tile> entry: input.entrySet()){
            if(entry.getValue().getType() != types.GRASS)
                output.add(entry.getKey());
        }
        generateCheckpoints(output);
    }

    private void generateCheckpoints(ArrayList<Pair> blankTiles){
        for(int i = 0; i < 4; i++){
            int random_int = (int)(Math.random() * (blankTiles.size()));
            checkpoints.add(blankTiles.get(random_int));
            blankTiles.remove(random_int);
        }
        //System.out.println(checkpoints);
    }
    //Creates a blank tile map that the user uses to create grass tiles
    private HashMap<Pair, Tile> createTileMap(){
        HashMap<Pair, Tile> output = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                output.put(new Pair(i,j), Tile.EMPTY_TILE);
        }
        return output;
    }

    public GuiGrid getGuiGrid() {
        return guiGrid;
    }

    public HashMap<Pair, Tile> getTilesMap(){ return tilesMap; }

    public ArrayList<Pair> getCheckpoints(){ return checkpoints; }

}