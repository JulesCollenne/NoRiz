package States;

import Utils.Utils;
import Utils.WORLDITEM;
import static Utils.WORLDITEM.*;

import WorldBuilder.worldRender;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EditorState extends GameState {

    private WORLDITEM posingBlock = WALL;
    private WORLDITEM[][] buildingMap;

    EditorState(GameStateManager gsm) {
        super(gsm);
        createScene();
        init();
    }

    private void createScene() {
        Group root = new Group();
        theScene = new Scene( root );
        root.setStyle("-fx-background-color: darkslategrey;");

        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize);

        root.getChildren().addAll(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // game logic
                nextStep();

                // render
                render(gc);

            }
        };

        theScene.setOnKeyPressed(
                this::keyInput);

        theScene.setOnMousePressed(
                this::mouseInput);

        theScene.setOnMouseDragged(this::mouseInput);

    }

    @Override
    public void nextStep() {

    }

    @Override
    public void keyInput(KeyEvent e) {
        switch (e.getCode()) {
            case A:
                setPosingBlock(ROAD);
                break;
            case Z:
                setPosingBlock(WALL);
                break;
            case E:
                setPosingBlock(BONUS);
                break;
            case R:
                setPosingBlock(RICE);
                break;
            case S:
                saveMap();
                break;
            case L:
                loadMap();
                break;
            case ESCAPE:
                gsm.changeState(0);
                break;
        }
    }

    /**
     *
     * @param posingBlock set this block as the current Posing block
     */
    private void setPosingBlock(WORLDITEM posingBlock) {
        this.posingBlock = posingBlock;
    }

    private void mouseInput(MouseEvent e){
        int x = (int) e.getX();
        int y = (int) e.getY();

        int coords[] = Utils.getSquare(x,y);

        if(isCorrect(coords))
            buildingMap[coords[0]][coords[1]] = posingBlock;
    }

    private boolean isCorrect(int[] coords) {
        return coords[1] > 1;
    }

    @Override
    public void render(GraphicsContext gc) {

        worldRender.renderMap(gc, buildingMap);
        Image Header = new Image("UI/headerEditor.png", 800, 64, true, false);
        gc.drawImage(Header, 0, 0);

    }

    @Override
    public void init() {
        posingBlock = WALL;
        buildingMap = gsm.world.makeCleanMap();
    }

    @Override
    public void createAnimTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

            }
        };
    }

    /**
     * Save the current map in a file
     */
    private void saveMap(){
        gsm.world.saveMap(buildingMap);
    }

    /**
     * Load a map from a file to the current map
     */
    private void loadMap(){
        buildingMap = gsm.world.loadMap();
    }
}