package sample.Logic;

import javafx.scene.paint.Color;

public class OShape extends Shape{
    public static final Color color = new Color(1,0.019,0.121,1);
    private final Tile [][] patterns = {
            { new Tile(color,0, 0), new Tile(color,0, 1), new Tile(color,1, 0), new Tile(color,1, 1) },
            { new Tile(color,0, 0), new Tile(color,0, 1), new Tile(color,1, 0), new Tile(color,1, 1) },
            { new Tile(color,0, 0), new Tile(color,0, 1), new Tile(color,1, 0), new Tile(color,1, 1) },
            { new Tile(color,0, 0), new Tile(color,0, 1), new Tile(color,1, 0), new Tile(color,1, 1) }
    };

    public OShape(int rotation, Tile origin) {
        super(origin, rotation);
        this.setPatterns(patterns);
        this.setTiles(patterns[rotation]);
        set(origin);
    }


}
