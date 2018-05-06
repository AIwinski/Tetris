package sample.Logic;

import java.io.Serializable;

public class Score implements Serializable{
    private int points;
    private String nickname;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Score(int points, String nickname) {

        this.points = points;
        this.nickname = nickname;
    }
}
