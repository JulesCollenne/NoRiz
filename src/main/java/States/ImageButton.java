package States;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Button {
    ImageView image;
    String label;

    public ImageButton(Image image, String label){
        this.image = new ImageView(image);
        this.getChildren().add(this.image);
        this.label = label;
        super.setGraphic(this.image);
    }

    public void handler(MouseEvent m){

    }
}
