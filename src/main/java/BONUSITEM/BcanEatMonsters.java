package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TypeEffectBonus;

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
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
