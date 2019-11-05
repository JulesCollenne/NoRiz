package Input;

import States.GameState;
import States.GameStateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestion du clavier.
 * Quand on appuie sur une touche : change de direction du personnage si la direction est possible.
 * Pour ce faire, tu peux vérifier les coord de Nori, la matrice etc... Tout est dans gsm
 */
public class KeysManager implements KeyListener {

    GameStateManager gsm;

    public KeysManager(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //gsm.player.
            //TODO On bouge a droite
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
