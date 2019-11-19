package Utils;

import Entity.Monster;
import Entity.Player;

public class myGameData {

    public WORLDITEM map[][];
    public Monster[] monsters;
    public Player player;
    public long leftTime;
    public int nbLife;
    public int nbRiz;

    public myGameData(WORLDITEM map[][], Monster[] monsters, Player player){

        this.map = map;
        leftTime = Utils.roundDuration;
        nbLife = 5;
        nbRiz = 0;
        this.monsters = monsters;
        this.player = player;

    }

}
