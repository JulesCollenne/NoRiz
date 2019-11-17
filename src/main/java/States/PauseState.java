package States;

import Utils.Utils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PauseState extends GameState {
    PauseState(GameStateManager gsm) {
        super(gsm);
    }

    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void initScene() {
        Pane layout = new Pane();

        layout.setStyle("-fx-background-color: darkslategrey;");


        Text title = new Text("Pause");
        title.setX(Utils.canvasSize/2- 75);
        title.setY(Utils.canvasSize/2 - 200);
        title.setFont(new Font(40));
        title.setStyle("-fx-font-weight: bold");

        Text commande = new Text("Commandes");
        commande.setX(Utils.canvasSize/9);
        commande.setY(Utils.canvasSize/2);
        commande.setFont(new Font(20));

        ModeButton rejouer = new ModeButton(new Image("Buttons/sign_rejouer.png"), "Rejouer");
        rejouer.setLayoutX(Utils.canvasSize/2);
        rejouer.setLayoutY(Utils.canvasSize/2);
        rejouer.setMaxSize((20 /100.0)*Utils.canvasSize,(10 /100.0)*Utils.canvasSize);
        rejouer.handler(gsm);


        layout.getChildren().addAll(title, commande, rejouer);


        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);

    }
}

