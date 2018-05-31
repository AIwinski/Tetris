package sample.Logic.Shapes;

import javafx.scene.paint.Color;
import sample.Logic.Shape;
import sample.Logic.Tile;

public class SShape extends Shape {
    public static final Color color = new Color(0.082,0.784,0.078,1);
    private final Tile[][] patterns = {
            { new Tile(color,1, 0), new Tile(color,0, 1), new Tile(color,2, 0), new Tile(color,1, 1) },
            { new Tile(color,0, 0), new Tile(color,1, 1), new Tile(color,0, 1), new Tile(color,1, 2) },
            { new Tile(color,1, 0), new Tile(color,0, 1), new Tile(color,2, 0), new Tile(color,1, 1) },
            { new Tile(color,0, 0), new Tile(color,1, 1), new Tile(color,0, 1), new Tile(color,1, 2) }
    };

    public SShape(int rotation, Tile origin) {
        super(origin, rotation);
        setPatterns(patterns);
        Tile [] temp = new Tile[patterns[rotation].length];
        for(int i=0;i<temp.length;i++){
            temp[i] = new Tile(patterns[rotation][i].getColor(), patterns[rotation][i].getxPos(), patterns[rotation][i].getyPos());
        }
        this.setTiles(temp);
        set(origin);
    }

}
