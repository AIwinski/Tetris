package sample.Logic;

import javafx.scene.paint.Color;

public class ZShape extends Shape {
    public static final Color color = new Color(0.211,0.078,0.784,1);
    private final Tile [][] patterns = {
            { new Tile(color,0, 0), new Tile(color,1, 0), new Tile(color,1, 1), new Tile(color,2, 1) },
            { new Tile(color,1, 0), new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,0, 2) },
            { new Tile(color,0, 0), new Tile(color,1, 0), new Tile(color,1, 1), new Tile(color,2, 1) },
            { new Tile(color,1, 0), new Tile(color,0, 1), new Tile(color,1, 1), new Tile(color,0, 2) }
    };

    public ZShape(int rotation, Tile origin) {
        super(origin, rotation);
        this.setPatterns(patterns);
        this.setTiles(patterns[rotation]);
        set(origin);
    }
}
