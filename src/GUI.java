import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;

public class GUI extends Application {
    Scene scene1;
    private GridPane uiPane = new GridPane();
    private StackPane firstscene = new StackPane ();
    private GridPane senceone = new GridPane();
    private Button play = new Button("   Play   ");
    private Button stepBtn = new Button(" Start ");
    private Button quit = new Button(" Quit ");
    private Scene scene = new Scene(uiPane);
    private Image img = new Image("carbackground.jpg", 600, 400, false, false);
    private ImageView view = new ImageView();
    private TabPane tabPane = new TabPane();
    private GuiGrid guiGrid;
    private Simulator sim;
    private Label board = new Label();
    private Tab tab1 = new Tab("LeaderBoard", board);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;

        window.setTitle("Car-Racing");
        window.getIcons().add(new Image("carsideview.jpg"));


        //Scene-One code Here

        firstscene.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        play.setStyle("-fx-background-color: YELLOW");
        play.setTooltip(new Tooltip("Click here to play"));
        view.setImage(img);
        firstscene.getChildren().add(view);
        play.setTranslateX(200);
        play.setTranslateY(140);
        senceone.add(play, 0, 1, 1, 1);
        senceone.setAlignment(Pos.CENTER);
        firstscene.getChildren().addAll(senceone);
        play.setOnAction(e -> window.setScene(scene));

     //Scene-two code Here
        stepBtn.setTooltip(new Tooltip("Click here to Start game"));
        quit.setTooltip(new Tooltip("Click here to Quit"));
        initSimulator(Simulator.generateRace());
        uiPane.add(guiGrid, 0, 0);
        uiPane.add(stepBtn, 0, 1);
        uiPane.add(quit,1,1);
        uiPane.add(tabPane, 1, 0);
        scene.getStylesheets().add("./stylesheet.css");
        tab1.setClosable(false);
        tabPane.getTabs().add(tab1);


        stepBtn.setOnMouseClicked(e -> {

                sim.step();
                guiGrid.update();
                updateLeaderBoard();

        });
        quit.setOnAction(e -> closeGame());

        scene1 = new Scene(firstscene);
        window.setScene(scene1);
        window.show();
    }
    private void updateLeaderBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i=1; i <= sim.getLeaderBoard().size(); i++) {
            BoardEntry entry = sim.getLeaderBoard().get(i-1);
            sb.append(i + ". " + entry.getRacer().getName() + " " + entry.getTime() + " seconds.\n");
        }
        board.setText(sb.toString());

    }
    private void closeGame(){
        JOptionPane result = new JOptionPane();
        int dialogResult = JOptionPane.showConfirmDialog(result, "Are you sure want to Quit?",
                "Quit", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.NO_OPTION)
            ;
        else
            System.exit(0);
    }


    private void initSimulator(Simulator sim) {
        this.sim = sim;
        guiGrid = new GuiGrid(sim.getGrid());
    }
}
