package Entity;

import Input.KeysManager;
import Input.MouseManager;
import States.GameStateManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.awt.*;

/**
 * Player is the sashimi
 */
public class Player implements Entity {

    private int x;
    private int y;

    /*
        facing : 0 = D, 1 = U, 2 = L, 3 = R
     */

    final int DOWN = 0;
    final int UP = 1;
    final int LEFT = 2;
    final int RIGHT = 3;

    private int facing = 0;
    public int nextFacing = -1;

    private int speed;
    private boolean hasBonus;

    GameStateManager gsm;

    private Image[][] image = new Image[4][2];
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;

    private int animTime;
    public int lastAnim;
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

    public void setImages(){
        image[LEFT][0] = new Image("nori_droite.png");
        image[LEFT][1] = new Image("nori_droite2.png");

        image[RIGHT][0] = new Image("nori_gauche.png");
        image[RIGHT][1] = new Image("nori_gauche2.png");
    }

    public void render(GraphicsContext gc)
    {
        System.out.println(animTime);
        gc.drawImage( image[facing][animTime], x, y , 50, 50);

        if(lastAnim == animSpeed) {
            animTime = (animTime + 1) % 2;
            lastAnim = 0;
        }
        lastAnim++;
        //gc.fillRoundRect(x,y,50,50,10,10);
    }

    public void input(KeysManager key, MouseManager mouse){
        if(key.keys[key.KEY_Q])
            gsm.player.changeFacing(2);
        if(key.keys[key.KEY_D])
            gsm.player.changeFacing(3);
        if(key.keys[key.KEY_S])
            gsm.player.changeFacing(0);
        if(key.keys[key.KEY_Z])
            gsm.player.changeFacing(1);
    }

    public void tryMove(int dx, int dy) {
        if(!gsm.collider.isPossible(x + dx, y + dy))
            return;
        x += dx;
        y += dy;
    }

    public void draw(Graphics g) {
        //TODO draw sashimi
        g.setColor(Color.decode("#00FF00"));
        g.fillArc(x, y, 50, 50, 45, 270);
    }

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

    public void changeFacing(int newFacing){
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
