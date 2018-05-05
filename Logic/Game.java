package sample.Logic;

import javafx.scene.canvas.GraphicsContext;
import sample.GameController;
import sample.Logic.Shapes.*;

import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable{
    private Board board;
    private Thread thread;
    private GraphicsContext gc;
    private volatile boolean isRunning = false;
    private int points;
    private Shape shape;
    private Tile origin;
    private GameController controller;
    int speed;


    private static boolean [] keys = new boolean[6];

    public static int maxHeight = 3; //okresla jak wysoko  od gory gracz moze klasc jeszcze klocki

    public Game(GraphicsContext gc, GameController controller) {
        this.points = 0;
        this.gc = gc;
        this.controller = controller;
        speed = 50;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        init();
        while (isRunning){
            tick();
            render(gc);
            for(int i = 0;i<speed; i++){
                try {
                    Thread.sleep(10);
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
                        System.out.println("obrot w prawo");
                        if( !board.collides(shape.rotate(1))){
                            shape.setTiles(shape.rotate(1));
                            shape.setRotation(shape.getRotation() + 1);
                        }
                        keys[4] = false;
                    }
                    else if(keys[5]){ //obrot w lewo
                        System.out.println("obrot w lewo");
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
                System.out.println("Koniec gry");
                return;
            }
            points += board.clearRows();
            controller.setPoints(points);

        }
        System.out.println("konec");
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
        isRunning = false;
    }

    private void init(){
        board = new Board(250, 425);
        points = 0;
        origin = new Tile(Board.emptyTile, board.getWidth()/2, maxHeight);
        shape = generateRandomShape(origin);
    }

    private void tick(){

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


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

}
