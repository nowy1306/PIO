/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mjgaj
 */
public class IO {
    public static void readScores(ArrayList players) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("leaderboard.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] w = line.split("\\s+");
            if (w.length != 2) {
                throw new IOException("Zła zawartość pliku ");
            }
            try {
                players.add(new Player(w[0], Integer.parseInt(w[1])));
            } catch (NumberFormatException e) {
                throw new IOException("Zła zawartość pliku ");
            }
        }
    }
    
}
