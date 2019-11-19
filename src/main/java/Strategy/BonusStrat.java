package Strategy;

import Entity.Monster;
import Utils.DIRECTION;
import Utils.WORLDITEM;
import Utils.Utils;
import WorldBuilder.World;


/**
 * Calcule l'angle entre le monstre et le bonus le plus proche, choisit le chemin possible le plus proche de l'angle.
 */
public class BonusStrat implements Strategy{

    private World world;
    private static int xBonus,yBonus;
    private static int[][] coordsBonusUsed = new int[10][2];
    private static int index = 0;

    public BonusStrat(World world) {
        this.world = world;
        int[] coords = searchBonus();
        xBonus = coords[0];
        yBonus = coords[1];
    }

    boolean notTaken(int x, int y) {
        for(int i = 0 ; i < index ; i++){
            if(coordsBonusUsed[i][0] == x && coordsBonusUsed[i][1] == y) {
                return false;
            }
        }
        return true;
    }

    public int[] searchBonus(){
        int[] coords = new int[2];

        for(int i = 0 ; i < world.map.length ; i++){
            for(int j = 0 ; j < world.map[0].length ; j++){
                if(world.map[i][j] == WORLDITEM.BONUS && notTaken(i*Utils.caseDimension, j*Utils.caseDimension)) {
                    coords[0] = i*Utils.caseDimension;
                    coords[1] = j*Utils.caseDimension;
                    coordsBonusUsed[index][0] = coords[0];
                    coordsBonusUsed[index][1] = coords[1];
                    index++;
                    return coords;
                }
            }
        }
        return coords;
    }

    public DIRECTION nextWay(Monster monster) {
        int x = monster.getX();
        int y = monster.getY();

        if(x + Utils.caseDimension  < xBonus   && y + Utils.caseDimension < yBonus) {
            int x2 = xBonus - x;
            int y2 = yBonus - y;

            double angle = Math.atan2(y2, x2);
            double cos, sin;
            cos = Math.cos(angle);
            sin = Math.sin(angle);

            DIRECTION currentWay = monster.getFacing();

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
                if (monster.collider.isPossible(x + 1, y + Utils.caseDimension + 1))
                    return DIRECTION.DOWN;
                else if (monster.collider.isPossible(x + 1, y - Utils.caseDimension + 1))
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
        else {
            int[] coords = searchBonus();
            xBonus = coords[0];
            yBonus = coords[1];
            return DIRECTION.STOP;
        }
        return DIRECTION.STOP;
    }
}

