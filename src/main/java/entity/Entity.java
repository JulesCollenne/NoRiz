package entity;

import collider.Collider;
import utils.DIRECTION;
import utils.Utils;
import javafx.scene.image.Image;
import static utils.Utils.*;

/**
 * Entities are the Player and the monsters
 */

public abstract class Entity {

    int x;
    int y;
    int spawnX;
    int spawnY;
    public float speed;
    float initialSpeed;
    int size;

    DIRECTION facing = DIRECTION.STOP;
    DIRECTION nextFacing = DIRECTION.STOP;

    public int frozen;
    public int ghost;

    public Collider collider;

    int nbImgAnim;
    Image[][] image;
    int animTime;
    int lastAnim;
    int animSpeed;

    abstract void setImages();
    abstract void makeAnimations(int direction, String name, String path);

    /**
     * Constructor
     */
    public Entity(Collider collider, int initialX, int initialY){
        this.collider = collider;
        spawnX = initialX;
        spawnY = initialY;
        frozen = 0;
        ghost = 0;
        size = Utils.caseDimension;
    }

    /**
     * Respawn entity at its spawn coordinates
     */
    public void resetPosition(){
        x = spawnX;
        y = spawnY;
    }

    public void init(){
        frozen = 0;
        ghost = 0;
        size = Utils.caseDimension;
    }

    /**
     * Set the entity's spawn
     */
    public void setSpawn(int x, int y){
        spawnX = x;
        spawnY = y;
    }

    public void setSize(int choosenSize){
        size = choosenSize;
    }

    public int[] findPossibleMove(int dx, int dy) {
        int[] possibleCoords = new int[2];
        int sigx = (int) -Math.signum(dx);
        int sigy = (int) -Math.signum(dy);
        int[][] nextCoords;
        for(int i = dx; i != 0; i+=sigx){
            nextCoords = getNextCoords(i, dy);
            if (!collider.collide(nextCoords)){
                possibleCoords[0] = i;
                possibleCoords[1] = dy;
                return possibleCoords;
            }
        }
        for(int i = dy; i != 0; i+=sigy){
            nextCoords = getNextCoords(dx, i);
            if (!collider.collide(nextCoords)){
                possibleCoords[0] = dx;
                possibleCoords[1] = i;
                return possibleCoords;
            }
        }
        return null;
    }

    /**
     * move if it's possible, do nothing otherwise
     * @param dx next x
     * @param dy next y
     *
     */
    void tryMove(int dx, int dy) {
        int[][] nextCoords = getNextCoords(dx, dy);
        int[] newDeltas;
        if(collider.collide(nextCoords) && ghost == 0) {
            newDeltas = findPossibleMove(dx, dy);
            if (newDeltas == null)
                return;
            move(newDeltas[0], newDeltas[1]);
            return;
        }
        move(dx, dy);
    }

    void move(int dx, int dy){
        x = (x + dx) % canvasSize;
        y = y + dy;
        if(y >= canvasSize)
            y = caseDimension * UISize;

        if(x < 0)
            x = canvasSize-1;
        if(y < UISize*caseDimension)
            y = canvasSize-1;
    }

    /**
     * Change the direction of the entity
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

    /**
     * Check if the next facing is possible
     * @return true if yes, false if no
     */
    boolean nextFacingPossible(DIRECTION nextFacing) {
        if(nextFacing == DIRECTION.STOP)
            return false;
        if(nextFacing == facing) {
            this.nextFacing = DIRECTION.STOP;
            return false;
        }

        return (!collider.collide(getNextCoords(getFacingX(nextFacing), getFacingY(nextFacing))) || ghost > 0);
    }

    /*
     * ..................COORDINATES AHEAD
     */

    /**
     * Coordinates on the pane
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

    /**
     * DEPRECATED
     * The hitbox of the entity is a line : the side of its box that it's facing
     * @return the coordinates of the line
     */
    public int[] getCollideCoords(){
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
     * Get the square hitbox of the entity
     * @return the hitbox
     */
    public int[][] getCoords(){
        int[][] coords = new int[4][2];

        //Haut gauche
        coords[0][0] = x+1;
        coords[0][1] = y+1;

        //Haut droit
        coords[1][0] = x + getSize()-1;
        coords[1][1] = y+1;

        //Bas gauche
        coords[2][0] = x+1;
        coords[2][1] = y + getSize()-1;

        //Bas droit
        coords[3][0] = x + getSize()-1;
        coords[3][1] = y + getSize()-1;

        return coords;
    }

    private int[][] getNextCoords(int dx, int dy){
        int[][] coords = getCoords();

        for(int i=0; i < coords.length; i++) {
            coords[i][0] += dx;
            coords[i][1] += dy;
        }

        return coords;
    }


    public DIRECTION getFacing(){
        return facing;
    }
    public int getSize(){
        return size;
    }

}
