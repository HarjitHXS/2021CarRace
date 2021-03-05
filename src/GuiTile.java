import javafx.scene.control.Label;

public class GuiTile extends Label {

    public GuiTile(types type) {
        super("");
        setPrefSize(50, 50); //TODO: re-think the sizing of our app (We don't need to dynamically change.)
        if (type==types.CAR)
            this.getStyleClass().add("car");
        if (type==types.STREET)
            this.getStyleClass().add("street");
    }
}
