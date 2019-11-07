package UI;

import Collider.Collider;
import Input.KeysManager;
import Input.MouseManager;
import States.GameState;
import States.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    private double updateTime = 10000000;
    private double lastUpdate = 0;

    private boolean running;

    public static int width;
    public static int height;

    private GameStateManager gsm;
    private BufferedImage image;
    private Graphics2D g;

    public KeysManager key;
    public MouseManager mouse;


    public GamePanel(int width, int height){
        GamePanel.width = width;
        GamePanel.height = height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();

        initialize();

        Thread thread = new Thread(this, "GameThread");
        thread.start();
    }

    public void run() {
        Collider collider = gsm.collider;

        while(running){

            waitUpdate();

            gsm.input(key, mouse);
            gsm.nextStep();

            repaint();
            initInput();
        }
    }

    private void waitUpdate(){
        double now;
        now = System.nanoTime();
        while(now - lastUpdate < updateTime){
            now = System.nanoTime();
        }

        lastUpdate = now;
    }

    private void initInput() {
        key.initKeys();
    }

    public void initialize(){
        running = true;
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) image.getGraphics();
        gsm = new GameStateManager(this);

        key = new KeysManager(this);
        mouse = new MouseManager(this);
    }

    public void paintComponent(Graphics g){
        if(gsm == null)
            return;
        gsm.draw(g);
    }
}
