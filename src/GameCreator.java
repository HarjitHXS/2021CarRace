import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.HashMap;


public class GameCreator extends GridPane{

    private GuiGrid guiGrid;
    private String optionString;
    private int columns;
    private int rows;
    private int numOfCars;

    public GameCreator(int columns, int rows){
        this.columns = columns;
        this.rows = rows;
        this.guiGrid = new GuiGrid(createTileMap());
        this.optionString = "street";
        this.numOfCars = 0;
        setupBoard();
    }

    private void setupBoard(){
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                int xCord = i;
                int yCord = j;
                //Create a blank grid for the user to pick grass/street/car tiles
                Label gridSquare = new Label("");
                gridSquare.setPrefSize(60,60);
                gridSquare.setStyle("-fx-border-color: black");
                gridSquare.setOnMouseClicked(event -> {
                    if(optionString.equals("grass")) {
                        guiGrid.add(new GuiTile(types.GRASS), xCord, yCord);
                        System.out.println("Added grass");
                    }
                    //node.getStyleClass().add("highlight-green");
                    if(optionString.equals("car") && numOfCars < 3){
                        guiGrid.add(new GuiTile(types.CAR), xCord, yCord);
                        System.out.println("Added car");
                        numOfCars++;
                        //node.getStyleClass().add("car");
                    }
                    guiGrid.update();
                });
                super.add(gridSquare, xCord , yCord);
            }
        }
    }

    public void changeOption(String s){
        optionString = s;
    }

    private HashMap<Pair, Tile> createTileMap(){
        HashMap<Pair, Tile> output = new HashMap<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++)
                output.put(new Pair(i,j), Tile.EMPTY_TILE);
        }
        return output;
    }

    //to string method
    @Override
    public String toString() {
        return "Add the cars and add grass as you like and then click on the play with these settings";
    }


    public GuiGrid getGuiGrid() {
        return guiGrid;
    }

    public int getNumOfCars(){ return numOfCars; }
}