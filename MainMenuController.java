package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class MainMenuController {

    @FXML
    private GridPane mainmenuPane;


    @FXML
    private void handleStartButtonClick(){
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("game.fxml"));
            mainmenuPane.getChildren().setAll(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHighscoresButtonClick(){

    }

    @FXML
    private void handleExitButtonClick(){
        Platform.exit();
    }



}
