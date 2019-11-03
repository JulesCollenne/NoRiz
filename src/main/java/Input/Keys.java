package Input;

import States.GameState;
import States.GameStateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener {

    GameStateManager gsm;

    public Keys(GameStateManager gsm){
        this.gsm = gsm;
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_D){
            //TODO On bouge a droite
        }
    }

    public void keyReleased(KeyEvent e) {

    }
}
