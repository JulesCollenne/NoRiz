package Entity;

import java.awt.*;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster implements  Entity{

    private int x;
    private int y;

    private int speed;

    public Monster(int initialX, int initialY, int initialSpeed){
        x = initialX;
        y = initialY;
        speed = initialSpeed;
    }


    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void draw(Graphics g) {
        //TODO draw monster
    }
}
