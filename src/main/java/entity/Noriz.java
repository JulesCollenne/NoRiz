package entity;

import collider.Collider;
import utils.DIRECTION;
import utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The player is the sashimi Nori
 */
public class Noriz extends Entity {

    private int invulnerable;
    private int reversed;
    private int isSmall;
    private int timeGhosted;
    private String skin = "nori";
    public boolean leaveWall;
    private int[] closestRoad = new int[2];

    /**
     * Constructor
     */
    public Noriz(Collider collider, int initialX, int initialY, int initialSpeed){
        super(collider, initialX, initialY);

        speed = initialSpeed;
        invulnerable = 0;
        frozen = 0;
        reversed = 0;
        isSmall = 0;

        nbImgAnim = 2;
        animSpeed = 10;
        image = new Image[4][nbImgAnim];

        setImages();

        init();
    }

    /**
     * Initialise les variables. Used when the game starts
     */
    public void init(){
        x = spawnX;
        y = spawnY;

        animTime = 0;
        lastAnim = 0;
        invulnerable = 0;
        leaveWall = false;
        nextFacing = DIRECTION.STOP;
        facing = DIRECTION.STOP;
    }


    /**
     * Compute the next position
     */
    public void nextStep() {
        /*sous l'effet du malus freeze, le joueur ne bouge pas pendant un laps de temps*/
        if(frozen > 0){
            setNextFacing(DIRECTION.STOP);
            frozen --;
        }
        /*Si le joueur prend le malus reverse, inverse les commandes de control du perso*/
        if(reversed > 0){
            reversed --;
        }

        if(isSmall > 0){
            isSmall --;
            if(isSmall == 0) {
                setSize(this.getSize() * 2);
                speed--;
                leaveWall = true;
            }
        }

        if (invulnerable > 0) {
            invulnerable--;
        }

        if(timeGhosted > 0) {
            ghost = 1;
            timeGhosted--;
            if(timeGhosted == 0) {
                leaveWall = true;
                ghost = 0;
            }
        }

        if(leaveWall){
            int coords[][] = getCoords();
            int squareToGo[];
            int nextX = 0, nextY = 0;
            if(collider.collide(coords)) {
                squareToGo = Utils.getCanvasCoords(collider.closestRoad(getCenterX(), getCenterY()));
                if (squareToGo[0] > getCenterX())
                    nextX = speed;
                if (squareToGo[0] < getCenterX())
                    nextX = -speed;
                if (squareToGo[1] > getCenterY())
                    nextY = speed;
                if (squareToGo[1] < getCenterY())
                    nextY = -speed;

                move(nextX, nextY);
                return;
            }
            else
                leaveWall = false;
        }

        if (nextFacingPossible(nextFacing)) {
            facing = nextFacing;
            nextFacing = DIRECTION.STOP;
        }
        switch (facing) {
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
     * Affiche le player
     * @param gc le contexte graphique
     */
    public void render(GraphicsContext gc)
    {

        gc.drawImage( image[Utils.toInt(facing)][animTime], x, y , size, size);

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
        makeAnimations(Utils.toInt(DIRECTION.LEFT),"_gauche","");
        makeAnimations(Utils.toInt(DIRECTION.RIGHT),"_droite","");
        makeAnimations(Utils.toInt(DIRECTION.DOWN),"_gauche", "");
        makeAnimations(Utils.toInt(DIRECTION.UP),"_gauche", "");
    }

    /**
     * Puts the images at the right place in the variable "image"
     */
    public void makeAnimations(int direction, String name, String path){
        for(int i = 0; i < nbImgAnim; i++)
            image[direction][i] = new Image(path+ getSkin() + name + i + ".png");
    }

    public int getInvulnerable(){
        return invulnerable;
    }
    public int getReversed(){
        return reversed;
    }

    public String getSkin(){return skin;}
    public void setInvulnerable(int invulnerable){this.invulnerable = invulnerable;}
    public void setReversed(int reversed){this.reversed = reversed;}
    public void setSkin(String skin){this.skin = skin;}
    public void setIsSmall(int isSmall){this.isSmall = isSmall;}
    public void setTimeGhosted(int timeGhosted){this.timeGhosted = timeGhosted;}
}