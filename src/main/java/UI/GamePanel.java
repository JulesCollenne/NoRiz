package UI;

import Input.KeysManager;
import Input.MouseManager;
import States.GameStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable {

    private boolean running;

    public static int width;
    public static int height;

    private GameStateManager gsm;
    private BufferedImage image;
    private Graphics2D g;

    KeysManager key;
    MouseManager mouse;


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

        while(running){
            //TODO Lancer le jeu
            gsm.input(key, mouse);
            gsm.nextStep();
            gsm.draw(g);
            initInput();
        }
    }

    private void initInput() {
        key.initKeys();
    }

    public void initialize(){
        running = true;
        image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        g = (Graphics2D) image.getGraphics();
        gsm = new GameStateManager();

        key = new KeysManager(this);
        mouse = new MouseManager(this);
    }

    public void paintComponent(Graphics g){
        if(gsm == null)
            return;
        gsm.draw(g);
    }
}
