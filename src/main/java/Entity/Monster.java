package Entity;

import Strategy.Strategy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster implements  Entity{

    private int x;
    private int y;

    private int speed;

    Strategy strat;

    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;


    public Monster(int initialX, int initialY, int initialSpeed, Strategy strat){
        x = initialX;
        y = initialY;
        speed = initialSpeed;
        this.strat = strat;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public void setImages(){

    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void draw(Graphics g) {
        //TODO draw monster
    }

    public void move(int facing) {

    }

    public void nextStep() {

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
     *
     * @return la prochaine direction du monstre
     */
    public int nextWay(){
       return strat.nextWay();
    }

}
