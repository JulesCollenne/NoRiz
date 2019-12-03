package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

/**
 * This bonus allow Nori to eat the enemies
 */
public class BcanEatMonsters implements CollectableItem {

    TypeEffectBonus typeEffectBonus;

    public BcanEatMonsters(TypeEffectBonus typeEffectBonus){
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Player player) {
        player.setInvulnerable(500);
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
