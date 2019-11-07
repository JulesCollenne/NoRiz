package WorldBuilder;

import States.GameStateManager;
import Utils.WORLDITEM;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import static Utils.WORLDITEM.*;
import static java.lang.System.exit;

/**
 * Cette classe permet de créer les niveaux
 * Les niveaux sont des matrices d'entiers dont les valeurs signifie la présence de vide (0), murs (1) ou consommables (2)
 *
 * NOTE TODO : Tu pourras rajouter un argument, ou d'autres fonctions pour créer des niveaux différents. Soit tu fais création de niveau
 * aléatoire, soit des niveau sur mesure. Comme tu le sens
 */
public class WorldBuilder {

    WORLDITEM world[][];

    private int l;
    private int h;

    public WorldBuilder(int l, int h){
        this.l = l;
        this.h = h;
    }

    /*
     * Crée la matrice représentant la map (pour le moment: récupère celle de base selon le niveau)
     * Retourne la matrice crée
     */
    public WORLDITEM[][] build(){
        world = new WORLDITEM[l][h];

        //TODO créer le niveau

        /*
        Niveau de test en attendant
         */

        for(int i = 0; i<h; i++)
            for(int j=0; j<h;j++)
                world[i][j] = ROAD;

        for(int i=0; i<h; i++) { // Censé faire un carré de 21 de coté
            world[i][0] = WALL;
            world[i][h-1] = WALL;
            world[0][i] = WALL;
            world[h-1][i] = WALL;
        }

        return world;
    }

    /*
     *   Entrée: une matrice représentant la carte
     *   Déssine la carte dans la fenêtre de jeu
     *        */
    public void renderMap(GraphicsContext gc) {

        for(int posX=0; posX<world.length; posX++) {
            for (int posY = 0; posY < world[0].length; posY++) {
                renderItem(posX,posY,world[posX][posY], gc);
            }
        }
    }

    /*
     *   Entrée: posX et posY: position de l'item // l'item a dessiner
     *   Dessine l'item a la position demandée
     */
     public void renderItem(int posX, int posY, WORLDITEM item, GraphicsContext gc) {

         switch(item){

            case ROAD:
                /*itemImage = ImageIO.read(new File("pathToImageRoad"));
                g.drawImage(itemImage, posX*50, posY*50);
                */;
                gc.setFill(Color.WHITE);
                gc.fillRect(posX*50, posY*50, 50,50); // mettre 50 en variable gloable ?
                break;

            case WALL:
                gc.setFill(Color.BLACK);
                gc.fillRect(posX*50, posY*50, 50,50);
                break;

            case RICE:
                gc.setFill(Color.YELLOW);
                gc.fillRect(posX*50, posY*50, 50,50);
                break;

            case BONUS:
                gc.setFill(Color.GREEN);
                gc.fillRect(posX*50, posY*50, 50,50);
                break;

        }

    }

}
