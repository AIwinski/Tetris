package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class MainMenuController {

    @FXML
    private GridPane mainmenuPane;

    private FXMLLoader loader;

    @FXML
    private void handleStartButtonClick(){
        try {
            loader = new FXMLLoader(
                    getClass().getResource(
                            "details.fxml"
                    )
            );
            AnchorPane pane = loader.load();
            mainmenuPane.getChildren().setAll(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleHighscoresButtonClick(){
        try {
            loader = new FXMLLoader(
                    getClass().getResource(
                            "highscores.fxml"
                    )
            );
            AnchorPane pane = loader.load();
            mainmenuPane.getChildren().setAll(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExitButtonClick(){
        Platform.exit();
    }


}
