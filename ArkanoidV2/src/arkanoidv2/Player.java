
package arkanoidv2;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable {
    private String username;
    private int score;
    
    public Player(String name, int scr){
        this.username = name;
        this.score = scr;
    }
    @Override
    public int compareTo(Player other){
        if(score > other.getScore())
            return -1;
        else if(getScore() < other.getScore())
            return 1;
        else 
            return 0;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }
}
