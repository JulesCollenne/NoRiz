package Entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

/**
 * Entities are the Player, monsters, rice and bonuses
 */
public interface Entity {
    void nextStep();
    int getX();
    int getY();
    int getSize();
    void setImages();
    void render(GraphicsContext gc);
}
