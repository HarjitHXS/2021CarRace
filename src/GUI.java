import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {
    private GridPane uiPane = new GridPane();
    private Button stepBtn = new Button("Step");
    private Scene scene = new Scene(uiPane);
    private GuiGrid guiGrid;
    private Simulator sim;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initSimulator(Simulator.generateRace());
        uiPane.add(guiGrid, 0, 0);
        uiPane.add(stepBtn, 0, 1);
        scene.getStylesheets().add("./stylesheet.css");

        stepBtn.setOnMouseClicked(e -> {
                sim.step();
                guiGrid.update();
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private void initSimulator(Simulator sim) {
        this.sim = sim;
        guiGrid = new GuiGrid(sim.getGrid());
    }
}
