package sample.Logic;

import javafx.scene.paint.Color;

public class Tile {
    public static final int size = 16;

    private Color color;
    private int xPos;
    private int yPos;


    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Tile(Color color, int xPos, int yPos) {

        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
