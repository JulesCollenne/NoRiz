package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

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
