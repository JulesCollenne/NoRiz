package WorldBuilder;

import States.GameStateManager.DIF;
import Utils.Utils;
import Utils.WORLDITEM;
import static Utils.WORLDITEM.*;
import static Utils.Utils.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

import static Utils.Utils.caseDimension;
import static Utils.Utils.mapSize;

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

    DIF difficulty;
    Stage theStage;

    private Image road = new Image("textures/roadTextureLevel1.png");
    private Image wall = new Image("textures/wallTextureLevel1.png");

    private Image backGroundImage = new Image("textures/roadTextureLevel1.png");

    public World(int l, int h){
        this.l = l;
        this.h = h;
    }

    /*
     * Crée la matrice représentant la map (pour le moment: récupère celle de base selon le niveau)
     * Retourne la matrice crée
     */
    public void build(DIF difficultyChosen, Stage theStage){

        difficulty = difficultyChosen;
        this.theStage = theStage;

        switch(difficulty){

            case EASY:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = matrixWorld.world1;
                break;

            case MEDIUM:
                road = new Image("textures/roadTextureLevel2.jpg");
                wall = new Image("textures/wallTextureLevel1.png");
                map = matrixWorld.world2;
                break;

            case HARD:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = matrixWorld.world3;
                break;

            default:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = matrixWorld.world1;
                break;
        }
    }

    /*
     *   Entrée: une matrice représentant la carte
     *   Dessine la carte dans la fenêtre de jeu
     *        */
    public void renderMap(GraphicsContext gc) {
        //gc.drawImage(backGroundImage,0,0,Utils.canvasSize, Utils.canvasSize);
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
    public void renderItem(int posX, int posY, WORLDITEM item, GraphicsContext gc) {

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

            case UI:
                gc.setFill(Color.DARKGRAY);
                gc.fillRect(posX*caseDimension, posY*caseDimension, caseDimension,caseDimension);
                break;

        }
    }

    public void makeCleanMap(){
        map = new WORLDITEM[mapSize][mapSize];

        for(int i=0; i <2; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = UI;
                map[mapSize-i-1][j] = UI;
            }
        }

        for(int i=0; i< mapSize; i++){
            for(int j=0; j < mapSize; j++){
                map[i][j] = RICE;
            }
        }

        for(int i = 0; i<mapSize; i++){
            map[i][0] = WALL;
            map[i][mapSize-1] = WALL;
            map[0][i] = WORLDITEM.WALL;
            map[mapSize-1][i] = WALL;
        }
    }

    public void saveMap(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un niveau");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Map Files", "*.map"));
        File selectedFile = fileChooser.showSaveDialog(theStage);
        if (selectedFile != null) {
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(selectedFile)));

                writer.write(getMapString());
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getMapString(){
        StringBuilder mapString = new StringBuilder();
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[i].length; j++){
                mapString.append(worldItemToInt(map[i][j]));
                mapString.append('\n');
            }
        }
        return mapString.toString();
    }

    public void loadMap() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Ouvrir un niveau");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Map Files", "*.map"));
        File selectedFile = fileChooser.showOpenDialog(theStage);
        if (selectedFile != null) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(selectedFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }
            for (int i = 0; i < map.length; i++){
                for (int j = 0; j < map[i].length; j++){ //
                    map[i][j] = intToWorldItem(scanner.nextInt());
                }
            }
        }
    }
}