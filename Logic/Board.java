package sample.Logic;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
    private int widthpx = 250;
    private int heightpx = 425; //px

    private int width;
    private int height;

    public static final Color emptyTile = new Color(0.8,1,1,1);
    public static final Color wallTile = new Color(0.2,0.2,0.2,1);

    private Tile [][] tiles;


    public Board(int widthpx, int heightpx) {
        this.widthpx = widthpx;
        this.heightpx = heightpx;
        width = widthpx/Tile.size;
        height = heightpx/Tile.size;
        tiles = new Tile[width][height];
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                if(i == 0 || i == width - 1 || j == 0 || j == height - 1){
                    tiles[i][j] = new Tile(wallTile, i, j);
                } else {
                    tiles[i][j] = new Tile(emptyTile, i, j);
                }
            }
        }
    }

    public boolean collides(Tile [] nextPos){
        for(int i=0;i<nextPos.length;i++){
            if(nextPos[i].getyPos() < 0 || nextPos[i].getyPos() >= height || nextPos[i].getxPos() < 0 || nextPos[i].getxPos() >= width){
                return true;
            }
        }
        for(int i=0;i<nextPos.length;i++){
            if(tiles[nextPos[i].getxPos()][nextPos[i].getyPos()].getColor() != emptyTile){
                return true;
            }
        }
        return false;
    }

    public void fixShapeToTheBoard(Tile [] currPos){
        for(int i=0;i<currPos.length;i++){
            tiles[currPos[i].getxPos()][currPos[i].getyPos()].setColor(currPos[i].getColor());
        }
    }

    public boolean checkIfGameFinished(){
        for(int i=1;i<getWidth()-1;i++){
            if(tiles[i][Game.maxHeight + 2].getColor() != emptyTile){
                return true;
            }
        }
        return false;
    }


    public void render(GraphicsContext gc){
        for(int i=0;i<getWidth();i++){
            for(int j=0;j<getHeight();j++){
                gc.setFill(getTiles()[i][j].getColor());
                gc.fillRect(getTiles()[i][j].getxPos()*Tile.size, getTiles()[i][j].getyPos()*Tile.size, Tile.size, Tile.size);
            }
        }
    }

    public int clearRows(){
        boolean emptyRow;
        int p = 0;
        for(int i=getHeight() - 2;i>1;i--) {
            emptyRow = true;
            for (int j = 1; j < getWidth() - 1; j++) {
                if (tiles[j][i].getColor() == emptyTile) {
                    emptyRow = false;
                }
            }
            if (emptyRow) {
                p++;
                for(int k=i; k>1; k--){
                    for(int h=1; h<getWidth()-1;h++){
                        tiles[h][k].setColor(tiles[h][k-1].getColor());
                    }
                }
            }
        }
        return p * 10; // user get 10 points for every cleared row
    }


    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    public Tile[][] getTiles() {
        return tiles;
    }
}
