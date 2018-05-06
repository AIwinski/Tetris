package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.Logic.*;

public class GameController {

    @FXML
    private AnchorPane gamePane;

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Text textPoints;

    @FXML
    private Text textMessage;

    private GraphicsContext gc;

    private Game game = null;

    private String nick;
    private String diff;

    private Stage stage;


    @FXML
    private void handleBackButtonClick(){
        if(game != null){
            game.stop();
        }

        try {
            GridPane pane = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
            gamePane.getChildren().setAll(pane);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    private void handleStartButton(){ //odpalanie gry
        gc = gameCanvas.getGraphicsContext2D();
        if(game == null){
            game = new Game(gc, this, nick, diff);
        } else {
            game.stop();
            game = new Game(gc, this, nick, diff);
        }
        game.start();
        stage = (Stage)gamePane.getScene().getWindow();
        stage.setOnCloseRequest((WindowEvent event) -> {
            game.stop();
        });
    }


    @FXML
    private void handleKeyPress(){
        if(game != null){
            gamePane.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode() == KeyCode.A){
                    game.handleInput(1);
                }
                else if(key.getCode() == KeyCode.D){
                    game.handleInput(2);
                }
                else if(key.getCode() == KeyCode.ESCAPE){
                    game.handleInput(3);
                }
                else if(key.getCode() == KeyCode.M){
                    game.handleInput(4);
                }
                else if(key.getCode() == KeyCode.W){
                    game.handleInput(5);
                }
                else if(key.getCode() == KeyCode.S){
                    game.handleInput(6);
                }
            });
        }
    }

    public void setPoints(int points){
        textPoints.setText("Points: " + points);
    }

    public void setMessage(String message){
        textMessage.setText(message);
    }

    public void initData(String nick, String diff){
        this.diff = diff;
        this.nick = nick;
    }


}
