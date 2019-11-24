package Entity;

import Collider.Collider;
import States.GameStateManager;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import Utils.DIRECTION;
import javafx.scene.paint.Color;

import static Utils.Utils.caseDimension;

/**
 * The player is the sashimi Nori
 */
public class Player extends Entity {

    private int invulnerable;
    private int reversed;

    /**
     * Constructor
     */
    public Player(Collider collider, int initialX, int initialY, int initialSpeed){
        super(collider, initialX, initialY);

        speed = initialSpeed;
        invulnerable = 0;
        frozen = 0;
        reversed = 0;


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
        nextFacing = DIRECTION.STOP;
        facing = DIRECTION.STOP;
    }


    /**
     * Compute the next position
     */
    public void nextStep() {
        /**sous l'effet du malus freeze, le joueur ne bouge pas pendant un laps de temps*/
        if(frozen > 0){
            setNextFacing(DIRECTION.STOP);
            frozen --;
        }
        /**Si le joueur prend le malus reverse, inverse les commandes de control du perso*/
        if(reversed > 0){
            if (nextFacingPossible(nextFacing)) {
                facing = nextFacing;
                nextFacing = DIRECTION.STOP;
            }
            switch (facing) {
                case DOWN:
                    tryMoveR(0, -speed);
                    break;
                case UP:
                    tryMoveR(0, speed);
                    break;
                case LEFT:
                    tryMoveR(speed, 0);
                    break;
                case RIGHT:
                    tryMoveR(-speed, 0);
                    break;
            }
            reversed--;
        }
        else {
            if (invulnerable > 0) {
                invulnerable--;
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
    }

    /**
     * Affiche le player
     * @param gc le contexte graphique
     */
    public void render(GraphicsContext gc)
    {

        gc.drawImage( image[Utils.toInt(facing)][animTime], x, y , Utils.caseDimension, Utils.caseDimension);

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
     * Puts the images at the right place in the variable "image"
     */
    public void makeAnimations(int direction, String name){
        for(int i = 0; i < nbImgAnim; i++)
            image[direction][i] = new Image("Player/nori_" + name + i + ".png");
    }


    public int getSize() {
        return Utils.caseDimension;
    }
    public int getInvulnerable(){
        return invulnerable;
    }

    public void setInvulnerable(int invulnerable){this.invulnerable = invulnerable;}
    public void setReversed(int reversed){this.reversed = reversed;}

    private int getCollideX1Reverse(){
        if(facing == DIRECTION.RIGHT)
            return x + 1;
        if(facing == DIRECTION.LEFT)
            return x + getSize() - 1;
        return x + 1;
    }

    private int getCollideX2Reverse(){
        if(facing == DIRECTION.RIGHT)
            return x + 1; //
        if(facing == DIRECTION.LEFT)
            return x  + getSize() - 1;
        return x + getSize() - 1;
    }

    private int getCollideY1Reverse(){
        if(facing == DIRECTION.DOWN)
            return y+1; ///
        if(facing == DIRECTION.UP)
            return y + getSize() -1; ///
        return y + 1;
    }

    private int getCollideY2Reverse(){
        if(facing == DIRECTION.DOWN)
            return y+1; ///
        if(facing == DIRECTION.UP)
            return y + getSize()-1; ///
        return y + getSize() -1;
    }

    void tryMoveR(int dx, int dy) {
        int[] coords = getCollideCoordsR();
        if(!collider.isPossible(coords[0]+dx, coords[1]+dy, coords[2]+dx, coords[3]+dy)){
            return;
        }

        x += dx;
        y += dy;
    }
    public int[] getCollideCoordsR(){
        int[] coords = new int[4];

        coords[0] = getCollideX1Reverse();
        coords[1] = getCollideY1Reverse();
        coords[2] = getCollideX2Reverse();
        coords[3] = getCollideY2Reverse();

        return coords;
    }


}