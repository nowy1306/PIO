package arkanoid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameplayGui extends JPanel {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    private Gameplay game;

    private int displayDelay = 8;
    private Timer displayTimer;
    private Image b1Img;
    private Image b2Img;
    private Image b3Img;
    private Image b4Img;
    private Image b5Img;
    private Image paddleImg;
    private Image ballImg;
    private Image fireballImg;
    private Image backgroundImg;
    private int lastPaddleSize;

    public GameplayGui() {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        game = new Gameplay();
        displayTimer = new Timer(displayDelay, actionEvent
                -> {
            repaint();
            if (game.getNofT() == 0) {
                game.stop();
                displayTimer.stop();
                showMessage();
            }
        });
        addKeyListener(game);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        displayTimer.start();
        game.start();
        lastPaddleSize = Paddle.START_PADDLE_LENGTH;
        BufferedImage brickImg;
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\KlocekBialy.bmp"));
            b1Img = brickImg.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad1");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\KlocekJZolty.bmp"));
            b2Img = brickImg.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad2");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\KlocekZolty.bmp"));
            b3Img = brickImg.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad3");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\KlocekPom.bmp"));
            b4Img = brickImg.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad4");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\KlocekCzerwony.bmp"));
            b5Img = brickImg.getScaledInstance((int) Brick.BRICK_WIDTH, (int) Brick.BRICK_HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("blad5");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\paddle.bmp"));
            paddleImg = brickImg.getScaledInstance(Paddle.START_PADDLE_LENGTH, Paddle.START_PADDLE_THICKNESS, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("bladPaddle");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\ball.png"));
            ballImg = brickImg.getScaledInstance(Ball.SIZE, Ball.SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("bladPaddle");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\fireball.png"));
            fireballImg = brickImg.getScaledInstance(Ball.SIZE, Ball.SIZE, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("bladPaddle");
        }
        try {
            brickImg = ImageIO.read(new File("src\\Graphics\\background.png"));
            backgroundImg = brickImg.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("bladback");
        }

    }

    @Override
    public void paintComponent(Graphics g) {
        //tło
        g.drawImage(backgroundImg, 0, 0, null);


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
        if (lastPaddleSize != game.getPaddle().getLength()) {
            lastPaddleSize = game.getPaddle().getLength();
            paddleImg = paddleImg.getScaledInstance(lastPaddleSize, Paddle.START_PADDLE_THICKNESS, Image.SCALE_FAST);

        }
        g.drawImage(paddleImg, game.getPaddle().getPosX(), game.getPaddle().getPosY(), null);

        //wynik
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 35));
        g.drawString("" + game.getScore(), 715, 45);

        synchronized (game.getBonuses()) {
            for (IBonus b : game.getBonuses()) {
                if (b.getActive()) {
                    g.setColor(Color.yellow);
                    g.fillOval(b.getX(), b.getY(), b.getSize(), b.getSize());
                }
            }
        }

        // piłka
        for (int i = 0; i < game.getNofT(); i++) {


            if (game.getFBall()) {
                g.drawImage(fireballImg, (int) game.getBall(i).getPosX(), (int) game.getBall(i).getPosY(), null);
            } else {
                g.drawImage(ballImg, (int) game.getBall(i).getPosX(), (int) game.getBall(i).getPosY(), null);

            }
        }

        if (game.getNofT() == 0 && !game.isWin()) {

            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("GAME OVER Score: " + game.getScore(), 150, 320);

        }
        if (game.getNofBrics() == 0) {
            g.setColor(Color.PINK);
            g.setFont(new Font("serif", Font.ITALIC, 50));
            g.drawString("YOU WIN!", 150, 320);
            g.setFont(new Font("serif", Font.ROMAN_BASELINE, 14));
            g.drawString("Let the balls fall down to save the score", 450, 330);
            game.setIsWin(true);
        }

        g.dispose();
    }

    public void showMessage() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        final JOptionPane optionPane = new JOptionPane("Do you want to save your score?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
        final JDialog dialog = new JDialog(frame, "Wanna join the leaderboard?", true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(frame);

        optionPane.addPropertyChangeListener((PropertyChangeEvent e)
                -> {
            String prop = e.getPropertyName();

            if (dialog.isVisible()
                    && (e.getSource() == optionPane)
                    && (prop.equals(JOptionPane.VALUE_PROPERTY))) {
                dialog.setVisible(false);
            }
        });
        dialog.pack();
        dialog.setVisible(true);

        int value = (Integer) optionPane.getValue();
        if (value == JOptionPane.YES_OPTION) {
            displayPlayerScoreIn();
        } else if (value == JOptionPane.NO_OPTION) {
            displayMenu();
        }

    }

    public void displayPlayerScoreIn() {

        setVisible(false);
        EventQueue.invokeLater(()
                -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            PlayerScoreInGui gui = new PlayerScoreInGui(game.getScore(), game.isWin());
            frame.add(gui);
            gui.requestFocus();
            frame.pack();
        });

    }

    public void displayMenu() {

        setVisible(false);
        EventQueue.invokeLater(()
                -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            Menu menu = new Menu();
            frame.add(menu);
            menu.requestFocus();
            frame.pack();
        });

    }
}
