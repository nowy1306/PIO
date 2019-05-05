package arkanoid;

import java.awt.*;
import javax.swing.*;

public class GameplayGui extends JPanel
{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    Gameplay game;
    private int fpsDelay = 20;
    Timer fpsTimer;

    public GameplayGui()
    {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        game = new Gameplay();
        fpsTimer = new Timer(fpsDelay, actionEvent ->
        {
            repaint();
        });
        addKeyListener(game);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        fpsTimer.start();
        game.start();

    }

    @Override
    public void paintComponent(Graphics g)
    {
        //tło
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 800);

        //granice
        /*
        g.setColor(Color.gray);
        g.fillRect(0, 0, 10, 800);
        g.fillRect(0, 0, 800, 10);
        g.fillRect(784, 0, 10, 800);
         */

        //map
        game.getMap().draw((Graphics2D) g);
        // platforma
        g.setColor(Color.blue);
        g.fillRect(game.getPaddle().getPosX(), game.getPaddle().getPosY(), Paddle.PADDLE_LENGTH, Paddle.PADDLE_THICKNESS);

        //wynik
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString("" + game.getScore(), 715, 45);

        // piłka
        g.setColor(Color.CYAN);
        g.fillOval((int) game.getBall().getPosX(), (int) game.getBall().getPosY(), Ball.SIZE, Ball.SIZE);

        if (game.getBall().getPosY() > GameplayGui.HEIGHT)
        {
            game.stop();
            game.getBall().setDir(0, 0);
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("GAME OVER Score: " + game.getScore(), 150, 420);
        }

        g.dispose();
    }


}
