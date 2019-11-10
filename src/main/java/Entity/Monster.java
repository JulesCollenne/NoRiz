package Entity;

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

    private final int DOWN = 0;
    private final int UP = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    private int x;
    private int y;

    private int facing = 0;
    public int nextFacing = -1;

    private int speed;
    private boolean hasBonus;

    private GameStateManager gsm;

    private int nbImgAnim = 2;
    private Image[][] image = new Image[4][nbImgAnim];

    private int animTime;
    private int lastAnim;
    private final int animSpeed = 10;

    private int timerStrat = 0;
    private int timeNextStrat = 10;

    private Strategy strat;


    public Monster(int initialX, int initialY, int initialSpeed, Strategy strat, GameStateManager gsm, String name){
        x = initialX;
        y = initialY;
        speed = initialSpeed;
        this.strat = strat;
        this.gsm = gsm;
        this.name = name;
    }

    public int getCollideX(){
        if(facing == RIGHT)
            return x + getSize();
        return x;
    }

    public int getCollideY(){
        if(facing == DOWN)
            return y + getSize();
        return y;
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
            DIRECTION tmp = strat.nextWay();

            switch (tmp) {
                case DOWN:
                    setNextFacing(DOWN);
                    break;
                case UP:
                    setNextFacing(UP);
                    break;
                case LEFT:
                    setNextFacing(LEFT);
                    break;
                case RIGHT:
                    setNextFacing(RIGHT);
                    break;
            }
            //facing = strat.nextWay();

            //System.out.println(name + " " + facing);
        }

        if (nextFacingPossible(nextFacing)) {
            facing = nextFacing;
            nextFacing = -1;
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
        if(!gsm.collider.isPossible(getCollideX() + dx, getCollideY() + dy))
            return;
        x += dx;
        y += dy;
    }

    public int getSize() {
        return Utils.caseDimension;
    }

    /**
     *
     * @return la prochaine direction du monstre
     */
    public DIRECTION nextWay(){
        return strat.nextWay();
    }

    /**
     * change the direction of the player
     * @param newFacing the new facing
     */
    private void setNextFacing(int newFacing){
        if(nextFacingPossible(newFacing))
            facing = newFacing;
        else
            nextFacing = newFacing;
    }

    private boolean nextFacingPossible(int nextFacing) {
        if(nextFacing == -1)
            return false;
        if(nextFacing == facing) {
            this.nextFacing = -1;
            return false;
        }

        int tmp = facing;
        facing = nextFacing;

        int[] coords = getCollideCoords();

        if(!gsm.collider.isPossible(coords[0] + getFacingX(facing),coords[1] + getFacingY(facing),coords[2] + getFacingX(facing),coords[3] + getFacingY(facing))){
            facing = tmp;
            return false;
        }
        return true;
    }


    /**
     * ..................COORDINATES AHDEAD
     */

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int getFacingX(int facing){
        if(facing == UP || facing == DOWN)
            return 0;
        if(facing == RIGHT)
            return getSize()/2;
        return -getSize()/2;
    }

    private int getFacingY(int facing) {
        if(facing == RIGHT || facing == LEFT)
            return 0;
        if(facing == DOWN)
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

    public int getCollideX1(){
        if(facing == RIGHT)
            return x + getSize();
        if(facing == LEFT)
            return x;
        return x + 1;
    }

    public int getCollideX2(){
        if(facing == RIGHT)
            return x + getSize();
        if(facing == LEFT)
            return x;
        return x + getSize() - 1;
    }

    public int getCollideY1(){
        if(facing == DOWN)
            return y + getSize();
        if(facing == UP)
            return y;
        return y + 1;
    }

    public int getCollideY2(){
        if(facing == DOWN)
            return y + getSize();
        if(facing == UP)
            return y;
        return y + getSize() - 1;
    }

}