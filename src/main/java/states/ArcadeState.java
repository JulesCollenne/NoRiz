package states;

import ui.inGameUserInterface;
import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ArcadeState extends PlayState {


    ArcadeState(GameStateManager gsm, inGameUserInterface ui) {
        super(gsm, ui);
    }

    @Override
    void win() {

        myData.score += myData.nbLife * 100;

        gsm.currentScore = myData.score;

        gsm.changeState(6);

    }

    void playerDie(){

        saveBestScore(myData.score);
        gsm.currentScore = 0;
        gameOver();
    }

    @Override
    void manageScore() {
        myData.score = gsm.currentScore;
    }

    @Override
    public void render(GraphicsContext gc){
        super.render(gc);

        ui.render(gc, myData.nbLife, myData.nbRiz, getTimer(), myData.score);
    }


    void saveBestScore(int score){

        FileInputStream file;
        try {
            file = new FileInputStream(getClass().getResource("/save/saveFile").toURI().getPath());

            String fileString = "";

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String line = sc.nextLine();

                if (line.startsWith("B_S")) {
                    if(score > Integer.parseInt(line.substring(6))){
                        gsm.bestScore = score;
                        fileString += "B_S = "+score+"\n";
                    }
                    else{
                        fileString += line+"\n";
                    }
                }
                else{
                    fileString += line+"\n";
                }
            }

            try {
                Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("/save/saveFile"))));
                writer.write(fileString);
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
