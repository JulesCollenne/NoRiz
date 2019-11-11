package WorldBuilder;

import Utils.WORLDITEM;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import static Utils.Utils.caseDimension;

/**
 * Cette classe permet de créer les niveaux
 * Les niveaux sont des matrices d'entiers dont les valeurs signifie la présence de vide (0), murs (1) ou consommables (2)
 *
 * NOTE TODO : Tu pourras rajouter un argument, ou d'autres fonctions pour créer des niveaux différents. Soit tu fais création de niveau
 * aléatoire, soit des niveau sur mesure. Comme tu le sens
 */
public class World {

    public WORLDITEM map[][];

    private int l;
    private int h;

    private int difficulty;

    private Image road = new Image("textures/roadTextureLevel1.png");
    private Image wall = new Image("textures/wallTextureLevel1.png");

    public World(int l, int h){
        this.l = l;
        this.h = h;
    }

    /*
     * Crée la matrice représentant la map (pour le moment: récupère celle de base selon le niveau)
     * Retourne la matrice crée
     */
    public void build(int difficulty){

        this.difficulty = difficulty;
        setDifficulty(difficulty);

        switch(difficulty){

            case 0:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                break;

            case 1:
                road = new Image("textures/roadTextureLevel2.jpg");
                wall = new Image("textures/wallTextureLevel1.png");
                break;

            case 2:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                break;

            default:
                break;
        }

        /*for(int i = 0; i<h; i++)
            for(int j=0; j<h;j++)
                map[i][j] = ROAD;

        for(int i=0; i<h; i++) { // Censé faire un carré de 21 de coté
            map[i][0] = WALL;
            map[i][h-1] = WALL;
            map[0][i] = WALL;
            map[h-1][i] = WALL;
        }*/

        // Attention, le haut de la matrice représente la gauche en jeu

        switch(difficulty){

            case 0:
                map = matrixWorld.world1;
                break;

            case 1:
                map = matrixWorld.world2;
                break;

            case 2:
                map = matrixWorld.world3;
                break;

            default:
                map = matrixWorld.world1;
                break;
        }
    }

    /*
     *   Entrée: une matrice représentant la carte
     *   Dessine la carte dans la fenêtre de jeu
     *        */
    public void renderMap(GraphicsContext gc) {
        for(int posX = 0; posX< map.length; posX++) {
            for (int posY = 0; posY < map[posX].length; posY++) {
                renderItem(posX,posY, map[posX][posY], gc);
            }
        }
    }

    /*
     *   Entrée: posX et posY: position de l'item // l'item a dessiner
     *   Dessine l'item a la position demandée
     */
    private void renderItem(int posX, int posY, WORLDITEM item, GraphicsContext gc) {

         switch(item){

            case ROAD:
                gc.drawImage(road, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                break;

            case WALL:
                gc.drawImage(wall, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                break;

            case RICE:
                gc.drawImage(road, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                gc.setFill(Color.YELLOW);
                gc.fillRect(posX*caseDimension, posY*caseDimension, caseDimension/2.,caseDimension/2.);
                break;

            case BONUS:
                gc.drawImage(road, posX*caseDimension, posY*caseDimension, caseDimension, caseDimension);
                gc.setFill(Color.GREEN);
                gc.fillRect(posX*caseDimension, posY*caseDimension, caseDimension,caseDimension);
                break;

        }

    }

    private void setDifficulty(int difficulty) {
        switch(difficulty){

            case 0:
                road = new Image("textures/roadTextureLevel1.png");
                break;

            case 1:
                road = new Image("textures/roadTextureLevel2.png");
                break;

            case 2:
                road = new Image("textures/roadTextureLevel1.png");
                break;

            default:
                road = new Image("textures/roadTextureLevel1.png");
                break;

        }
    }

}

