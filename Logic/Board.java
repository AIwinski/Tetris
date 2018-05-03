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

    public boolean collides(Tile [] currPos, Tile [] nextPos){
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

    public void clearRows(){
        boolean [] result = new boolean[getHeight()];
        boolean emptyRow = true;
        for(int i=getHeight()-1;i>=0;i--){
            for(int j=0;j<getWidth();j++){
                if(tiles[j][i].getColor() == emptyTile){
                    emptyRow = false;
                }
            }
            if(emptyRow){
                result[i] = true;
            }
        }
        for(int i=0;i<result.length;i++){
            if(result[i]){
                for(int j=i;j<getHeight()-1;j++){
                    for(int k=0;k<getWidth();k++){
                        tiles[j][k].setColor(tiles[j+1][k].getColor());
                    }
                }
            }
        }
        for(int i=0;i<getWidth();i++){
            tiles[getHeight()-1][i].setColor(emptyTile);
        }
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }
}
