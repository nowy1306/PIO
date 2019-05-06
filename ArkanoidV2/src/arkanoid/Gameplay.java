package arkanoid;

import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.Timer;

public class Gameplay extends KeyAdapter implements ActionListener
{

    private boolean play;
    private int score = 0;
    private Timer animationTimer;
    private Timer paddleTimer;
    private int keyCode;
    private static final int ANIMATION_DELAY = 6;
    private static final int PADDLE_DELAY = 20;

    private MapGenerator map;
    private Paddle paddle;
    private Ball ball;

    public Gameplay()
    {
        ball = new Ball();
        paddle = new Paddle();
        map = new MapGenerator();

        animationTimer = new Timer(ANIMATION_DELAY, this);
        paddleTimer = new Timer(PADDLE_DELAY, actionEvent -> {
            if (keyCode == KeyEvent.VK_RIGHT)
            {
                if (paddle.getPosX() < GameplayGui.WIDTH - Paddle.PADDLE_LENGTH)
                {
                    paddle.moveRight();
                }
            }
            if (keyCode == KeyEvent.VK_LEFT)
            {
                if (paddle.getPosX() > 0)
                {
                    paddle.moveLeft();
                }
            }
        });
        animationTimer.start();
        paddleTimer.start();

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

        if (play)
        {
            if (new Rectangle((int) ball.getPosX(), (int) ball.getPosY(), Ball.SIZE, Ball.SIZE).intersects(new Rectangle(paddle.getPosX(), paddle.getPosY(), Paddle.PADDLE_LENGTH, Paddle.PADDLE_THICKNESS)))
            {

                double a = ball.getPosX() - paddle.getPosX() + Ball.SIZE / 2.0;
                double alf_d;

                if (a <= (Paddle.PADDLE_LENGTH / 2))
                {
                    alf_d = 30 + 60 * a / (Paddle.PADDLE_LENGTH / 2);
                    ball.setDir(-1, -1);
                } else
                {
                    alf_d = 150 - 60 * a / (Paddle.PADDLE_LENGTH / 2);
                    ball.setDir(1, -1);
                }
                ball.setAngle(alf_d);


            }

            for (int i = 0; i < map.map.length; i++)
            {
                for (int j = 0; j < map.map[0].length; j++)
                {
                    if (map.map[i][j] > 0)
                    {
                        int brickX = j * map.brickWidth + 62 + j * 5;
                        int brickY = i * map.brickHeight + 60 + i * 5;
                        int brickWidth = map.brickWidth;
                        int brickHight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHight);
                        Rectangle ballRect = new Rectangle((int) ball.getPosX(), (int) ball.getPosY(), Ball.SIZE, Ball.SIZE);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect))
                        {
                            map.setBrickValue(map.map[i][j] - 1, i, j);
                            score += 5;
                            if (ball.getPosX() + Ball.SIZE - 1 <= brickRect.x || ball.getPosX() + 1 >= brickRect.x + brickRect.width)
                            {
                                ball.setDir(-ball.getXDir(), ball.getYDir());
                            } else
                            {
                                ball.setDir(ball.getXDir(), -ball.getYDir());
                            }
                        }
                    }
                }
            }

            ball.move();
            if (ball.getPosX() < 0)
            {
                ball.setDir(-ball.getXDir(), ball.getYDir());
            }
            if (ball.getPosY() < 0)
            {
                ball.setDir(ball.getXDir(), -ball.getYDir());
            }
            if (ball.getPosX() > GameplayGui.WIDTH - Ball.SIZE)
            {
                ball.setDir(-ball.getXDir(), ball.getYDir());
            }
        }

    }

    public Ball getBall()
    {
        return ball;
    }

    public Paddle getPaddle()
    {
        return paddle;
    }

    public MapGenerator getMap()
    {
        return map;
    }

    public int getScore()
    {
        return score;
    }

    public void start()
    {
        animationTimer.start();
        paddleTimer.start();
    }

    public void stop()
    {
        animationTimer.stop();
        paddleTimer.stop();
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        play = true;
        if(keyCode == 0)
        {
            keyCode = e.getKeyCode();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keyCode = 0;
    }


}
