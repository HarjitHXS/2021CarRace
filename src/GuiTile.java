import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuiTile extends Label {

    public GuiTile(types type) {
        super("");
        setPrefSize(100, 100); //TODO: re-think the sizing of our app (We don't need to dynamically change.)
        if (type==types.CAR) {
            this.getStyleClass().add("car");
            Image img = new Image("car.jpg", 50, 50, true, false);
            setGraphic(new ImageView(img));
        }
        if (type==types.STREET)
            this.getStyleClass().add("street");
    }
}
