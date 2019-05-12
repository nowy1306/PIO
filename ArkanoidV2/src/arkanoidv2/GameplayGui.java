package arkanoidv2;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Clock;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameplayGui extends JPanel {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    Gameplay game;

    private int fpsDelay = 8;
    Timer fpsTimer;
    BufferedImage brick1Img;
    BufferedImage brick2Img;
    BufferedImage brick3Img;
    BufferedImage brick4Img;
    BufferedImage brick5Img;
    Image b1Img;
    Image b2Img;
    Image b3Img;
    Image b4Img;
    Image b5Img;

    public GameplayGui() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        game = new Gameplay();
        fpsTimer = new Timer(fpsDelay, actionEvent -> {
            repaint();
        });
        addKeyListener(game);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        fpsTimer.start();
        game.start();
        brick1Img = null;
        brick2Img = null;
        brick3Img = null;
        brick4Img = null;
        brick5Img = null;
        try {
            brick1Img = ImageIO.read(new File("src\\Graphics\\KlocekBialy.bmp"));
            b1Img = brick1Img.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad1");
        }
        try {
            brick2Img = ImageIO.read(new File("src\\Graphics\\KlocekJZolty.bmp"));
            b2Img = brick2Img.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad2");
        }
        try {
            brick3Img = ImageIO.read(new File("src\\Graphics\\KlocekZolty.bmp"));
            b3Img = brick3Img.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad3");
        }
        try {
            brick4Img = ImageIO.read(new File("src\\Graphics\\KlocekPom.bmp"));
            b4Img = brick4Img.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad4");
        }
        try {
            brick5Img = ImageIO.read(new File("src\\Graphics\\KlocekCzerwony.bmp"));
            b5Img = brick5Img.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad5");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //tło
        g.setColor(Color.black);
        g.fillRect(0, 0, GameplayGui.WIDTH, GameplayGui.HEIGHT);

        //map
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.RED);
        Brick[][] bricks = game.getBricks();
        for (int i = 0; i < MapGenerator.ROW_BRICKS_NUMBER; i++) {
            for (int j = 0; j < MapGenerator.COL_BRICKS_NUMBER; j++) {
                Brick brick = bricks[i][j];
                if (brick != null) {
                    switch (brick.getHealth()) {
                        case 1:
                            g2.drawImage(b1Img, (int) brick.getXPos(), (int) brick.getYPos(), null);
                            break;
                        case 2:
                            g2.drawImage(b2Img, (int) brick.getXPos(), (int) brick.getYPos(), null);
                            break;
                        case 3:
                            g2.drawImage(b3Img, (int) brick.getXPos(), (int) brick.getYPos(), null);
                            break;
                        case 4:
                            g2.drawImage(b4Img, (int) brick.getXPos(), (int) brick.getYPos(), null);
                            break;
                        case 5:
                            g2.drawImage(b5Img, (int) brick.getXPos(), (int) brick.getYPos(), null);
                            break;
                    }

                }
            }
        }

        // platforma
        g.setColor(Color.blue);
        g.fillRect(game.getPaddle().getPosX(), game.getPaddle().getPosY(), Paddle.PADDLE_LENGTH, Paddle.PADDLE_THICKNESS);

        //wynik
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString("" + game.getScore(), 715, 45);

        // piłka
        for (int i = 0; i < game.getNofT(); i++) {
            g.setColor(Color.CYAN);
            g.fillOval((int) game.getBall(i).getPosX(), (int) game.getBall(i).getPosY(), game.getBall(i).SIZE, game.getBall(i).SIZE);

        }

        if (game.getNofT() == 0) {
            game.stop();

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("GAME OVER Score: " + game.getScore(), 150, 420);
        }

        g.dispose();
    }

}
