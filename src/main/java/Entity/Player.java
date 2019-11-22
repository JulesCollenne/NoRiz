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
public class Player extends Entity {

    private int speed;
    private int invulnerable;

    private int nbImgAnim = 2;
    private Image[][] image = new Image[4][nbImgAnim];

    private final int animSpeed = 10;

    public Player(Collider collider, int initialX, int initialY, int initialSpeed){
        super(collider, initialX, initialY);

        speed = initialSpeed;
        invulnerable = 0;
        frozen = 0;

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
        if(frozen > 0){
            setNextFacing(DIRECTION.STOP);
            frozen --;
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

}