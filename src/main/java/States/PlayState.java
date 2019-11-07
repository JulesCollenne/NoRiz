package States;

import Entity.Monster;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class PlayState extends GameState {

    private boolean firstRender;

    PlayState(GameStateManager gsm) {
        super(gsm);
        firstRender = true;
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
            gsm.worldBuilder.render();
            firstRender = false;
        }

        gsm.player.render(gc);

        for(Monster monster : gsm.monsters){
            monster.render(gc);
        }



    }
}
