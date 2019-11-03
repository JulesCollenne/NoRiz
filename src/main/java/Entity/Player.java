package Entity;

import java.awt.*;

/**
 * Player is the sashimi
 */
public class Player implements Entity {

    private int x;
    private int y;

    /*
        facing : 0=R, 1=D, 2=L, 3=U
     */
    private int facing;

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
        g.setColor(Color.decode("#00FF00"));
        g.fillArc(x, y, 50, 50, 45, 270);
    }

    public void move(int facing) {

    }
}
