package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TypeEffectBonus;

public class BSmall implements CollectableItem {
    @Override
    public void effect(Player player) {
        player.setSize(player.getSize()/2);
    }

    @Override
    public void effect(Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return null;
    }
}
