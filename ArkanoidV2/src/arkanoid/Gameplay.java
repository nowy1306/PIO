package arkanoid;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;

public class Gameplay extends KeyAdapter implements ActionListener
{

    private boolean play;
    private int score = 0;
    private Timer animationTimer;
    private Timer paddleTimer;
    private int keyCode;
    private static final int ANIMATION_DELAY = 1;
    private static final int PADDLE_DELAY = 16;

    private Brick[][] bricks;
    private Paddle paddle;
    private Ball ball;

    private Brick curr = null;

    public Brick get()
    {
        return curr;
    }

    public void set()
    {
        curr = null;
    }

    public Gameplay()
    {
        ball = new Ball();
        paddle = new Paddle();
        bricks = MapGenerator.generateBricks();

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

                if (a <= (Paddle.PADDLE_LENGTH / 2.0))
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

            Rectangle2D.Double ballCuboid = new Rectangle2D.Double(ball.getPosX(), ball.getPosY(), Ball.SIZE, Ball.SIZE);
            Brick nearestBrick = null;
            double distance = Double.MAX_VALUE;

            for(int i = 0; i < MapGenerator.ROW_BRICKS_NUMBER; i++)
            {
                for(int j = 0; j < MapGenerator.COL_BRICKS_NUMBER; j++)
                {

                    Brick brick = bricks[i][j];
                    if(brick != null)
                    {
                        Rectangle2D.Double currentBrickCuboid = new Rectangle2D.Double(brick.getXPos(), brick.getYPos(), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);


                        if (ballCuboid.intersects(currentBrickCuboid))
                        {
                            double currentDistance = Math.sqrt(Math.pow(ball.getCenterXPos() - brick.getCenterXPos(), 2) + Math.pow(ball.getCenterYPos() - brick.getCenterYPos(), 2));
                            if (currentDistance < distance)
                            {
                                //System.out.println("ddd");
                                distance = currentDistance;
                                nearestBrick = brick;
                                //curr = nearestBrick;
                            }
                        }
                    }
                }
            }

            if(nearestBrick != null)
            {
                Rectangle2D.Double nearestBrickCuboid = new Rectangle2D.Double(nearestBrick.getXPos(), nearestBrick.getYPos(), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);
                Rectangle2D intersectedRect = ballCuboid.createIntersection(nearestBrickCuboid);

                if(intersectedRect.getWidth() >= intersectedRect.getHeight())
                {
                    ball.setDir(ball.getXDir(), -ball.getYDir());
                }
                else
                {
                    ball.setDir(-ball.getXDir(), ball.getYDir());
                }
                nearestBrick.setHealth(nearestBrick.getHealth() - 1);

                if(nearestBrick.getHealth() < 1)
                {
                    score += 5;
                    for(int i = 0; i < MapGenerator.ROW_BRICKS_NUMBER; i++)
                    {
                        for (int j = 0; j < MapGenerator.COL_BRICKS_NUMBER; j++)
                        {
                            if(bricks[i][j] == nearestBrick)
                                bricks[i][j] = null;
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

    public Brick[][] getBricks()
    {
        return bricks;
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
