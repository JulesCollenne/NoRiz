import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame implements ComponentListener {
    GamePanel gamePanel;
    Graphics g;

    public Window(){
        firstInit();

         setPanels();

        lastInit();
    }

    private void firstInit(){
        this.setTitle("NoRiz");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
    }

    private void setPanels(){
        gamePanel = new GamePanel();
        this.getContentPane().add(gamePanel, null);
    }

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
