package sample;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class DetailsController {

    @FXML
    public void initialize(){
        diffLevel.setItems(FXCollections.observableArrayList(
                "Easy", "Medium", "Hard"
        ));
        diffLevel.setValue("Easy");
    }

    @FXML
    TextField textNick;

    @FXML
    Text textMessage;

    @FXML
    Button btnPlay;

    @FXML
    ChoiceBox diffLevel;

    @FXML
    AnchorPane detailsPane;

    @FXML
    private void playButtonClicked(){
        if(textNick.getText().length() < 1) {
            textMessage.setText("Nickname field cannot be empty!");
            return;
        } else if(textNick.getText().length() > 10){
            textMessage.setText("Nickname should be maximum 10 characters long!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "game.fxml"
                    )
            );
            AnchorPane pane = loader.load(); //FXMLLoader.load(getClass().getResource("game.fxml"));
            detailsPane.getChildren().setAll(pane);
            GameController controller = loader.getController();
            controller.initData(textNick.getText(), diffLevel.getValue().toString());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void backButtonClicked(){
        try {
            GridPane pane = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            detailsPane.getChildren().setAll(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
