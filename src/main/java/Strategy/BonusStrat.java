package Strategy;

import Entity.Monster;
import Utils.*;
import WorldBuilder.World;



/**
 * Calcule l'angle entre le monstre et le bonus le plus proche, choisit le chemin possible le plus proche de l'angle.
 */


public class BonusStrat implements Strategy{

    private World world;
    private static int xBonus,yBonus;
    private static int[][] coordsBonus = new int[10][2];
    private static int[][] coordsBonusUsed = new int[10][2];
    private static int index,indexBonusUsed;

    public BonusStrat(World world) {
        this.world = world;
        index = 0;
        indexBonusUsed = 0;
        searchBonus();
        xBonus = coordsBonus[0][0];
        yBonus = coordsBonus[0][1];
        setBonusUsed();
        //affiche();
    }

    private void setBonusUsed(){
        coordsBonusUsed[indexBonusUsed][0] = xBonus;
        coordsBonusUsed[indexBonusUsed][1] = yBonus;
        indexBonusUsed++;
    }

    private void searchBonus(){
        for(int i = 0 ; i < world.map.length ; i++){
            for(int j = 0 ; j < world.map[0].length ; j++){
                if(world.map[i][j] == WORLDITEM.BONUS) {
                    coordsBonus[index][0] = i*Utils.caseDimension;
                    coordsBonus[index][1] = j*Utils.caseDimension;
                    index++;
                }
            }
        }
    }

    //TODO chercher le bonus le plus proche marche pas encore
    private void searchBonusSpirale(){
        int i,j;
        i = 0;
        j = 0;

        int nbCases = 0;
        int k = 1;

        while(nbCases != Utils.mapSize*Utils.mapSize){
            if(world.map[i][j - k] == WORLDITEM.BONUS && j-k > 0){
                coordsBonus[index][0] = i * Utils.caseDimension;
                coordsBonus[index][1] = (j-k) * Utils.caseDimension;
                index++;
            }
            else if(world.map[i + k][j - k] == WORLDITEM.BONUS && i+k < Utils.mapSize && j-k > 0){
                coordsBonus[index][0] = (i+k) * Utils.caseDimension;
                coordsBonus[index][1] = (j-k) * Utils.caseDimension;
                index++;
            }
            else if(world.map[i + k][j] == WORLDITEM.BONUS && i+k < Utils.mapSize){
                coordsBonus[index][0] = (i+k) * Utils.caseDimension;
                coordsBonus[index][1] = j * Utils.caseDimension;
                index++;
            }
            else if(world.map[i + k][j + k] == WORLDITEM.BONUS && i+k < Utils.mapSize && j+k < Utils.mapSize){
                coordsBonus[index][0] = (i+k) * Utils.caseDimension;
                coordsBonus[index][1] = (j+k) * Utils.caseDimension;
                index++;
            }
            else if(world.map[i][j + k] == WORLDITEM.BONUS){
                coordsBonus[index][0] = i * Utils.caseDimension;
                coordsBonus[index][1] = (j+k) * Utils.caseDimension;
                index++;
            }
            else if(world.map[i - k][j + k] == WORLDITEM.BONUS && i-k > 0 && j+k < Utils.mapSize){
                coordsBonus[index][0] = (i-k) * Utils.caseDimension;
                coordsBonus[index][1] = (j+k) * Utils.caseDimension;
                index++;
            }
            else if(world.map[i-k][j] == WORLDITEM.BONUS && i-k > 0){
                coordsBonus[index][0] = (i-k) * Utils.caseDimension;
                coordsBonus[index][1] = j * Utils.caseDimension;
                index++;
            }
            else if(world.map[i - k][j - k] == WORLDITEM.BONUS && i-k > 0 && j-k > 0){
                coordsBonus[index][0] = (i-k) * Utils.caseDimension;
                coordsBonus[index][1] = (j-k) * Utils.caseDimension;
                index++;
            }
            nbCases++;
        }
    }

    private static void affiche(){
        for(int i = 0 ; i < index ; i++){
            System.out.println(coordsBonus[i][0] + " " + coordsBonus[i][1]);
        }
    }

    public void targetNewBonus(){
        System.out.println("");
        if(coordsBonusUsed[(indexBonusUsed - 1)%index][0] == coordsBonus[(indexBonusUsed - 1)%index][0] && coordsBonusUsed[(indexBonusUsed - 1)%index][1] == coordsBonus[(indexBonusUsed - 1)%index][1]) {
            xBonus = coordsBonus[indexBonusUsed%index][0];
            yBonus = coordsBonus[indexBonusUsed%index][1];
            setBonusUsed();
        }
    }

