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

    private int l;
    private int h;

    DIF difficulty;
    Stage theStage;
    public WORLDITEM[][] map;

    public static Image road = new Image("textures/roadTextureLevel1.png");
    public static Image wall = new Image("textures/wallTextureLevel1.png");

    private Image backGroundImage = new Image("textures/roadTextureLevel1.png");

    public World(int l, int h){
        this.l = l;
        this.h = h;
    }

    /*
     * Crée la matrice représentant la map (pour le moment: récupère celle de base selon le niveau)
     * Retourne la matrice crée
     */
    public WORLDITEM[][] build(DIF difficultyChosen){

        difficulty = difficultyChosen;

        switch(difficulty){

            case EASY:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = copyMap(matrixWorld.world1);
                break;

            case MEDIUM:
                road = new Image("textures/roadTextureLevel2.jpg");
                wall = new Image("textures/wallTextureLevel1.png");
                map = copyMap(matrixWorld.world2);
                break;

            case HARD:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = copyMap(matrixWorld.world3);
                break;

            default:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = copyMap(matrixWorld.world1);
                break;
        }

        return map;
    }

    public WORLDITEM[][] makeCleanMap(){
        WORLDITEM[][] tempMap = new WORLDITEM[mapSize][mapSize];

        for(int i=0; i< mapSize; i++){
            for(int j=0; j < mapSize; j++){
                tempMap[i][j] = RICE;
            }
        }

        for(int i = 0; i<mapSize; i++){
            tempMap[i][2] = WALL;
            tempMap[i][mapSize-1] = WALL;
            tempMap[0][i] = WORLDITEM.WALL;
            tempMap[mapSize-1][i] = WALL;
        }

        for(int i=0; i <mapSize; i++) {
            for (int j = 0; j < 2; j++) {
                tempMap[i][j] = UI;
            }
        }

        return tempMap;
    }

    public void saveMap(WORLDITEM[][] tempMap){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un niveau");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Map Files", "*.map"));
        File selectedFile = fileChooser.showSaveDialog(theStage);
        if (selectedFile != null) {
            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(selectedFile)));

                writer.write(getMapString(tempMap));
                writer.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getMapString(WORLDITEM[][] tempMap){
        StringBuilder mapString = new StringBuilder();
        for (int i = 0; i < tempMap.length; i++){
            for (int j = 0; j < tempMap[i].length; j++){
                mapString.append(worldItemToInt(tempMap[i][j]));
                mapString.append('\n');
            }
        }
        return mapString.toString();
    }

    public WORLDITEM[][] loadMap() {
        WORLDITEM[][] tempMap = new WORLDITEM[Utils.mapSize][Utils.mapSize];
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
                return null;
            }
            for (int i = 0; i < Utils.mapSize; i++){
                for (int j = 0; j < Utils.mapSize; j++){ //
                    tempMap[i][j] = intToWorldItem(scanner.nextInt());
                }
            }
        }
        return tempMap;
    }
}