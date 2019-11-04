package Input;

import States.GameStateManager;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Gestionnaire de la souris
 * Ce sera peut etre pas nécessaire, mais si on veut faire des boutons, vérifier si on clique su un bouton dans le menu etc...
 * Tout sera dans gsm.
 */
public class MouseManager implements MouseListener {

    GameStateManager gsm;

    public MouseManager(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
