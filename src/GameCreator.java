import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.util.HashMap;


public class GameCreator extends GridPane{

    private HashMap<Pair, Node> nodesMap;
    private HashMap<Pair, Tile> userChoices;

    public GameCreator(){
        setupBoard();
        this.nodesMap = new HashMap<>();
        this.userChoices = new HashMap<>();
    }

    private void setupBoard(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                Label gridSquare = new Label("");
                gridSquare.setPrefSize(60,60);
                gridSquare.getStyleClass().add("street");
                gridSquare.setOnMouseClicked(event -> {
                    System.out.println("Test");
                });
                super.add(gridSquare, i , j);
            }
        }
    }
    //to string method
    @Override
    public String toString() {
        return "Add the cars and add grass as you like and then click on the play with these settings";
    }





}