package sample.Logic.Shapes;

import javafx.scene.paint.Color;
import sample.Logic.Shape;
import sample.Logic.Tile;

public class JShape extends Shape {
    public static final Color color = new Color(1,0.360,0.678,1);
    private final Tile[][] patterns = {
            { new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,2, 1), new Tile(color,2, 0) },
            { new Tile(color,1, 0), new Tile(color,1, 1), new Tile(color,1, 2), new Tile(color,2, 2) },
            { new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,2, 1), new Tile(color,0, 2) },
            { new Tile(color,1, 0), new Tile(color,1, 1), new Tile(color,1, 2), new Tile(color,0, 0) }
    };

    public JShape(int rotation, Tile origin) {
        super(origin, rotation);
        this.setPatterns(patterns);
        Tile [] temp = new Tile[patterns[rotation].length];
        for(int i=0;i<temp.length;i++){
            temp[i] = new Tile(patterns[rotation][i].getColor(), patterns[rotation][i].getxPos(), patterns[rotation][i].getyPos());
        }
        this.setTiles(temp);
        set(origin);
    }

    public Tile [] rotate(int dir){
        Tile [] temp = new Tile[tiles.length];
        Tile o = getOrigin();
        int rot = this.rotation + dir;
        if(rot > 3){
            rot = 0;
        } else if(rot < 0){
            rot = 3;
        }
        for(int i =0;i<temp.length;i++){
            temp[i] = new Tile(patterns[rot][i].getColor(), patterns[rot][i].getxPos() + o.getxPos()-1,
                    patterns[rot][i].getyPos() + o.getyPos()-1);
        }
        return temp;
    }

}
