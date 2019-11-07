package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gestion du clavier.
 * Quand on appuie sur une touche : change de direction du personnage si la direction est possible.
 * Pour ce faire, tu peux vérifier les coord de Nori, la matrice etc... Tout est dans gsm
 */
public class KeysManager implements KeyListener {

    public final int KEY_S = 0;
    public final int KEY_Z = 1;
    public final int KEY_Q = 2;
    public final int KEY_D = 3;


    public boolean[] keys = new boolean[10];

    public void initKeys(){
        for(int i = 0; i < keys.length; i++){
            keys[i] = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_S)
            keys[KEY_S] = true;
        if(e.getKeyCode() == KeyEvent.VK_Z)
            keys[KEY_Z] = true;
        if(e.getKeyCode() == KeyEvent.VK_Q)
            keys[KEY_Q] = true;
        if(e.getKeyCode() == KeyEvent.VK_D)
            keys[KEY_D] = true;
    }

    public void keyReleased(KeyEvent e) {

    }
}
