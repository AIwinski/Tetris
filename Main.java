package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    FXMLLoader loader;

    @Override
    public void start(Stage primaryStage) throws Exception{
        loader = new FXMLLoader(
                getClass().getResource(
                        "mainmenu.fxml"
                )
        );
        Parent root = loader.load();
        primaryStage.setTitle("Tetris :)");
        primaryStage.setScene(new Scene(root, 250, 500));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
