package sample.Logic;

import javafx.scene.paint.Color;

public class TShape extends Shape{
    public static final Color color = new Color(0.1,0.1,0.1,1);
    private final Tile [][] patterns = {
            { new Tile(color,1, 0), new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,2, 1) },
            { new Tile(color,1, 0), new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,1, 2) },
            { new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,2, 1), new Tile(color,1, 2) },
            { new Tile(color,1, 0), new Tile(color,1, 1), new Tile(color,2, 1), new Tile(color,1, 2) }
    };

    public TShape(int rotation, Tile origin) {
        super(origin, rotation);
        this.setPatterns(patterns);
        this.setTiles(patterns[rotation]);
        set(origin);
    }
}
