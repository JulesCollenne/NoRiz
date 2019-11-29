package States;

import UI.inGameUserInterface;
import Utils.Utils;
import javafx.scene.canvas.GraphicsContext;
import WorldBuilder.worldRender;

import java.io.*;
import java.util.Scanner;


public class StoryPlayState extends PlayState {
    StoryPlayState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    void win() {
        gsm.changeState(9);
    }

    void playerDie(){
        saveProgress();
        gameOver();
    }

    @Override
    void manageScore() {
        return;
    }

    public void render(GraphicsContext gc){

        super.render(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer());

    }

    void saveProgress(){

        FileInputStream file = null;
        try {
            file = new FileInputStream("src/main/resources/save/saveFile");

            String fileString = "";

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.substring(0, 3).equals("P_S")) {
                        switch (gsm.difficulty){

                            case EASY:
                                fileString += "P_S = 0\n";
                                break;

                            case MEDIUM:
                                fileString += "P_S = 1\n";
                                break;

                            case HARD:
                                fileString += "P_S = 2\n";
                                break;

                        }
                }
                else{

                    fileString += line+"\n";

                }
            }

            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("src/main/resources/save/saveFile"))));
                writer.write(fileString);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
