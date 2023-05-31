package strategy;

import entity.Monster;
import utils.*;
import worldBuilder.World;

import java.util.Timer;

import static java.lang.Math.abs;


/**
 * Calcule l'angle entre le monstre et le bonus le plus proche, choisit le chemin possible le plus proche de l'angle.
 *
 * Parfois, le chat reste bloqué à un endroit car l'usage de l'angle comme seule indication pour lé direction à prendre
 * n'est pas bon.
 * Correctif temporaire : Utiliser un timer qui change de bonus à cibler au bout d'un certain temps
 * Ce qu'il faut changer : Utiliser Dijkstra ou autre méthode qui prend en compte les chemins
 */


public class BonusStrat implements Strategy{

    private World world;
    private static int xBonus,yBonus;
    private static int[][] coordsBonus = new int[10][2];
    private static int nb_bonus,current_bonus;
    private static long bonus_timer = 0;

    public BonusStrat(World world) {
        this.world = world;
        nb_bonus = 0;
        current_bonus = 0;
        searchBonus();
        xBonus = coordsBonus[0][0];
        yBonus = coordsBonus[0][1];
        bonus_timer = System.currentTimeMillis();
        setBonusUsed();
        //affiche();
    }

    private void setBonusUsed(){
        /*
        coordsBonusUsed[indexBonusUsed][0] = xBonus;
        coordsBonusUsed[indexBonusUsed][1] = yBonus;
        indexBonusUsed++;
         */
    }

