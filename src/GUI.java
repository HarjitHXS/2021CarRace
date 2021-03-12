import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private GuiGrid guiGrid;
    private Simulator sim;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage window = primaryStage;

        window.setTitle("Car-Racing");

        //Scene-One code Here

        firstscene.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        play.setStyle("-fx-background-color: YELLOW");
        ImageView view = new ImageView();
        Image img = new Image("car1.jpg", 600, 400, false, false);
        view.setImage(img);
        firstscene.getChildren().add(view);
        play.setTranslateX(200);
        play.setTranslateY(140);
        senceone.add(play, 0, 1, 1, 1);
        senceone.setAlignment(Pos.CENTER);
        firstscene.getChildren().addAll(senceone);
        play.setOnAction(e -> window.setScene(scene));

     //Scene-two code Here

        initSimulator(Simulator.generateRace());
        uiPane.add(guiGrid, 0, 0);
        uiPane.add(stepBtn, 0, 1);
        uiPane.add(quit,1,1);
        scene.getStylesheets().add("./stylesheet.css");



        stepBtn.setOnMouseClicked(e -> {
                sim.step();
                guiGrid.update();

        });
        quit.setOnAction(e -> closeGame());

        scene1 = new Scene(firstscene);
        window.setScene(scene1);
        window.show();
    }
    private void closeGame(){
        JOptionPane result = new JOptionPane();
        int dialogResult = JOptionPane.showConfirmDialog(result, "Are you sure you to Quit?",
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
