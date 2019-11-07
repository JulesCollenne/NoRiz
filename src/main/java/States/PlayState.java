package States;

import Entity.Monster;
import Input.KeysManager;
import Input.MouseManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class PlayState extends GameState {

    private boolean firstRender;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        firstRender = true;
    }

    void input(KeysManager key, MouseManager mouse) {
        gsm.player.input(key, mouse);
    }

    public void nextStep() {
        gsm.player.nextStep();
    }

    @Override
    public void input(KeyEvent e) {
        gsm.player.input(e);
    }

    @Override
    public void render(GraphicsContext gc) {

        if(firstRender){
            //TODO afficher le niveau
            firstRender = false;
        }

        gsm.player.render(gc);

        for(Monster monster : gsm.monsters){
            monster.render(gc);
        }



    }
}
