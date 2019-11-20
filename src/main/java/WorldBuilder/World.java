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
 * aléatoire, soit des niveau sur mesure. Comme tu le sens
 */
public class World {

    private Stage theStage;
    public WORLDITEM[][] map;

    static Image road = new Image("textures/roadTextureLevel1.png");
    static Image wall = new Image("textures/wallTextureLevel1.png");

    private Image backGroundImage = new Image("textures/roadTextureLevel1.png");

    /**
     * Crée la matrice représentant la map (pour le moment: récupère celle de base selon le niveau)
     * @param chosenDifficulty la difficultée du niveau
     * @return la matrice crée
     */
    public WORLDITEM[][] build(DIF chosenDifficulty){
        switch(chosenDifficulty){
            case EASY:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = loadMap("src/main/resources/Maps/worldEasy.map");
                break;

            case MEDIUM:
                road = new Image("textures/roadTextureLevel2.jpg");
                wall = new Image("textures/wallTextureLevel1.png");
                map = loadMap("src/main/resources/Maps/worldMedium.map");
                break;

            case HARD:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = loadMap("src/main/resources/Maps/worldHard.map");
                break;

            default:
                road = new Image("textures/roadTextureLevel1.png");
                wall = new Image("textures/wallTextureLevel1.png");
                map = loadMap("src/main/resources/Maps/worldEasy.map");
                break;
        }
        return map;
    }

    /**
     *
     * @return une map sans les murs sauf les contours et l'UI
     */
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
        fileChooser.setInitialDirectory( new File("src/main/resources/Maps"));
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
        fileChooser.setInitialDirectory( new File("src/main/resources/Maps"));
        fileChooser.setTitle("Ouvrir un niveau");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Map Files", "*.map"));
        File selectedFile = fileChooser.showOpenDialog(theStage);
        if (selectedFile != null) {
            Scanner scanner;
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
            return tempMap;
        }
        return null;
    }

    public WORLDITEM[][] loadMap(String path) {
        WORLDITEM[][] tempMap = new WORLDITEM[Utils.mapSize][Utils.mapSize];
        File file = new File(path);
        if (file != null) {
            Scanner scanner;
            try {
                scanner = new Scanner(file);
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