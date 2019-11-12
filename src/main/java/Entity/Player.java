package Entity;

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

    private int maxLife = 5;
    private int nbLife = maxLife;
    private int x;
    private int y;
    private int spawnX;
    private int spawnY;

    private DIRECTION facing = DIRECTION.STOP;
    private DIRECTION nextFacing = DIRECTION.STOP;

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
        nextFacing = DIRECTION.STOP;
        facing = DIRECTION.STOP;
    }

    public int getSize() {
        return Utils.caseDimension;
    }

    /**
     * Quand un ennemi nous touche
     * TODO faire réapparaitre le joueur à un autre endroit
     */
    public void gotHit(){
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

        gsm.collider.takeRice(getCenterX(), getCenterY());

        if(gsm.collider.isImpossible(coords[0] + dx, coords[1] + dy, coords[2] + dx, coords[3] + dy))
            return;
        x += dx;
        y += dy;
    }

    /*
     * .....................INPUTS
     */

    /**
     * Gere les entrées
     * @param e the pressed keys
     */
    public void input(KeyEvent e) {
        switch (e.getCode()) {
            case Q:
                setNextFacing(DIRECTION.LEFT);
                break;
            case D:
                setNextFacing(DIRECTION.RIGHT);
                break;
            case S:
                setNextFacing(DIRECTION.DOWN);
                break;
            case Z:
                setNextFacing(DIRECTION.UP);
                break;
            case A:
                System.out.println("OHHHHH !!! Pourquoi tu appuies sur A, mon vieux ?\n On est pas pote de UN, de DEUX, c'est une sorte de point G pour moi, le A... Alors fais un peu plus gaffe la prochaine fois... ;)");
                break;
        }
    }

    /**
     * change the direction of the player
     * @param newFacing the new facing
     */
    private void setNextFacing(DIRECTION newFacing){
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

        if(gsm.collider.isImpossible(coords[0] + getFacingX(facing), coords[1] + getFacingY(facing), coords[2] + getFacingX(facing), coords[3] + getFacingY(facing))){
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

}