package States;

import Utils.Utils;
import Utils.WORLDITEM;
import static Utils.WORLDITEM.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EditorState extends GameState {

    private WORLDITEM posingBlock = WALL;

    EditorState(GameStateManager gsm) {
        super(gsm);
        createScene();
        init();
    }

    private void createScene() {
        Group root = new Group();
        theScene = new Scene( root );
        root.setStyle("-fx-background-color: darkslategrey;");

        root.getChildren().addAll(gsm.canvas);

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
    public void input(KeyEvent e) {

    }

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

    private void setPosingBlock(WORLDITEM posingBlock) {
        this.posingBlock = posingBlock;
    }

    public void mouseInput(MouseEvent e){
        int x = (int) e.getX();
        int y = (int) e.getY();

        int coords[] = Utils.getSquare(x,y);

        gsm.world.map[coords[0]][coords[1]] = posingBlock;
    }

    @Override
    public void render(GraphicsContext gc) {
        gsm.world.renderMap(gc);
    }

    @Override
    public void init() {
        posingBlock = WALL;
        gsm.world.makeCleanMap();
    }

    public void saveMap(){
        gsm.world.saveMap();
    }

    public void loadMap(){
        gsm.world.loadMap();
    }
}
