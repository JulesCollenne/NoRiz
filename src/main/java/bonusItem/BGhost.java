package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

public class BGhost implements CollectableItem {
    @Override
    public void effect(Player player) {

    }

    @Override
    public void effect(Monster[] monsters) {

    }

    @Override
    public void effect(Player player, Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return null;
    }
}