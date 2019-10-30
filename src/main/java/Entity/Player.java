package Entity;

import java.awt.*;

/**
 * Player is the sashimi
 */
public class Player implements Entity {

    private int x;
    private int y;

    private int speed;

    private boolean hasBonus;

    public Player(int initialX, int initialY, int initialSpeed){
        x = initialX;
        y = initialY;
        speed = initialSpeed;
        hasBonus = false;
    }


    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void draw(Graphics g) {
        //TODO draw sashimi
    }
}
