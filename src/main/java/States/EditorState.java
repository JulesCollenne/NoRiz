package States;

import Utils.Utils;
import Utils.WORLDITEM;
import static Utils.WORLDITEM.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

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
        Canvas canvas = new Canvas(Utils.canvasSize, Utils.canvasSize + (2*Utils.caseDimension));

        root.getChildren().addAll(canvas);

        theScene.setOnKeyPressed(
                this::keyInput);

        theScene.setOnMousePressed(
                this::mouseInput);
        )

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
        gc.setFont(theFont);
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                // game logic
                nextStep();

                // render
                render(gc);

            }
        }.start();

    }

    @Override
    public void nextStep() {

    }

    @Override
    public void input(KeyEvent e) {

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
            case S:
                saveMap();
                break;
            case L:
                loadMap();
                break;
        }
    }

    private void setPosingBlock(WORLDITEM posingBlock) {
        this.posingBlock = posingBlock;
    }

    public void mouseInput(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

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
