package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.Logic.*;

public class GameController {

    @FXML
    private AnchorPane gamePane;

    @FXML
    private Canvas gameCanvas;

    private GraphicsContext gc;

    public static boolean [] keys = new boolean[6];


    private Game game = null;




    @FXML
    private void handleBackButtonClick(){
        game.stop();
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
        for(boolean k : keys){
            k = false;
        }
        if(game == null){
            game = new Game(gc);
        }
        game.start();
    }



    @FXML
    private void handleKeyPress(){
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.A){
                System.out.println("A");
                keys[0] = true;
            }
            else if(key.getCode() == KeyCode.D){
                keys[1] = true;
            }
            else if(key.getCode() == KeyCode.ESCAPE){
                keys[2] = true;
            }
            else if(key.getCode() == KeyCode.SPACE){
                keys[3] = true;
            }
            else if(key.getCode() == KeyCode.W){
                keys[4] = true;
            }
            else if(key.getCode() == KeyCode.S){
                keys[5] = true;
            }
        });
    }

    @FXML
    private void handleKeyReleased(){
        gamePane.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(key.getCode() == KeyCode.A){
                keys[0] = false;
            }
            else if(key.getCode() == KeyCode.D){
                keys[1] = false;
            }
            else if(key.getCode() == KeyCode.ESCAPE){
                keys[2] = false;
            }
            else if(key.getCode() == KeyCode.SPACE){
                keys[3] = false;
            }
            else if(key.getCode() == KeyCode.W){
                keys[4] = false;
            }
            else if(key.getCode() == KeyCode.S){
                keys[5] = false;
            }
        });
    }



}
