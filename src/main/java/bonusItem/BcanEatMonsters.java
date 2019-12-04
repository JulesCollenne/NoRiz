package bonusItem;

import entity.Monster;
import entity.Noriz;
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
    public void effect(Noriz noriz) {
        noriz.setInvulnerable(500);
    }

    @Override
    public void effect(Monster[] monsters) {

    }

    @Override
    public void effect(Noriz noriz, Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
