package utils;

public class myGameData {

    public WORLDITEM map[][];
    public long leftTime;
    public int nbLife;
    public int nbRiz;
    public int score;

    public myGameData(WORLDITEM map[][]){

        this.map = map;
        leftTime = Utils.roundDuration;
        nbLife = 5;
        nbRiz = 0;
        score = 0;

    }

}
