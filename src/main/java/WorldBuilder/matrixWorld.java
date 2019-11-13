package WorldBuilder;

import Utils.WORLDITEM;

import static Utils.WORLDITEM.RICE;
import static Utils.WORLDITEM.WALL;

class matrixWorld {

    final static int nbworld1Rice = 242; //derriere l'ennemi+exterieur en moins ?
    final static WORLDITEM[][] world1 = {{WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, WALL, WALL,WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL,WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, RICE, WALL, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, WALL, RICE, RICE, WALL, RICE, RICE, RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE,  WALL, WALL, WALL, WALL,WALL, WALL, WALL, RICE, WALL, RICE,  WALL, WALL, WALL, WALL,WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, WALL, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, WALL, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL, WALL, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, WALL},
            {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL}};

    final static int nbworld2Rice = 242; //temp
    static final WORLDITEM[][] world2 = {{WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, WALL, WALL,WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL,WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            /**/  {WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE,  WALL, RICE, WALL, WALL,RICE, WALL, WALL, RICE, WALL, RICE,  WALL, WALL, WALL, RICE,WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            /**/ {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, WALL},
            {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL}};

    final static int nbworld3Rice = 242; //temp
    static final WORLDITEM[][] world3 = {{WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, WALL, WALL,WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL,WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, RICE, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, RICE, RICE, WALL, RICE, RICE, WALL, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            /**/  {WALL, WALL, WALL, RICE, RICE, RICE, RICE, RICE, RICE, WALL,WALL, WALL, RICE, RICE, RICE, RICE, WALL, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, RICE, RICE, WALL, RICE, RICE, WALL, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE,  WALL, RICE, RICE, RICE,WALL, WALL, WALL, RICE, RICE, RICE, WALL, WALL, WALL, RICE,WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL},
            /**/ {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL},
            {WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, WALL},
            {WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL}};

    //

}
