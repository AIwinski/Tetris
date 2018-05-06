package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Logic.Score;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoresController {
    @FXML
    Button btnBack;

    @FXML
    AnchorPane highscoresPane;

    @FXML
    ListView list;

    private List<Score> highscores;
    private String fileName = "data.bin";

    @FXML
    public void initialize(){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            if(fileIn.available() == 0){
                return;
            }
            ObjectInputStream in = new ObjectInputStream(fileIn);

            highscores = (List<Score>) in.readObject();

            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {
            try{
                new FileOutputStream(fileName, true).close();
                return;
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
        } catch (IOException exx) {
            exx.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(highscores != null){
            Collections.sort(highscores, (x, y) -> x.getPoints() > y.getPoints() ? -1 : (x.getPoints() < y.getPoints()) ? 1 : 0);
            ArrayList<String> elements = new ArrayList<>();
            for(int i=0;i<highscores.size();i++){
                elements.add((i + 1) + ". " + highscores.get(i).getNickname() + " got " + highscores.get(i).getPoints() + " points");
            }
            ObservableList<String> observableList = FXCollections.observableList(elements);
            list.setItems(observableList);
        }

    }

    @FXML
    public void btnBackClicked(){
        try {
            GridPane pane = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            highscoresPane.getChildren().setAll(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
