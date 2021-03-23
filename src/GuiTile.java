//Written by Saif with edits by Alex

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GuiTile extends Label {

    public GuiTile(types type, String color) {
        super("");
        setPrefSize(60, 60);
        getStyleClass().add("guiTile");
        if (type==types.CAR) {
            this.getStyleClass().add("car");
            if(color.equals("red")) {
                Image img = new Image("RedCar.jpg", 50, 50, false, false);
                setGraphic(new ImageView(img));
            }
            if(color.equals("green")){
                Image img = new Image("GreenCar.jpg", 50, 50, false, false);
                setGraphic(new ImageView(img));
            }
            if(color.equals("blue")){
                Image img = new Image("blue car.jpg", 50, 50, false, false);
                setGraphic(new ImageView(img));
            }
        }
        if (type==types.STREET)
            this.getStyleClass().add("street");
        if( type == types.GRASS){
            this.getStyleClass().add("grass");
        }
        if( type == types.CHECKPOINT)
            this.getStyleClass().add("checkpoint");
    }
}
