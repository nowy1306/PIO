/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.io.Serializable;

/**
 *
 * @author mjgaj
 */
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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
