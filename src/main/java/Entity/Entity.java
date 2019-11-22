package Entity;

import Collider.Collider;
import Utils.DIRECTION;
import javafx.scene.canvas.GraphicsContext;

/**
 * Entities are the Player, monsters, rice and bonuses
 */

public abstract class Entity {

    int x;
    int y;
    int spawnX;
    int spawnY;

    DIRECTION facing = DIRECTION.STOP;
    DIRECTION nextFacing = DIRECTION.STOP;

    public int frozen;

    public Collider collider;

    int animTime;
    int lastAnim;

    abstract void nextStep();
    public abstract int getSize();
    abstract void setImages();
    abstract void render(GraphicsContext gc);
    abstract void makeAnimations(int direction, String name);


    public Entity(Collider collider, int initialX, int initialY){
        this.collider = collider;
        spawnX = initialX;
        spawnY = initialY;
        frozen = 0;
    }

    public void resetPosition(){
        x = spawnX;
        y = spawnY;
    }

    public void setSpawn(int x, int y){
        spawnX = x;
        spawnY = y;
    }

    /**
     * move if he can, do nothing otherwise
     * @param dx next x
     * @param dy next y
     *
     */
    void tryMove(int dx, int dy) {
        int[] coords = getCollideCoords();
        if(!collider.isPossible(coords[0] + dx, coords[1] + dy, coords[2] + dx, coords[3] + dy))
            return;
        x += dx;
        y += dy;
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

    public int getFacingX(DIRECTION facing){
        if(facing == DIRECTION.UP || facing == DIRECTION.DOWN)
            return 0;
        if(facing == DIRECTION.RIGHT)
            return getSize()/2;
        return -getSize()/2;
    }

    public int getFacingY(DIRECTION facing) {
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

    boolean nextFacingPossible(DIRECTION nextFacing) {
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

    public DIRECTION getFacing(){
        return facing;
    }

}
