package bonusItem;

import entity.Monster;
import entity.Noriz;
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
    public void effect(Noriz noriz) {
        noriz.frozen = 300;
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
