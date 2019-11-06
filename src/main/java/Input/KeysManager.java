package Input;

import UI.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestion du clavier.
 * Quand on appuie sur une touche : change de direction du personnage si la direction est possible.
 * Pour ce faire, tu peux vérifier les coord de Nori, la matrice etc... Tout est dans gsm
 */
public class KeysManager implements KeyListener {

    public final int KEY_D = 0;
    public final int KEY_U = 1;
    public final int KEY_L = 2;
    public final int KEY_R = 3;


    public boolean[] keys = new boolean[10];

    public KeysManager(GamePanel gamePanel){
        gamePanel.addKeyListener(this);
    }

    public void initKeys(){
        for(boolean key : keys){
            key = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            //gsm.player.
            //TODO On bouge a droite

        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            keys[KEY_R] = true;
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
