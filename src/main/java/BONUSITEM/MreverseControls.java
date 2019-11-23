package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TYPEBONUS;

public class MreverseControls implements CollectableItem {

    int x, y;
    TYPEBONUS type = TYPEBONUS.MALUSFREEZE;

    public MreverseControls(){}

    @Override
    public void effect(Player p) {
            p.setReversed(500);
        }

    @Override
    public void effect(Monster[] monsters) {

    }

}
