package sample.Logic.Shapes;

import javafx.scene.paint.Color;
import sample.Logic.Shape;
import sample.Logic.Tile;

public class IShape extends Shape {
    public static final Color color = new Color(0.835,0.858,0,1);
    private final Tile[][] patterns = {
            { new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,2, 1), new Tile(color,3, 1) },
            { new Tile(color,1, 0), new Tile(color,1, 1), new Tile(color,1, 2), new Tile(color,1, 3) },
            { new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,2, 1), new Tile(color,3, 1) },
            { new Tile(color,1, 0), new Tile(color,1, 1), new Tile(color,1, 2), new Tile(color,1, 3) }
    };

    public IShape(int rotation, Tile origin) {
        super(origin, rotation);
        this.setPatterns(patterns);
        Tile [] temp = new Tile[patterns[rotation].length];
        for(int i=0;i<temp.length;i++){
            temp[i] = new Tile(patterns[rotation][i].getColor(), patterns[rotation][i].getxPos(), patterns[rotation][i].getyPos());
        }
        this.setTiles(temp);
        set(origin);
    }
}
