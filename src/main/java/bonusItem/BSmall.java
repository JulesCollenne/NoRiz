package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

public class BSmall implements CollectableItem {

    TypeEffectBonus typeEffectBonus;

    public BSmall(TypeEffectBonus typeEffectBonus) {
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Player player) {
        player.setIsSmall(500);
        player.setSize(player.getSize()/2);
    }

    @Override
    public void effect(Monster[] monsters) {

    }

    @Override
    public void effect(Player player, Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
