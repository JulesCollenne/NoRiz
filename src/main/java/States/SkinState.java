package States;

import Utils.Utils;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class SkinState extends GameState {

    public SkinState(GameStateManager gsm) {
        super(gsm);
        createScene();
        createAnimTimer();
    }

    public void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }


    private void createScene() {
        Pane layout = new Pane();
        Color color = Color.WHITE;
        layout.setStyle("-fx-background-color: darkslategrey;");

        double tempWidth = new Image("Buttons/sign_return_menu.png").getWidth();

        Text title = new Text("Skin");
        title.setX(Utils.canvasSize/2.0- 75);
        title.setY(90);
        title.setFont(new Font(45));
        title.setFill(color);
        title.setStyle("-fx-font-weight: bold");

        FlowPane container = new FlowPane();

        container.setLayoutX(100);
        container.setLayoutY(150);
        container.setPrefSize(600,400);
        container.setStyle("-fx-background-color: rgba(95, 158, 160, 0.3);");

        ScrollPane skinsPane = new ScrollPane();
        skinsPane.setContent(container);
        skinsPane.setPannable(true);

        Button valideSkin = new Button("Valider");
        valideSkin.setStyle("-fx-background-color: rgba(95, 158, 160, 0.3);" +
                " -fx-font-size: 15px;" +
                " -fx-text-fill: black;" +
                " -fx-font-weight: bold;");

        valideSkin.setPrefSize(175,80);
        valideSkin.setLayoutX( ((((2*Utils.canvasSize)/3.) + (Utils.canvasSize/3.))/2) - (tempWidth/2));
        valideSkin.setLayoutY((80/100.0)*Utils.canvasSize);





        layout.getChildren().addAll(title, container, valideSkin);

        theScene = new Scene(layout, Utils.canvasSize, Utils.canvasSize);

        theScene.setOnKeyPressed(
                this::keyInput);
    }

    @Override
    public void nextStep() {

    }

    @Override
    public void keyInput(KeyEvent e) {
        switch (e.getCode()) {
            case ESCAPE:
                gsm.changeState(0);
                break;
        }
    }

    @Override
    public void render(GraphicsContext gc) {

    }

    @Override
    public void init() {

    }
}
