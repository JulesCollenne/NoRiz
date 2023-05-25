package states;

import ui.inGameUserInterface;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Scanner;


public class StoryPlayState extends PlayState {
    StoryPlayState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    void win() {
        gsm.changeState(9);
        saveProgress();
    }

    void playerDie(){
        saveProgress();
        gameOver();
    }

    @Override
    void manageScore() {
    }

    public void render(GraphicsContext gc){

        super.render(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer());

    }

    void saveProgress(){

        FileInputStream file;
        try {
            file = new FileInputStream(Objects.requireNonNull(getClass().getResource("./saveFile")).toURI().getPath());

            StringBuilder fileString = new StringBuilder();

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.startsWith("P_S")) {
                    switch (gsm.difficulty) {
                        case EASY -> fileString.append("P_S = 0\n");
                        case MEDIUM -> fileString.append("P_S = 1\n");
                        case HARD -> fileString.append("P_S = 2\n");
                    }
                }
                else{

                    fileString.append(line).append("\n");

                }
            }

            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./saveFile")));
                writer.write(fileString.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
