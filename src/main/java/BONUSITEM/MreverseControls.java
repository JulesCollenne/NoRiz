package BONUSITEM;

import Entity.Monster;
import Entity.Player;

public class MreverseControls implements CollectableItem {

    public MreverseControls(){}

    @Override
    public void effect(Player p, Monster[] monsters) {
            p.setReversed(500);
        }
}
