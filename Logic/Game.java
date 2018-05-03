package sample.Logic;

import javafx.scene.canvas.GraphicsContext;
import sample.GameController;

import java.util.concurrent.ThreadLocalRandom;

public class Game implements Runnable{
    private Board board;
    private Thread thread;
    private GraphicsContext gc;
    private boolean isRunning = false;
    private int points;
    private Shape shape;
    private Tile origin;

    public static int maxHeight = 3; //okresla jak wysoko  od gory gracz moze klasc jeszcze klocki

    public Game(GraphicsContext gc) {
        this.gc = gc;
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
            for(int i = 0;i<20; i++){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j < GameController.keys.length;j++){
                    System.out.println(GameController.keys[0]);
                    if(GameController.keys[0]){ //lewo
                        System.out.println("lewo");
                        if( !board.collides(shape.getTiles(), shape.move(-1))){
                            shape.setTiles(shape.move(-1));
                        }
                    }
                    else if(GameController.keys[1]){ //prawo
                        if( !board.collides(shape.getTiles(), shape.move(1))){
                            shape.setTiles(shape.move(1));
                        }
                    }
                    else if(GameController.keys[2]){ //esc

                    }
                    else if(GameController.keys[3]){ //spacja

                    }
                    else if(GameController.keys[4]){ //obrot w prawo

                    }
                    else if(GameController.keys[5]){ //obrot w lewo

                    }
                }
            }

            //System.out.print("x");
            if(board.collides(shape.getTiles(), shape.fall())){
                board.fixShapeToTheBoard(shape.getTiles());
                shape = generateRandomShape(origin);
                System.out.println(shape.getTiles()[0].getxPos());
            } else {
               shape.setTiles(shape.fall());
            }
            if(board.checkIfGameFinished()){
                System.out.println("Koniec gry");
                stop();
            }
        }
        stop();
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
        System.out.println("stop");
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
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
}
