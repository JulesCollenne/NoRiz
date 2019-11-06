package Entity;

import States.GameStateManager;

import java.awt.*;
import java.util.Vector;

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

    private int facing;

    private int speed;
    private boolean hasBonus;

    GameStateManager gsm;

    public Player(GameStateManager gsm, int initialX, int initialY, int initialSpeed){
        this.gsm = gsm;
        x = initialX;
        y = initialY;
        speed = initialSpeed;
        hasBonus = false;
    }


    public void move(int dx, int dy) {
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

    public void move(int facing) {

    }

    public void nextStep() {
        switch(facing){
            case DOWN:
                move(0, speed);
                break;
            case UP:
                move(0, -speed);
                break;
            case LEFT:
                move(-speed, 0);
                break;
            case RIGHT:
                move(speed, 0);
                break;
        }
    }
}
