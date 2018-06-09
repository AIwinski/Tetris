package sample.Logic;


import javafx.scene.canvas.GraphicsContext;

public abstract class Shape {

    protected Tile [] tiles;
    protected Tile origin;
    protected int rotation;
    private Tile [][] patterns;

    public Shape(Tile origin, int rotation) {
        this.origin = origin;
        this.rotation = rotation;
    }

    public void set(Tile origin){
        for(int i =0;i<tiles.length;i++){
            tiles[i].setxPos(origin.getxPos() + patterns[rotation][i].getxPos());
            tiles[i].setyPos(origin.getyPos() + patterns[rotation][i].getyPos());
        }
    }


    public Tile [] move(int dir){ //-1 left 1 right
        Tile [] temp = new Tile[tiles.length];
        for(int i=0;i<tiles.length;i++){
            temp[i] = new Tile(tiles[i].getColor(), tiles[i].getxPos() + dir, tiles[i].getyPos());
        }
        return temp;
    }

    public Tile [] fall(){
        Tile [] temp = new Tile[tiles.length];
        for(int i=0;i<temp.length;i++){
            temp[i] = new Tile(tiles[i].getColor(), tiles[i].getxPos(), tiles[i].getyPos() + 1);
        }
        return temp;
    }

    public abstract Tile[] rotate(int dir);



//    public Tile [] rotate(int dir){
//        Tile [] temp = new Tile[tiles.length];
//        Tile o = getOrigin();
//        int rot = this.rotation + dir;
//        if(rot > 3){
//            rot = 0;
//        } else if(rot < 0){
//            rot = 3;
//        }
//        for(int i =0;i<temp.length;i++){
//            temp[i] = new Tile(patterns[rot][i].getColor(), patterns[rot][i].getxPos() + o.getxPos()-1,
//                    patterns[rot][i].getyPos() + o.getyPos()-1);
//        }
//        return temp;
//    }


    public void render(GraphicsContext gc){
        gc.setFill(getTiles()[0].getColor());
        for(int k=0;k<getTiles().length;k++) {
            gc.fillRect(getTiles()[k].getxPos() * Tile.size, getTiles()[k].getyPos() * Tile.size, Tile.size, Tile.size);
        }
    }



    public void setPatterns(Tile[][] patterns) {
        this.patterns = patterns;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }

    public Tile getOrigin() {
        return tiles[1];
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        if(rotation < 0){
            this.rotation = 3;
        } else if(rotation > 3){
            this.rotation = 0;
        } else {
            this.rotation = rotation;
        }
    }
}
