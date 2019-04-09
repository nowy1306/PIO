package arkanoid;

import java.awt.PopupMenu;
import javax.swing.JFrame;

public class Arkanoid {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
        int c = 1;
        Gameplay gamePlay = new Gameplay();
        HighScore highScore = new HighScore();
        Menu menu = new Menu("Menu");
        obj.setBounds(10, 10, 800, 800);
        obj.setTitle("Arkanoid");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        A:
        //while (true) 
        {
            switch (c) {
                case 0:
                    if (obj.getContentPane() != menu) {
                        obj.remove(obj.getContentPane());
                        obj.add(menu);
                    }
                    break;
                case 1:
                    if (obj.getContentPane() != gamePlay) {
                        obj.remove(obj.getContentPane());
                        obj.add(gamePlay);
                        while(gamePlay.timer.isRunning());
                    }
                    break;
                case 2:
                    if (obj.getContentPane() != highScore) {
                        obj.remove(obj.getContentPane());
                        obj.add(highScore);
                    }
                    break;
                case 3:
                    break A;
            }

        }
    }
}

