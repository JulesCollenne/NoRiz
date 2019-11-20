package BONUSITEM;

import Entity.Monster;
import Entity.Player;
import Utils.TYPEBONUS;

public class BstopMonsters implements BonusItem {

    int x, y;
    TYPEBONUS type =  TYPEBONUS.BONUSFREEZE;

    public BstopMonsters() {
    }

    @Override
    public void effect(Player p) {
       // System.out.println("bonus stop dans effect with player");
    }

    @Override
    public void effect(Monster[] monsters) {
        for(int i =0; i< monsters.length; i++){
            monsters[i].frozen = 500;
        }
    }
}
