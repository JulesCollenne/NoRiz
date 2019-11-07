package Entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

/**
 * Entities are the Player, monsters, rice and bonuses
 */
public interface Entity {

    void draw(Graphics g);
    void nextStep();
    int getX();
    int getY();
    int getSize();
    void setImage(Image i);
    void render(GraphicsContext gc);
}
