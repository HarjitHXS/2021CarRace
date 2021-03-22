import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

public class GUI extends Application {
    private GridPane uiPane = new GridPane();
    private Button play = new Button("   Play   ");
    private Button stepBtn = new Button(" Start ");
    private Button quit = new Button(" Quit ");
    private Scene scene = new Scene(uiPane);
    private TabPane tabPane = new TabPane();
    private GuiGrid guiGrid;
    private Simulator sim;
    private Label board = new Label();
    private Label instruct = new Label();
    private Tab tab1 = new Tab("Instructions", instruct);
    private Tab tab2 = new Tab("LeaderBoard", board);
    private GameCreator gameCreator = new GameCreator(10, 10);
    private int milliSeconds = 750;

    //Written by Saif and edited by Alex
    //GUI frontend was created by Harjit
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Car-Racing");
        primaryStage.getIcons().add(new Image("carbackground.jpg"));

        //Scene-One code Here
        showIntroScene(primaryStage);

        //Scene-Two code Here
        stepBtn.setTooltip(new Tooltip("Click here to Start game"));
        quit.setTooltip(new Tooltip("Click here to Quit"));
        initSimulator(Simulator.generateRace(gameCreator.getTilesMap(), gameCreator));

        uiPane.add(gameCreator, 0, 0);
        uiPane.add(stepBtn, 0, 1);
        uiPane.add(quit,1,1);
        uiPane.add(tabPane, 1, 0);
        scene.getStylesheets().add("./stylesheet.css");
        tab1.setClosable(false);
        tab2.setClosable(false);
        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        instructions();

        stepBtn.setOnMouseClicked(e -> {
            if(!stepBtn.getText().equals("Step")) {
                initSimulator(Simulator.generateRace(gameCreator.getTilesMap(), gameCreator));
            }
            stepBtn.setText("Step");
            guiLoop();
        });

        quit.setOnAction(e -> closeGame());
    }

    /** Written by Saif and Alex
     * This loop creates the animation part of the GUI, which is the car's movement.
     */
    private void guiLoop() {
        Timeline animation = new Timeline(new KeyFrame(Duration.millis(milliSeconds), e ->
        {
            sim.step();
            guiGrid.update();
            updateLeaderBoard();
        }));
        animation.setCycleCount(15);
        animation.play();
        /*
        * The animation 'loops' for 15 frames at a time and then checks if the game has concluded with a winner. If
        * there is a winner then the game will alert the user and close the game.
         */
        animation.setOnFinished(e -> {
            if (sim.raceFinished()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(sim.getLeaderBoard().get(0).getRacer().getName() + " has won the race!");
                alert.setTitle("Winner");
                alert.setOnHidden(event -> {
                    System.exit(0);
                });
                alert.show();
            } else {
                animation.setCycleCount(5);
                animation.play();
            }
        });
    }

    //Written by Saif
    private void updateLeaderBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i=1; i <= sim.getLeaderBoard().size(); i++) {
            BoardEntry entry = sim.getLeaderBoard().get(i-1);
            sb.append(i + ". " + entry.getRacer().getName() + " " + entry.getTime() + " seconds.\n");
        }
        board.setText(sb.toString());


    }

    //Written by Harjit
    private void instructions() {
        StringBuilder it = new StringBuilder();

        it.append("User can Add the grass or remove grass as user like by clicking add Grass/add Street " + "\n"
                + "\n" + " Then click on Start for the car race "+ "\n" +
                "\n"+" Tap on LeaderBoard to see the result ");

        instruct.setText(it.toString());
        instruct.setWrapText(true);
        instruct.setPrefSize(170, 200);
        instruct.setFont(Font.font ("Verdana", 12));

    }

    //Written by Saif
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
        guiGrid = gameCreator.getGuiGrid();
    }

    //Written by Saif
    private void showIntroScene(Stage window) {
        GridPane root = new GridPane();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        play.setStyle("-fx-background-color: YELLOW");
        play.setTooltip(new Tooltip("Click here to play"));
        Image img = new Image("carbackground.jpg", 600, 400, true, true);
        ImageView view = new ImageView();
        view.setImage(img);
        root.getChildren().add(view);
        root.add(play, 0, 1);
        root.setAlignment(Pos.CENTER);
        play.setOnAction(e -> window.setScene(scene));
        window.setScene(new Scene(root));
        window.show();
    }

}
