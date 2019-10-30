package Entity;

import java.awt.*;

/**
 * Entities are the Player, monsters, rice and bonuses
 */
public interface Entity {

    void draw(Graphics g);
    void move(int facing);

}
