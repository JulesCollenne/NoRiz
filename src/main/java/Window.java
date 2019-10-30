import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Window extends JFrame implements ComponentListener {
    Panel panel = new Panel();
    Graphics g;

    public Window(){
        firstInit();

        lastInit();
    }

    private void lastInit() {
        this.setVisible(true);
    }

    private void firstInit(){
        this.setTitle("NoRiz");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.WHITE);
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
