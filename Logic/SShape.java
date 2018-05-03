package sample.Logic;

import javafx.scene.paint.Color;

public class SShape extends Shape{
    public static final Color color = new Color(0.082,0.784,0.078,1);
    private final Tile [][] patterns = {
            { new Tile(color,1, 0), new Tile(color,2, 0), new Tile(color,0, 1), new Tile(color,1, 1) },
            { new Tile(color,0, 0), new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,1, 2) },
            { new Tile(color,1, 0), new Tile(color,2, 0), new Tile(color,0, 1), new Tile(color,1, 1) },
            { new Tile(color,0, 0), new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,1, 2) }
    };

    public SShape(int rotation, Tile origin) {
        super(origin, rotation);
        this.setPatterns(patterns);
        this.setTiles(patterns[rotation]);
        set(origin);
    }

}
