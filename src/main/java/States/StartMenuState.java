package States;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class StartMenuState extends GameState{
    StartMenuState(GameStateManager gsm) {
        super(gsm);
    }

    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

        Pane layout = new Pane();

        layout.setStyle("-fx-background-color: darkslategrey;");

        Button easy = new ImageButton(new Image("sign_facile.png"), "Facile");
        easy.setStyle("-fx-background-color: darkslategrey;");
        easy.setLayoutX(165-(230/2));
        easy.setLayoutY(495-(87.5/2));
        easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Mode facile");
            }
        });

        Button medium = new ImageButton(new Image("sign_moyen.png"), "Medium");
        medium.setStyle("-fx-background-color: darkslategrey;");
        medium.setLayoutX(500-(230/2));
        medium.setLayoutY(495-(87.5/2));

        Button hard = new ImageButton(new Image("sign_difficile.png"), "Hard");
        hard.setStyle("-fx-background-color: darkslategrey;");
        hard.setLayoutX(830-(230/2));
        hard.setLayoutY(495-(87.5/2));


        layout.getChildren().addAll(easy, medium, hard);



        Scene scene = new Scene(layout, 1000, 1000);

        gsm.theStage.setScene(scene);
        gsm.theStage.show();

    }
}
