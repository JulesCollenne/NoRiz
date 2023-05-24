package worldBuilder;

import utils.Utils;
import utils.WORLDITEM;
import static utils.WORLDITEM.*;
import static utils.Utils.*;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

import static utils.Utils.mapSize;

/**
 * Cette classe permet de créer les niveaux
 * Les niveaux sont des matrices d'entiers dont les valeurs signifie la présence de vide (0), murs (1) ou consommables (2)
 *
 * aléatoire, soit des niveau sur mesure. Comme tu le sens
 */
public class World {

    private Stage theStage;
    public WORLDITEM[][] map;
    int nbArcadeMap = 3;

    //var img2 = new Image(TheClassThisCodeIsIn.class.getResource("logo1.png").toString());
    static Image road = new Image(World.class.getResource("/textures/road1.png").toString());
    static Image wall = new Image(World.class.getResource("/textures/wall1.png").toString());
    static Image rice = new Image(World.class.getResource("/collectable/GrainDeRiz.png").toString());
    static Image[] bonus = new Image[2];

    public World(){
        bonus[0] = new Image(World.class.getResource("/collectable/bonus0.png").toString());
        bonus[1] = new Image(World.class.getResource("/collectable/bonus1.png").toString());
    }

    /**
     * Crée la matrice représentant la map (pour le moment: récupère celle de base selon le niveau)
     * @param chosenDifficulty la difficultée du niveau
     * @return la matrice crée
     */
    public WORLDITEM[][] build(DIF chosenDifficulty){

        switch (chosenDifficulty) {
            case EASY -> {
                road = new Image(World.class.getResource("/textures/road1.png").toString());
                wall = new Image(World.class.getResource("/textures/wall1.png").toString());
                map = loadMap("/Maps/worldEasy.map");
            }
            case MEDIUM -> {
                road = new Image(World.class.getResource("/textures/road2.png").toString());
                wall = new Image(World.class.getResource("/textures/wall2.png").toString());
                map = loadMap("/Maps/worldMedium.map");
            }
            case HARD -> {
                road = new Image(World.class.getResource("/textures/road3.png").toString());
                wall = new Image(World.class.getResource("/textures/wall3.png").toString());
                map = loadMap("/Maps/worldHard.map");
            }
            case ARCADE -> {
                Random rand2 = new Random();
                road = new Image(World.class.getResource("/textures/road1.png").toString());
                wall = new Image(World.class.getResource("/textures/wall1.png").toString());
                map = loadMap("/Maps/arcade" + rand2.nextInt(nbArcadeMap) + ".map");
            }
            //map = loadMap("src/main/resources/Maps/arcade2.map");
            default -> {
                road = new Image("textures/road1.png");
                wall = new Image("textures/wall1.png");
                map = loadMap("/Maps/worldEasy.map");
            }
        }
        return map;
    }

    public WORLDITEM[][] build(WORLDITEM[][] map){
        this.map = map;
        return map;
    }

    /**
     *
     * @return une map sans les murs sauf les contours et l'ui
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
        Scanner scanner;
        InputStream inputStream = getClass().getResourceAsStream(path);
        assert inputStream != null;
        scanner = new Scanner(inputStream);
        for (int i = 0; i < Utils.mapSize; i++){
            for (int j = 0; j < Utils.mapSize; j++){
                tempMap[i][j] = intToWorldItem(scanner.nextInt());
            }
        }
        scanner.close();
        return tempMap;
    }
}