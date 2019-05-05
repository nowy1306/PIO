package arkanoid;

import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.Timer;

public class Gameplay extends KeyAdapter implements ActionListener
{

    public boolean play = true;
    public int score = 0;
    public Timer timer;
    private Timer paddleTimer;
    private int keyCode;
    private int delay = 6;
    private static final int PADDLE_DELAY = 20;
    private static final double BALL_SPEED = 5;

    private MapGenerator map;
    private Paddle paddle;
    private Ball ball;

    public Gameplay()
    {
        ball = new Ball();
        paddle = new Paddle();
        map = new MapGenerator();

        timer = new Timer(delay, this);
        paddleTimer = new Timer(PADDLE_DELAY, actionEvent -> {
            if (keyCode == KeyEvent.VK_RIGHT)
            {
                if (paddle.getPosX() >= 800 - Paddle.PADDLE_LENGTH)
                {
                    paddle.setPos(800 - Paddle.PADDLE_LENGTH);
                } else
                {
                    paddle.moveRight();
                }
            }
            if (keyCode == KeyEvent.VK_LEFT)
            {
                if (paddle.getPosX() < 10)
                {
                    paddle.setPos(10);
                } else
                {
                    paddle.moveLeft();
                }
            }
        });
        timer.start();
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
                double alf_r;
                if (a <= (Paddle.PADDLE_LENGTH / 2))
                {
                    alf_d = 30 + 60 * a / (Paddle.PADDLE_LENGTH / 2);
                    alf_r = Math.toRadians(alf_d);
                    ball.setDir((-1) * BALL_SPEED * Math.cos(alf_r), (-1) * BALL_SPEED * Math.sin(alf_r));
                } else
                {
                    alf_d = 150 - 60 * a / (Paddle.PADDLE_LENGTH / 2);
                    alf_r = Math.toRadians(alf_d);
                    ball.setDir(BALL_SPEED * Math.cos(alf_r), (-1) * BALL_SPEED * Math.sin(alf_r));
                }

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
                                ball.setDir(ball.ballXdir * (-1), ball.ballYdir);
                            } else
                            {
                                ball.setDir(ball.ballXdir, ball.ballYdir * (-1));
                            }
                        }
                    }
                }
            }
            ball.move();
            if (ball.getPosX() < 10)
            {
                ball.setDir(ball.ballXdir * (-1), ball.ballYdir);
            }
            if (ball.getPosY() < 10)
            {
                ball.setDir(ball.ballXdir, ball.ballYdir * (-1));
            }
            if (ball.getPosX() > 790 - Ball.SIZE)
            {
                ball.setDir(ball.ballXdir * (-1), ball.ballYdir);
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

    @Override
    public void keyPressed(KeyEvent e)
    {
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
