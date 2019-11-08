package Entity;

import States.GameStateManager;
import Strategy.Strategy;
import Utils.DIRECTION;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster implements  Entity{

    private String name;

    private int x;
    private int y;

    private int speed;
    private DIRECTION facing;

    private Strategy strat;

    private GameStateManager gsm;

    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;


    public Monster(int initialX, int initialY, int initialSpeed, Strategy strat, GameStateManager gsm, String name){
        x = initialX;
        y = initialY;
        speed = initialSpeed;
        this.strat = strat;
        this.gsm = gsm;
        this.name = name;
    }

    public int getCollideX(){
        if(facing == DIRECTION.RIGHT)
            return x + getSize();
        return x;
    }

    public int getCollideY(){
        if(facing == DIRECTION.DOWN)
            return y + getSize();
        return y;
    }

    public void render(GraphicsContext gc)
    {
        //gc.drawImage( image, positionX, positionY );
        gc.setFill(Paint.valueOf("#FF0000"));
        gc.fillRect(x,y,getSize(),getSize());
    }

    public void setImages(){

    }

    public void nextStep() {

        facing = strat.nextWay();

        System.out.println(name + " " + facing);

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

    private void tryMove(int dx, int dy) {
        if(!gsm.collider.isPossible(getCollideX() + dx, getCollideY() + dy))
            return;
        x += dx;
        y += dy;
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
    public DIRECTION nextWay(){
       return strat.nextWay();
    }

}
