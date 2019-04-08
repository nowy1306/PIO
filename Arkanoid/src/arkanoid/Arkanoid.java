package arkanoid;

import java.awt.PopupMenu;
import javax.swing.JFrame;

public class Arkanoid {


    public static void main(String[] args) {
        JFrame obj = new JFrame();
        int c = 2;
        Gameplay gamePlay = new Gameplay();
        HighScore highScore = new HighScore();
        Menu menu = new Menu("Menu");
        obj.setBounds(10, 10, 800, 800);
        obj.setTitle("Arkanoid");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        A:
        {
            switch (c) {
                case 0:
                    obj.remove(obj.getContentPane());
                    obj.add(menu);
                    break;
                case 1:
                    obj.remove(obj.getContentPane());
                    obj.add(gamePlay);
                    break;
                case 2:
                    obj.remove(obj.getContentPane());
                    obj.add(highScore);
                    break;
            }
            if (c == 3) {
                break A;
            }
        }
    }

}
