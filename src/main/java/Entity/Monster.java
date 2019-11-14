package Entity;

import Collider.Collider;
import States.GameStateManager;
import Strategy.Strategy;
import Utils.DIRECTION;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster implements  Entity{

    private String name;

    private int x;
    private int y;
    private int spawnX;
    private int spawnY;

    private DIRECTION facing = DIRECTION.STOP;
    private DIRECTION nextFacing = DIRECTION.STOP;

    private int speed;

    private int nbImgAnim = 2;
    private Image[][] image = new Image[4][nbImgAnim];

    private int animTime;
    private int lastAnim;
    private final int animSpeed = 10;

    private int timerStrat = 0;
    private int timeNextStrat = 10;

    private Strategy strat;
    public Collider collider;


    public Monster(int initialX, int initialY, int initialSpeed, Strategy strat, String name, Collider collider){
        spawnX = initialX;
        spawnY = initialY;
        speed = initialSpeed;
        this.strat = strat;
        this.name = name;
        this.collider = collider;
        init();
    }

    public void init(){
        x = spawnX;
        y = spawnY;
        animTime = 0;
        lastAnim = 0;
    }

    public void render(GraphicsContext gc)
    {
        //gc.drawImage( image, positionX, positionY );
        gc.setFill(Paint.valueOf("#FF0000"));
        gc.fillRect(x,y,getSize(),getSize());
    }

    public void setImages(){

    }

    public void nextStep() {
        if(timerStrat > timeNextStrat) {
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

            //System.out.println(name + " " + facing);
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

    private void tryMove(int dx, int dy) {
        int[] coords = getCollideCoords();
        if(collider.isImpossible(coords[0] + dx, coords[1] + dy, coords[2] + dx, coords[3] + dy))
            return;
        x += dx;
        y += dy;
    }

    public int getSize() {
        return Utils.caseDimension;
    }

    /**
     * change the direction of the player
     * @param newFacing the new facing
     */
    private void setNextFacing(DIRECTION newFacing){
        if(nextFacingPossible(newFacing))
            facing = newFacing;
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

        if(collider.isImpossible(coords[0] + getFacingX(facing), coords[1] + getFacingY(facing), coords[2] + getFacingX(facing), coords[3] + getFacingY(facing))){
            facing = tmp;
            return false;
        }
        return true;
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

    public DIRECTION getFacing(){
        return facing;
    }

}

