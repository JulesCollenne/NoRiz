package bonusItem;

import entity.Monster;
import entity.Player;
import utils.TypeEffectBonus;

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
    public void effect(Player player, Monster[] monsters) {

    }

    @Override
    public TypeEffectBonus getTypeEffectBonus() {
        return typeEffectBonus;
    }
}
