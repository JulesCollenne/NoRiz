package Entity;

import Input.KeysManager;
import States.GameStateManager;
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

    private int x;
    private int y;

    private int facing = 0;
    public int nextFacing = -1;

    private int speed;
    private boolean hasBonus;

    private GameStateManager gsm;

    private Image[][] image = new Image[4][2];

    private int animTime;
    private int lastAnim;
    private final int animSpeed = 10;

    public Player(GameStateManager gsm, int initialX, int initialY, int initialSpeed){
        this.gsm = gsm;
        x = initialX;
        y = initialY;
        speed = initialSpeed;
        hasBonus = false;

        setImages();
        animTime = 0;
        lastAnim = 0;
    }

    /**
     * Met en places les images permettant les animations
     */
    public void setImages(){
        image[LEFT][0] = new Image("nori_droite.png");
        image[LEFT][1] = new Image("nori_droite2.png");

        image[RIGHT][0] = new Image("nori_gauche.png");
        image[RIGHT][1] = new Image("nori_gauche2.png");

        image[UP][0] = new Image("nori_gauche.png");
        image[UP][1] = new Image("nori_gauche2.png");

        image[DOWN][0] = new Image("nori_gauche.png");
        image[DOWN][1] = new Image("nori_gauche2.png");
    }

    /**
     * Affiche le player
     * @param gc le contexte graphique
     */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image[facing][animTime], x, y , 50, 50);

        if(lastAnim == animSpeed) {
            animTime = (animTime + 1) % 2;
            lastAnim = 0;
        }
        lastAnim++;
    }

    /**
     * Gere les entrées
     * @param key the pressed keys
     */
    public void input(KeysManager key){
        if(key.keys[key.KEY_Q])
            gsm.player.changeFacing(2);
        if(key.keys[key.KEY_D])
            gsm.player.changeFacing(3);
        if(key.keys[key.KEY_S])
            gsm.player.changeFacing(0);
        if(key.keys[key.KEY_Z])
            gsm.player.changeFacing(1);
    }

    /**
     * move if he can, do nothing otherwise
     * @param dx next x
     * @param dy next y
     */
    private void tryMove(int dx, int dy) {
        if(!gsm.collider.isPossible(x + dx, y + dy))
            return;
        x += dx;
        y += dy;
    }

    /**
     * Compute the next position
     */
    public void nextStep() {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return 25;
    }

    /**
     * change the direction of the player
     * @param newFacing the new facing
     */
    private void changeFacing(int newFacing){
        facing = newFacing;
    }

    public void input(KeyEvent e) {
        switch (e.getCode()) {
            case Q:
                changeFacing(2);
                break;
            case D:
                changeFacing(3);
                break;
            case S:
                changeFacing(0);
                break;
            case Z:
                changeFacing(1);
                break;
        }
    }
}
