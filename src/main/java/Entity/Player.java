package Entity;

import States.GameOverState;
import States.GameStateManager;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

/**
 * Player is the sashimi
 */
public class Player implements Entity {

    private final int DOWN = 0;
    private final int UP = 1;
    private final int LEFT = 2;
    private final int RIGHT = 3;

    public int maxLife = 5;
    public int nbLife = maxLife;
    private int x;
    private int y;
    private int spawnX;
    private int spawnY;

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

    public Player(GameStateManager gsm, int initialX, int initialY, int initialSpeed){
        this.gsm = gsm;
        spawnX = initialX;
        spawnY = initialY;
        speed = initialSpeed;
        hasBonus = false;

        setImages();

        init();
    }

    public void init(){
        x = spawnX;
        y = spawnY;
        animTime = 0;
        lastAnim = 0;
        hasBonus = false;
        nbLife = maxLife;
        nextFacing = -1;
        facing = 0;
    }

    public int getSize() {
        return Utils.caseDimension;
    }

    /**
     * Quand un ennemi nous touche
     * TODO faire réapparaitre le joueur à un autre endroit
     */
    public void gotHit(){
        System.out.println("Aie");
        nbLife--;
        if(nbLife == 0)
            die();

        //TODO a changer car c'est malpropre de faire ça
        gsm.player.x = Utils.caseDimension;
        gsm.player.y = Utils.caseDimension;
    }

    /**
     * Quand le joueur n'a plus de vie, c'est le game over
     */
    private void die(){
        gsm.changeState(3);
    }

    /**
     * Compute the next position
     */
    public void nextStep() {
        if(nextFacingPossible(nextFacing)) {
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
    }

    /**
     * move if he can, do nothing otherwise
     * @param dx next x
     * @param dy next y
     *
     */
    private void tryMove(int dx, int dy) {
        int[] coords = getCollideCoords();
        if(!gsm.collider.isPossible(coords[0] + dx, coords[1] + dy, coords[2] + dx, coords[3] + dy))
            return;
        x += dx;
        y += dy;
    }

    /**
     * .....................INPUTS
     */

    /**
     * Gere les entrées
     * @param e the pressed keys
     */
    public void input(KeyEvent e) {
        switch (e.getCode()) {
            case Q:
                setNextFacing(2);
                break;
            case D:
                setNextFacing(3);
                break;
            case S:
                setNextFacing(0);
                break;
            case Z:
                setNextFacing(1);
                break;
        }
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
     * ...................VISUALS AHEAD
     */

    /**
     * Affiche le player
     * @param gc le contexte graphique
     */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image[facing][animTime], x, y , Utils.caseDimension, Utils.caseDimension);

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
        makeAnimations(LEFT,"gauche");
        makeAnimations(RIGHT,"droite");
        makeAnimations(DOWN,"gauche");
        makeAnimations(UP,"gauche");
    }


    private void makeAnimations(int direction, String name){
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
