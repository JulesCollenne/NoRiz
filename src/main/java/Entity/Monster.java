package Entity;

import Strategy.Strategy;

import java.awt.*;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster implements  Entity{

    private int x;
    private int y;

    private int speed;

    Strategy strat;

    public Monster(int initialX, int initialY, int initialSpeed, Strategy strat){
        x = initialX;
        y = initialY;
        speed = initialSpeed;
        this.strat = strat;
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

    /**
     *
     * @return la prochaine direction du monstre
     */
    public int nextWay(){
       return strat.nextWay();
    }

}
