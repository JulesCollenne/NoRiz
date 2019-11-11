package Entity;

import javafx.scene.canvas.GraphicsContext;

/**
 * Entities are the Player, monsters, rice and bonuses
 */

public interface Entity {
    void nextStep();
    int getX();
    int getY();
    int getCenterX();
    int getCenterY();
    int getSize();
    void setImages();
    void render(GraphicsContext gc);
}
