import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame implements ComponentListener {

    private final int WIDTH = 800;
    private final int HEIGHT = 800;

    GamePanel gamePanel;
    Graphics g;

    /**
     * Lors de la création de la Window, on lance le programme
     */
    public Window(){
        firstInit();

        setPanels();

        lastInit();

        run();
    }

    /**
     * Lancement du thread gamePanel
     */
    private void run(){
        Thread thread = new Thread(gamePanel);
        thread.start();
    }

    /**
     * Initialisation de la fenetre
     */
    private void firstInit(){
        this.setTitle("NoRiz");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
    }

    /**
     * Ajout du panneau dans lequel sera dessiné les éléments du jeu
     */
    private void setPanels(){
        gamePanel = new GamePanel(WIDTH, HEIGHT);
        this.getContentPane().add(gamePanel, null);
    }

    /**
     * On affiche le tout
     */
    private void lastInit() {
        this.setVisible(true);
        getContentPane().addComponentListener(this);
    }

    public void componentResized(ComponentEvent componentEvent) {

    }

    public void componentMoved(ComponentEvent componentEvent) {

    }

    public void componentShown(ComponentEvent componentEvent) {

    }

    public void componentHidden(ComponentEvent componentEvent) {

    }
}
