package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TypeEffectBonus;

/**
 * This malus freeze Nori for some time
 */
public class MstopNoriz implements CollectableItem{

    TypeEffectBonus typeEffectBonus;

    public MstopNoriz(TypeEffectBonus typeEffectBonus){
        this.typeEffectBonus = typeEffectBonus;
    }

    @Override
    public void effect(Player player) {
        player.frozen = 300;
    }

    @Override
    public void effect(Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
