package Entity;

import Collider.Collider;
import Strategy.Strategy;
import Utils.DIRECTION;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster extends Entity{

    private String name;

    private int timerStrat = 0;
    private int timeNextStrat = 10;

    private Strategy strat;

    public Monster(int initialX, int initialY, int initialSpeed, Strategy strat, String name, Collider collider){
        super(collider, initialX, initialY);

        speed = initialSpeed;
        this.strat = strat;
        this.name = name;

        nbImgAnim = 1;
        image = new Image[4][nbImgAnim];
        animSpeed = 10;
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

        if(lastAnim == animSpeed) {
            animTime = (animTime + 1) % nbImgAnim;
            lastAnim = 0;
        }
        lastAnim++;
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

    public void setImages(){
        makeAnimations(Utils.toInt(DIRECTION.LEFT),"gauche");
        makeAnimations(Utils.toInt(DIRECTION.RIGHT),"droite");
        makeAnimations(Utils.toInt(DIRECTION.DOWN),"gauche");
        makeAnimations(Utils.toInt(DIRECTION.UP),"gauche");
    }

    /**
     * Puts the images at the right place in the variable "image"
     */
    public void makeAnimations(int direction, String name){
        //for(int i = 0; i < nbImgAnim; i++)
          //  image[direction][i] = new Image("monsters/"+ name +"_" + name + i + ".png");

        for(int i = 0; i < nbImgAnim; i++)
            image[direction][i] = new Image("monsters/"+this.name + name + i + ".png");
    }

    public void die() {
        resetPosition();
    }

    public int getSize() {
        return Utils.caseDimension;
    }

    public void setFacing(DIRECTION dir){
        this.facing = dir;
    }
}

