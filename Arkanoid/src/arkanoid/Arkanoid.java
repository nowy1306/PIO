package arkanoid;

import java.awt.Graphics;
import javax.swing.JFrame;

public class Arkanoid {

    public static void main(String[] args){
        JFrame obj = new JFrame();
        int c = 0;
        Gameplay gamePlay = new Gameplay();
        HighScore highScore = new HighScore();
        Menu menu = new Menu("Menu",obj);
        obj.setBounds(10, 10, 800, 800);
        obj.setTitle("Arkanoid");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
        //obj.remove(menu);
        //obj.add(gamePlay);

    }
}
