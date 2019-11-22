package Entity;

import Collider.Collider;
import Strategy.Strategy;
import Utils.DIRECTION;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster extends Entity{

    private String name;
    private int speed;

    private int nbImgAnim = 2;
    private Image[][] image = new Image[4][nbImgAnim];

    private final int animSpeed = 10;

    private int timerStrat = 0;
    private int timeNextStrat = 10;

    private Strategy strat;

    public Monster(int initialX, int initialY, int initialSpeed, Strategy strat, String name, Collider collider){
        super(collider, initialX, initialY);

        speed = initialSpeed;
        this.strat = strat;
        this.name = name;

        setImages();
        init();
    }

    /**
     * Initialize the variables
     */
    public void init(){
        x = spawnX;
        y = spawnY;
        animTime = 0;
        lastAnim = 0;
    }

    /**
     * Draw the monster on the screen
     * @param gc the graphic context
     */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image[Utils.toInt(facing)][animTime], x, y , Utils.caseDimension, Utils.caseDimension);

        //System.out.println(x+", "+y);

        if(lastAnim == animSpeed) {
            animTime = (animTime + 1) % 2;
            lastAnim = 0;
        }
        lastAnim++;
    }

    /**
     *
     */
    public void setImages(){
        makeAnimations(Utils.toInt(DIRECTION.LEFT),"gauche");
        makeAnimations(Utils.toInt(DIRECTION.RIGHT),"droite");
        makeAnimations(Utils.toInt(DIRECTION.DOWN),"droite");
        makeAnimations(Utils.toInt(DIRECTION.UP),"gauche");

    }

    public void nextStep() {
        if(frozen > 0){
            setNextFacing(DIRECTION.STOP);
            frozen --;
        }
        else {
            if (timerStrat > timeNextStrat) {
                timerStrat = 0;

                switch (strat.nextWay(this)) {
                    case DOWN:
                        setNextFacing(DIRECTION.DOWN);
                        break;
                    case UP:
                        setNextFacing(DIRECTION.UP);
                        break;
                    case LEFT:
                        setNextFacing(DIRECTION.LEFT);
                        break;
                    case RIGHT:
                        setNextFacing(DIRECTION.RIGHT);
                        break;
                }
            }
            if (nextFacingPossible(nextFacing)) {
                facing = nextFacing;
                nextFacing = DIRECTION.STOP;
            }
            switch(facing){
                case DOWN:
                    tryMove(0, speed);
                    break;
                case UP:
                    tryMove(0, -speed);
                    break;
                case LEFT:
                    tryMove(-speed, 0);
                    break;
                case RIGHT:
                    tryMove(speed, 0);
                    break;
            }
            timerStrat++;
        }
    }

    public void makeAnimations(int direction, String name) {
        for(int i = 0; i < nbImgAnim; i++)
            image[direction][i] = new Image("monsters/catastrophe" + i + ".png");
    }

    public void die() {
        resetPosition();
    }

    public int getSize() {
        return Utils.caseDimension;
    }
}