    private void searchBonus(){
        for(int i = 0 ; i < world.map.length ; i++){
            for(int j = 0 ; j < world.map[0].length ; j++){
                if(world.map[j][i] == WORLDITEM.BONUS) {
                    int[] coords = new int[2];
                    coords[0] = j;
                    coords[1] = i;
                    coordsBonus[nb_bonus] = Utils.getCanvasCoords(coords);
                    //coordsBonus[nb_bonus][1] = i*Utils.caseDimension;
                    nb_bonus++;
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

//        while(nbCases != Utils.mapWidth*Utils.mapHeight){
//            if(world.map[i][j - k] == WORLDITEM.BONUS && j-k > 0){
//                coordsBonus[nb_bonus][0] = i * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = (j-k) * Utils.caseDimension;
//                nb_bonus++;
//            }
//            else if(world.map[i + k][j - k] == WORLDITEM.BONUS && i+k < Utils.mapSize && j-k > 0){
//                coordsBonus[nb_bonus][0] = (i+k) * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = (j-k) * Utils.caseDimension;
//                nb_bonus++;
//            }
//            else if(world.map[i + k][j] == WORLDITEM.BONUS && i+k < Utils.mapSize){
//                coordsBonus[nb_bonus][0] = (i+k) * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = j * Utils.caseDimension;
//                nb_bonus++;
//            }
//            else if(world.map[i + k][j + k] == WORLDITEM.BONUS && i+k < Utils.mapSize && j+k < Utils.mapSize){
//                coordsBonus[nb_bonus][0] = (i+k) * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = (j+k) * Utils.caseDimension;
//                nb_bonus++;
//            }
//            else if(world.map[i][j + k] == WORLDITEM.BONUS){
//                coordsBonus[nb_bonus][0] = i * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = (j+k) * Utils.caseDimension;
//                nb_bonus++;
//            }
//            else if(world.map[i - k][j + k] == WORLDITEM.BONUS && i-k > 0 && j+k < Utils.mapSize){
//                coordsBonus[nb_bonus][0] = (i-k) * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = (j+k) * Utils.caseDimension;
//                nb_bonus++;
//            }
//            else if(world.map[i-k][j] == WORLDITEM.BONUS && i-k > 0){
//                coordsBonus[nb_bonus][0] = (i-k) * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = j * Utils.caseDimension;
//                nb_bonus++;
//            }
//            else if(world.map[i - k][j - k] == WORLDITEM.BONUS && i-k > 0 && j-k > 0){
//                coordsBonus[nb_bonus][0] = (i-k) * Utils.caseDimension;
//                coordsBonus[nb_bonus][1] = (j-k) * Utils.caseDimension;
//                nb_bonus++;
//            }
//            nbCases++;
//        }
    }

    private static void affiche(){
        for(int i = 0; i < nb_bonus; i++){
            System.out.println(coordsBonus[i][0] + " " + coordsBonus[i][1]);
        }
    }

    private void targetNewBonus(){
        /*
        if(coordsBonusUsed[(indexBonusUsed - 1)% nb_bonus][0] == coordsBonus[(indexBonusUsed - 1)% nb_bonus][0] && coordsBonusUsed[(indexBonusUsed - 1)% nb_bonus][1] == coordsBonus[(indexBonusUsed - 1)% nb_bonus][1]) {
            xBonus = coordsBonus[indexBonusUsed% nb_bonus][0];
            yBonus = coordsBonus[indexBonusUsed% nb_bonus][1];
            setBonusUsed();
        }
         */
        current_bonus = (current_bonus + 1) % nb_bonus;
        xBonus = coordsBonus[current_bonus][0];
        yBonus = coordsBonus[current_bonus][1];
    }

    public DIRECTION nextWay(Monster monster) {
        int x,y;
        x = monster.getCenterX();
        y = monster.getCenterY();

        //searchBonusSpirale();
        //System.out.println(monster.getSize());
        //System.out.println("X : "+x +  " " + xBonus);
        //System.out.println("Y : "+y +  " " + yBonus);
        long current_time_milli = System.currentTimeMillis();
        //System.out.println(current_time_milli/1000. - bonus_timer/1000.);

        if ((abs(x - xBonus) < monster.getWidth() && abs(y - yBonus) < monster.getHeight()) || current_time_milli/1000. - bonus_timer/1000. > 15.) {
            targetNewBonus();
            //System.out.println("Bonus Target " + xBonus + " " + yBonus);
            bonus_timer = current_time_milli;
            return DIRECTION.STOP;
        }
        else {
            int xVector = xBonus - x;
            int yVector = yBonus - y;


            double angle = Math.atan2(yVector, xVector);
            double cos, sin;
            cos = Math.cos(angle);
            sin = Math.sin(angle);

            DIRECTION currentWay = monster.getFacing();
            //System.out.println(currentWay);

            if (currentWay == DIRECTION.UP && !monster.collider.isPossible(x + 1, y - Utils.caseHeight + 1)) {
                if (monster.collider.isPossible(x - Utils.caseWidth + 1, y + 1))
                    return DIRECTION.LEFT;
                else if (monster.collider.isPossible(x + Utils.caseWidth + 1, y + 1))
                    return DIRECTION.RIGHT;
                else
                    return DIRECTION.DOWN;
            } else if (currentWay == DIRECTION.DOWN && !monster.collider.isPossible(x + 1, y + Utils.caseHeight + 1)) {
                if (monster.collider.isPossible(x + Utils.caseWidth + 1, y + 1))
                    return DIRECTION.RIGHT;
                else if (monster.collider.isPossible(x - Utils.caseWidth + 1, y + 1))
                    return DIRECTION.LEFT;
                else
                    return DIRECTION.UP;
            } else if (currentWay == DIRECTION.LEFT && !monster.collider.isPossible(x - Utils.caseWidth + 1, y + 1)) {
                if (monster.collider.isPossible(x + 1, y + Utils.caseHeight + 1))
                    return DIRECTION.DOWN;
                else if (monster.collider.isPossible(x + 1, y - Utils.caseHeight + 1))
                    return DIRECTION.UP;
                else
                    return DIRECTION.RIGHT;
            } else if (currentWay == DIRECTION.RIGHT && !monster.collider.isPossible(x + Utils.caseWidth + 1, y + 1)) {
                if (monster.collider.isPossible(x + 1, y + Utils.caseHeight + 1))
                    return DIRECTION.DOWN;
                else if (monster.collider.isPossible(x + 1, y - Utils.caseHeight + 1))
                    return DIRECTION.UP;
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
        return DIRECTION.STOP;
    }
}

