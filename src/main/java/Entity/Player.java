package Entity;

import Collider.Collider;
import States.GameStateManager;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import Utils.DIRECTION;

/**
 * Player is the sashimi
 */
public class Player implements Entity {

    private int spawnX;
    private int spawnY;
    private int x;
    private int y;

    private DIRECTION facing = DIRECTION.STOP;
    private DIRECTION nextFacing = DIRECTION.STOP;

    private int speed;
    private int invulnerable;

    private Collider collider;

    private int nbImgAnim = 2;
    private Image[][] image = new Image[4][nbImgAnim];

    private int animTime;
    private int lastAnim;
    private final int animSpeed = 10;

    public Player(Collider collider, int initialX, int initialY, int initialSpeed){
        this.collider = collider;
        spawnX = initialX;
        spawnY = initialY;
        speed = initialSpeed;
        invulnerable = 0;

        setImages();

        init();
    }

    /**
     * Initialise les variables
     */
    public void init(){
        x = spawnX;
        y = spawnY;
        animTime = 0;
        lastAnim = 0;
        invulnerable = 0;
        nextFacing = DIRECTION.STOP;
        facing = DIRECTION.STOP;
    }


    /**
     * Compute the next position
     */
    public void nextStep() {
        if(invulnerable > 0){
            invulnerable --;
        }
        if(nextFacingPossible(nextFacing)) {
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
    }

    /**
     * move if he can, do nothing otherwise
     * @param dx next x
     * @param dy next y
     *
     */
    private void tryMove(int dx, int dy) {
        int[] coords = getCollideCoords();
        if(!collider.isPossible(coords[0] + dx, coords[1] + dy, coords[2] + dx, coords[3] + dy))
            return;
        x += dx;
        y += dy;
    }

    /*
     * .....................INPUTS
     */

    /**
     * change the direction of the player
     * @param newFacing the new facing
     */
    public void setNextFacing(DIRECTION newFacing){
        if(nextFacingPossible(newFacing)) {
            facing = newFacing;
            nextFacing = DIRECTION.STOP;
        }
        else
            nextFacing = newFacing;
    }

    private boolean nextFacingPossible(DIRECTION nextFacing) {
        if(nextFacing == DIRECTION.STOP)
            return false;
        if(nextFacing == facing) {
            this.nextFacing = DIRECTION.STOP;
            return false;
        }

        DIRECTION tmp = facing;
        facing = nextFacing;

        int[] coords = getCollideCoords();

        if(!collider.isPossible(coords[0] + getFacingX(facing), coords[1] + getFacingY(facing), coords[2] + getFacingX(facing), coords[3] + getFacingY(facing))){
            facing = tmp;
            return false;
        }
        return true;
    }


    /*
     * ...................VISUALS AHEAD
     */

    /**
     * Affiche le player
     * @param gc le contexte graphique
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
     * Met en places les images permettant les animations
     */
    public void setImages(){
        makeAnimations(Utils.toInt(DIRECTION.LEFT),"gauche");
        makeAnimations(Utils.toInt(DIRECTION.RIGHT),"droite");
        makeAnimations(Utils.toInt(DIRECTION.DOWN),"gauche");
        makeAnimations(Utils.toInt(DIRECTION.UP),"gauche");
    }


    /**
     * Quand un ennemi nous touche
     */
    public void resetPosition(){
        x = spawnX;
        y = spawnY;
    }

    public void makeAnimations(int direction, String name){
        for(int i = 0; i < nbImgAnim; i++)
            image[direction][i] = new Image("Player/nori_" + name + i + ".png");
    }


    /**
     * ..................COORDINATES AHEAD
     */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int getFacingX(DIRECTION facing){
        if(facing == DIRECTION.UP || facing == DIRECTION.DOWN)
            return 0;
        if(facing == DIRECTION.RIGHT)
            return getSize()/2;
        return -getSize()/2;
    }

    private int getFacingY(DIRECTION facing) {
        if(facing == DIRECTION.RIGHT || facing == DIRECTION.LEFT)
            return 0;
        if(facing == DIRECTION.DOWN)
            return getSize()/2;
        return -getSize()/2;
    }

    public int getCenterX() {
        return x + getSize()/2;
    }

    public int getCenterY() {
        return y + getSize()/2;
    }

    private int[] getCollideCoords(){
        int[] coords = new int[4];

        coords[0] = getCollideX1();
        coords[1] = getCollideY1();
        coords[2] = getCollideX2();
        coords[3] = getCollideY2();

        return coords;
    }

    private int getCollideX1(){
        if(facing == DIRECTION.RIGHT)
            return x + getSize();
        if(facing == DIRECTION.LEFT)
            return x;
        return x + 1;
    }

    private int getCollideX2(){
        if(facing == DIRECTION.RIGHT)
            return x + getSize();
        if(facing == DIRECTION.LEFT)
            return x;
        return x + getSize() - 1;
    }

    private int getCollideY1(){
        if(facing == DIRECTION.DOWN)
            return y + getSize();
        if(facing == DIRECTION.UP)
            return y;
        return y + 1;
    }

    private int getCollideY2(){
        if(facing == DIRECTION.DOWN)
            return y + getSize();
        if(facing == DIRECTION.UP)
            return y;
        return y + getSize() - 1;
    }

    public int getSize() {
        return Utils.caseDimension;
    }

    public int getInvulnerable(){
        return invulnerable;
    }
    public void setInvulnerable(int invulnerable){this.invulnerable = invulnerable;}

}