package WorldBuilder;

import Utils.WORLDITEM;

import static Utils.WORLDITEM.RICE;
import static Utils.WORLDITEM.WALL;
import static Utils.WORLDITEM.UI;

public class matrixWorld {

    public final static int nbRiceWorld1 = 271;
    static final WORLDITEM[][] world1 = {
            {UI, UI, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, UI, UI},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE ,RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE ,WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},

            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL ,RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE ,WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL ,RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE ,WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, RICE, WALL, UI, UI},

            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, UI, UI},

            {UI, UI, WALL, RICE, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL ,RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE ,WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL ,RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE ,WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE ,RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE ,WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, RICE, RICE, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, RICE, WALL, UI, UI},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, UI, UI},
            {UI, UI, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, UI, UI},
    };


    public final static int nbRiceWorld2 = 300;
    static final WORLDITEM[][] world2 = {
            {UI, UI, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
    };

    public final static int nbRiceWorld3 = 331;
    static final WORLDITEM[][] world3 = {                                                    //C
            {UI, UI, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, WALL, RICE, WALL, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL, RICE, WALL, WALL, RICE, WALL, WALL, WALL, WALL, RICE, WALL, RICE, WALL},
            {UI, UI, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, RICE, WALL},
            {UI, UI, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL, WALL}};

    //

}