    public DIRECTION nextWay(Monster monster) {
        int x,y;
        x = monster.getX();
        y = monster.getY();

        //searchBonusSpirale();

        if(x != xBonus && y != yBonus){
            int xVector = xBonus - x;
            int yVector = yBonus - y;

            //System.out.println("Bonus " + xBonus + " " + yBonus);
            //System.out.println("Monster " + x + " " + y + "\n");

            double angle = Math.atan2(yVector, xVector);
            double cos, sin;
            cos = Math.cos(angle);
            sin = Math.sin(angle);

            DIRECTION currentWay = monster.getFacing();
            //System.out.println(currentWay);

            if (currentWay == DIRECTION.UP && !monster.collider.isPossible(x + 1, y - Utils.caseDimension + 1)) {
                if (monster.collider.isPossible(x - Utils.caseDimension + 1, y + 1))
                    return DIRECTION.LEFT;
                else if (monster.collider.isPossible(x + Utils.caseDimension + 1, y + 1))
                    return DIRECTION.RIGHT;
                else
                    return DIRECTION.DOWN;
            } else if (currentWay == DIRECTION.DOWN && !monster.collider.isPossible(x + 1, y + Utils.caseDimension + 1)) {
                if (monster.collider.isPossible(x - Utils.caseDimension + 1, y + 1))
                    return DIRECTION.LEFT;
                else if (monster.collider.isPossible(x + Utils.caseDimension + 1, y + 1))
                    return DIRECTION.RIGHT;
                else
                    return DIRECTION.UP;
            } else if (currentWay == DIRECTION.LEFT && !monster.collider.isPossible(x - Utils.caseDimension + 1, y + 1)) {
                if (monster.collider.isPossible(x + 1, y - Utils.caseDimension + 1))
                    return DIRECTION.UP;
                else if (monster.collider.isPossible(x + 1, y + Utils.caseDimension + 1))
                    return DIRECTION.DOWN;
                else
                    return DIRECTION.RIGHT;
            } else if (currentWay == DIRECTION.RIGHT && !monster.collider.isPossible(x + Utils.caseDimension + 1, y + 1)) {
                if (monster.collider.isPossible(x + 1, y - Utils.caseDimension + 1))
                    return DIRECTION.UP;
                else if (monster.collider.isPossible(x + 1, y + Utils.caseDimension + 1))
                    return DIRECTION.DOWN;
                else
                    return DIRECTION.LEFT;
            }

            if (-0.5 < cos && cos < 0.5) {
                if (sin < 0) {
                    return DIRECTION.UP;                                                    // Haut
                } else if (sin > 0) {
                    return DIRECTION.DOWN;                                                  // Bas
                }

            } else if (-0.5 < sin && sin < 0.5) {
                if (cos < 0) {
                    return DIRECTION.LEFT;                                                  // Gauche
                } else if (cos > 0) {
                    return DIRECTION.RIGHT;                                                 // Droite
                }
            } else if (cos >= 0.5) {
                if (sin >= 0.5) {
                    if (sin > cos) {
                        return DIRECTION.DOWN;                                              // Bas
                    } else {
                        return DIRECTION.RIGHT;                                             // Droite
                    }
                } else if (sin <= -0.5) {
                    sin = -sin;
                    if (sin > cos) {
                        return DIRECTION.UP;                                                // Haut
                    } else {
                        return DIRECTION.RIGHT;                                             // Droite
                    }
                }
            } else if (cos <= -0.5) {
                cos = -cos;
                if (sin >= 0.5) {
                    if (sin > cos) {
                        return DIRECTION.DOWN;                                              // Bas
                    } else {
                        return DIRECTION.LEFT;                                              // Gauche
                    }
                } else if (sin <= -0.5) {
                    sin = -sin;
                    if (sin > cos) {
                        return DIRECTION.UP;                                                 // Haut
                    } else {
                        return DIRECTION.LEFT;                                               // Gauche
                    }
                }
            }
        }
        else {
            targetNewBonus();
            System.out.println("Bonus Target " + xBonus + " " + yBonus);
            //System.out.println("STOP");
            //return DIRECTION.STOP;
        }
        return DIRECTION.STOP;
    }
}

