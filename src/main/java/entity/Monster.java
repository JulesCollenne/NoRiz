package entity;

import collider.Collider;
import strategy.Strategy;
import utils.DIRECTION;
import utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import worldBuilder.World;

/**
 * Monsters are the cat trying to eat the player
 */
public class Monster extends Entity{

    private String name;

    private int timerStrat = 0;
    private int timeNextStrat = 10;

    private Strategy strat;

    public Monster(int initialX, int initialY, float initialSpeed, Strategy strat, String name, Collider collider){
        super(collider, initialX, initialY);

        speed = initialSpeed;
        this.strat = strat;
        this.name = name;

        nbImgAnim = 1;
        image = new Image[4][nbImgAnim];
        animSpeed = 10;
        setImages();
        init();
    }

    /**
     * Initialize the variables
     */
    public void init(){
        x = spawnX;
        y = spawnY;
        animTime = 0;
        lastAnim = 0;
    }

    /**
     * Draw the monster on the screen
     * @param gc the graphic context
     */
    public void render(GraphicsContext gc)
    {
        gc.drawImage( image[Utils.toInt(facing)][animTime], x, y , size, size);

        if(lastAnim == animSpeed) {
            animTime = (animTime + 1) % nbImgAnim;
            lastAnim = 0;
        }
        lastAnim++;
    }

    public void nextStep(double deltaTime) {
        int deltaMove = (int) Math.round(deltaTime * speed);
        //if(name.equals("cat_follow_")){
        /* 2 joueurs
        if(true){
            if(frozen > 0){
                setNextFacing(DIRECTION.STOP);
                frozen --;
            }
            if (nextFacingPossible(nextFacing)) {
                facing = nextFacing;
                nextFacing = DIRECTION.STOP;
            }
            switch (facing) {
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
        */
        if (frozen > 0) {
            setNextFacing(DIRECTION.STOP);
            frozen--;
        }
        else {
            if (timerStrat > timeNextStrat) {
                timerStrat = 0;

                switch (strat.nextWay(this)) {
                    case DOWN -> setNextFacing(DIRECTION.DOWN);
                    case UP -> setNextFacing(DIRECTION.UP);
                    case LEFT -> setNextFacing(DIRECTION.LEFT);
                    case RIGHT -> setNextFacing(DIRECTION.RIGHT);
                }
                }
            if (nextFacingPossible(nextFacing)) {
                facing = nextFacing;
                nextFacing = DIRECTION.STOP;
            }

            switch (facing) {
                case DOWN -> tryMove(0, deltaMove);
                case UP -> tryMove(0, -deltaMove);
                case LEFT -> tryMove(-deltaMove, 0);
                case RIGHT -> tryMove(deltaMove, 0);
            }
            timerStrat++;
        }
    }

    public DIRECTION getNextFacing(){
        return nextFacing;
    }

    public void setImages(){
        makeAnimations(Utils.toInt(DIRECTION.LEFT),"gauche", "monsters/");
        makeAnimations(Utils.toInt(DIRECTION.RIGHT),"droite", "monsters/");
        makeAnimations(Utils.toInt(DIRECTION.DOWN),"gauche", "monsters/");
        makeAnimations(Utils.toInt(DIRECTION.UP),"gauche", "monsters/");
    }

    /**
     * Puts the images at the right place in the variable "image"
     */
    public void makeAnimations(int direction, String name, String path){
        //for(int i = 0; i < nbImgAnim; i++)
          //  image[direction][i] = new Image("monsters/"+ name +"_" + name + i + ".png");

        for(int i = 0; i < nbImgAnim; i++)
            image[direction][i] = new Image(World.class.getResource("/"+path+this.name + name + i + ".png").toString());
    }

    public void die() {
        resetPosition();
        frozen = 500;
    }

    public void setFacing(DIRECTION dir){
        this.facing = dir;
    }
}

