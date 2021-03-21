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
    private GridPane uiPane = new GridPane();
    private Button play = new Button("   Play   ");
    private Button stepBtn = new Button(" Start ");
    private Button quit = new Button(" Quit ");
    private Scene scene = new Scene(uiPane);
    private TabPane tabPane = new TabPane();
    private TabPane tabPane2 = new TabPane();
    private GuiGrid guiGrid;
    private Simulator sim;
    private Label board = new Label();
    private Label instruct = new Label();
    private Tab tab1 = new Tab("LeaderBoard", board);
    private Tab tab2 = new Tab("Instructions", instruct);
    private GridPane userSettings = new GridPane();
    private Scene userSettingsScene = new Scene(userSettings);
    private GameCreator gameCreator;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Car-Racing");
        primaryStage.getIcons().add(new Image("carbackground.jpg"));


        //Scene-One code Here
        showIntroScene(primaryStage);

        //Scene-Two code Here
        showUserSettingsScene(primaryStage);

        //Scene-Three code Here
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
        play.setOnAction(e -> window.setScene(userSettingsScene));
        window.setScene(new Scene(root));
        window.show();
    }

    private void showUserSettingsScene(Stage window){
        gameCreator = new GameCreator();
        userSettings.add(gameCreator, 0, 0);
        Button playButton = new Button("Play game with these settings");
        playButton.setOnMouseClicked(event -> {
            window.setScene(scene);
        });

        Button grassButton = new Button("Add Grass");
        Button carButton = new Button("Add Car");
        userSettings.add(grassButton, 0, 1);
        userSettings.add(carButton, 0, 1);
        carButton.setTranslateX(100);
        userSettings.add(playButton, 0, 1);
        playButton.setTranslateX(200);

        userSettingsScene.getStylesheets().add("./stylesheet.css");
        tab2.setClosable(false);
        tabPane2.getTabs().add(tab2);
        userSettings.add(tabPane2, 1, 0);
        instruct.setWrapText(true);
        instruct.setMaxWidth(100);
        instruct.setMinHeight(40);
        instruct.setText(gameCreator.toString());
    }

}
