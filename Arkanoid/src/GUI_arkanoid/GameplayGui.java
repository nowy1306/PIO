/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_arkanoid;

import arkanoid.Gameplay;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author witol
 */
public class GameplayGui extends JPanel implements ActionListener {

    Gameplay game;
    private int fpsDelay = 5;
    Timer fpsTimer;
    JFrame obj;

    public GameplayGui(JFrame o) {
        obj = o;
        game = new Gameplay();
        fpsTimer = new Timer(fpsDelay, this);
        addKeyListener(game);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        fpsTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        //tło
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 800);

        //granice
        g.setColor(Color.gray);
        g.fillRect(0, 0, 10, 800);
        g.fillRect(0, 0, 800, 10);
        g.fillRect(784, 0, 10, 800);

        //map
        game.map.draw((Graphics2D) g);
        // platforma
        g.setColor(Color.blue);
        g.fillRect(game.paddle.getPosX(), game.paddle.getPosY(), game.paddle.getSize(), game.paddle.getThick());

        //wynik
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString("" + game.score, 715, 45);

        // piłka
        g.setColor(Color.CYAN);
        g.fillOval((int) game.ball_1.getPosX(), (int) game.ball_1.getPosY(), game.ball_1.getSize(), game.ball_1.getSize());

        if (game.ball_1.getPosY() > 800) {
            game.play = false;
            game.ball_1.setDir(0, 0);
            /*g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("GAME OVER Score: " + game.score, 150, 420);
            */
            game.timer.stop();
            this.setVisible(false);
            obj.add(new PlayersScoreIn(obj, game.score) );
            obj.repaint();
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

}
