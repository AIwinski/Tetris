package sample.Logic;

import javafx.scene.canvas.GraphicsContext;
import sample.GameController;
import sample.Logic.Shapes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable{
    private String fileName = "data.bin"; //name of file that holds highscores data
    private Board board;
    private Thread thread;
    private GraphicsContext gc;
    private volatile boolean isRunning = false;
    private int points;
    private Shape shape;
    private Tile origin;
    private GameController controller;
    private String nickname;
    String difficultyLevel;
    int speed;

    List<Score> highscores;


    private static boolean [] keys = new boolean[6];

    public static int maxHeight = 3; //okresla jak wysoko  od gory gracz moze klasc jeszcze klocki

    public Game(GraphicsContext gc, GameController controller, String nickname, String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
        this.nickname = nickname;
        this.points = 0;
        this.gc = gc;
        this.controller = controller;
        speed = 50;
        System.out.println("Nowa gra z nickiem " + nickname + " oraz z levelem " + difficultyLevel);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        controller.setMessage("You play as: " + nickname);
        init();
        while (isRunning){
            render(gc);
            for(int i = 0;i<speed; i++){
                try {
                    Thread.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j < keys.length;j++){
                    if(keys[0]){ //lewo
                        System.out.println("lewo");
                        if( !board.collides(shape.move(-1))){
                            shape.setTiles(shape.move(-1));
                        }
                        keys[0] = false;
                    }
                    else if(keys[1]){ //prawo
                        System.out.println("prawo");
                        if( !board.collides(shape.move(1))){
                            shape.setTiles(shape.move(1));
                        }
                        keys[1] = false;
                    }
                    else if(keys[2]){ //esc
                        keys[2] = false;
                    }
                    else if(keys[3]){ //spacja
                        while(!board.collides(shape.fall())){
                            shape.setTiles(shape.fall());
                        }
                        keys[3] = false;
                    }
                    else if(keys[4]){ //obrot w prawo
                        //System.out.println("obrot w prawo");
                        if( !board.collides(shape.rotate(1))){
                            shape.setTiles(shape.rotate(1));
                            shape.setRotation(shape.getRotation() + 1);
                        }
                        keys[4] = false;
                    }
                    else if(keys[5]){ //obrot w lewo
                        //System.out.println("obrot w lewo");
                        if( !board.collides(shape.rotate(-1))){
                            shape.setTiles(shape.rotate(-1));
                            shape.setRotation(shape.getRotation() - 1);
                        }
                        keys[5] = false;
                    }
                }
            }

            if(board.collides(shape.fall())){
                board.fixShapeToTheBoard(shape.getTiles());
                shape = generateRandomShape(origin);
                //System.out.println(shape.getTiles()[0].getxPos());
            } else {
               shape.setTiles(shape.fall());
            }
            if(board.checkIfGameFinished()){
                controller.setMessage("Game over! Click start button to play again!");
                System.out.println("Koniec gry");
                return;
            }
            points += board.clearRows();
            controller.setPoints(points);

        }
        System.out.println("koniec");
        return;
    }

    public void handleInput(int in){
        keys[in-1] = true;
    }

    public synchronized void start(){
        if(isRunning == true){
            return;
        }
        System.out.println("start");
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if (isRunning == false){
            return;
        }
        saveResult();
        isRunning = false;
    }

    private void init(){
        board = new Board(250, 425);
        points = 0;
        origin = new Tile(Board.emptyTile, board.getWidth()/2, maxHeight);
        shape = generateRandomShape(origin);
        loadDataFromFile(fileName);
        setDifficulty(difficultyLevel);
    }

    private void setDifficulty(String diff){
        if(diff=="Easy"){
            speed = 80;
        } else if(diff=="Medium"){
            speed = 60;
        } else if(diff=="Hard"){
            speed = 40;
        }
    }



    private void render(GraphicsContext gc){
        board.render(gc);
        shape.render(gc);
    }

    private Shape generateRandomShape(Tile origin){
        int x = ThreadLocalRandom.current().nextInt(1, 8);
        switch (x ){
            case 1:
                return new IShape(0, origin);
            case 2:
                return new JShape(0, origin);
            case 3:
                return new LShape(0, origin);
            case 4:
                return new OShape(0, origin);
            case 5:
                return new TShape(0, origin);
            case 6:
                return new SShape(0, origin);
            case 7:
                return new ZShape(0, origin);
                default:
                    return new ZShape(0, origin);
        }
    }

    public void saveResult(){
        loadDataFromFile(fileName);
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            boolean exists = false;
            if(highscores == null){
                highscores = new ArrayList<>();
            }

            for(int i=0;i<highscores.size(); i++){
                System.out.println(highscores.get(i).getNickname() + highscores.get(i).getPoints());
                if(highscores.get(i).getNickname().equals(nickname)){
                    exists = true;
                    if (highscores.get(i).getPoints() < points){
                        highscores.get(i).setPoints(points);
                    }
                }
            }
            if(!exists){
                highscores.add(new Score(points, nickname));;
            }
            out.writeObject(highscores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDataFromFile(String fileName){
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            if(fileIn.available() == 0){
                return;
            }
            ObjectInputStream in = new ObjectInputStream(fileIn);

            highscores = (List<Score>) in.readObject();

            System.out.println("z load: " + highscores.size());
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
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
