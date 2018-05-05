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
        this.setTiles(patterns[rotation]);
        set(origin);
    }
}
