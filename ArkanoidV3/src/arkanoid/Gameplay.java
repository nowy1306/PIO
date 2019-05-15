package arkanoid;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class Gameplay extends KeyAdapter implements ActionListener
{

    private boolean play;
    private boolean pause;
    private Boolean fireBall;
    private int score = 0;
    private Timer animationTimer;
    private Timer paddleTimer;
    private int keyCodeLeft;
    private int keyCodeRight;
    private static final int BALL_DELAY = 1;
    private static final int PADDLE_DELAY = 10;

    private Brick[][] bricks;
    private Paddle paddle;

    private List<Ball> ballList;
    private List<BallThread> ballThreadList;

    private List<IBonus> bonusList;

    private Brick curr = null;
    private int numberOfThreads;

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
        ballList = new ArrayList<>();
        ballThreadList = new ArrayList<>();
        bonusList = new ArrayList<>();
        ballList.add(new Ball());
        ballThreadList.add(new BallThread(0));
        numberOfThreads = 1;
        paddle = new Paddle();
        bricks = MapGenerator.generateBricks();
        pause = false;
        fireBall = false;

        animationTimer = new Timer(BALL_DELAY, this);
        paddleTimer = new Timer(PADDLE_DELAY, actionEvent ->
        {
            if (keyCodeRight == 1 && !pause)
            {
                if (paddle.getPosX() < GameplayGui.WIDTH - paddle.getLength())
                {
                    paddle.moveRight();
                }
            }
            if (keyCodeLeft == 1 && !pause)
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

        if (play && !pause)
        {

            if (ballList.size() > ballThreadList.size())
            {
                for (int i = ballThreadList.size(); i < ballList.size(); i++)
                {
                    ballThreadList.add(new BallThread(i));
                    numberOfThreads++;
                }
            }

            for (int i = 0; i < ballThreadList.size(); i++)
            {
                if (ballThreadList.get(i).getState() == Thread.State.NEW)
                {
                    ballThreadList.get(i).start();
                } else if (ballThreadList.get(i).getState() == Thread.State.TERMINATED)
                {
                    numberOfThreads -= 1;
                    ballThreadList.remove(i);
                    ballList.remove(i);
                }

            }


            synchronized (bonusList)
            {
                for (IBonus b : bonusList)
                {
                    b.move();
                    if (b.getActive() && new Rectangle(b.getX(), b.getY(), b.getSize(), b.getSize()).intersects(paddle.getPosX(), paddle.getPosY(), paddle.getLength(), score))
                    {
                        b.UseAbility();
                        b.setActive(false);
                    }
                }
            }
                /*for (int i = bonusList.size()-1; i >= 0; i--) {
                    if(!bonusList.get(i).getActive()){
                        bonusList.remove(i);
                    }*/

            //}


        }

    }

    public void randBonus(int x, int y)
    {
        Random r = new Random();
        int choice = r.nextInt(1000);
        synchronized (bonusList)
        {
            if (choice < 600)
            {
                ;
            } else if (choice < 800)
            {

                bonusList.add(new BigBallBonus(paddle, x, y));

            }
            else if(choice < 1000)
            {
                bonusList.add((new FireBallBonus(this, x, y)));
            }
        }

    }

    public void setFBall(boolean fb)
    {
        fireBall = fb;
    }

    public List<IBonus> getBonuses()
    {
        return bonusList;
    }

    public int getNofT()
    {
        return numberOfThreads;
    }

    public void setPause(boolean p)
    {
        pause = p;
    }

    public boolean getPause()
    {
        return pause;
    }

    public Ball getBall(int i)
    {
        return ballList.get(i);
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
        if (e.getKeyCode() == KeyEvent.VK_P)
        {
            pause = !pause;
        } else if (e.getKeyCode() == KeyEvent.VK_F8)
        {
            ballList.add(new Ball());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keyCodeLeft = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keyCodeRight = 1;
        }

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            keyCodeLeft = 0;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            keyCodeRight = 0;
        }
    }

    public boolean getFBall()
    {
        return fireBall;
    }

    class BallThread extends Thread
    {

        private Ball ball;
        private int ballIndex;

        public BallThread(int i)
        {
            ball = ballList.get(i);
            ballIndex = i;

        }

        @Override
        public void run()
        {
            System.out.println("dzialam");
            while (true)
            {
                try
                {
                    sleep(BALL_DELAY);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (play && !pause)
                {
                    if (new Rectangle((int) ball.getPosX(), (int) ball.getPosY(), Ball.SIZE, Ball.SIZE).intersects(new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getLength(), Paddle.START_PADDLE_THICKNESS)))
                    {

                        double a = ball.getPosX() - paddle.getPosX() + Ball.SIZE / 2.0;
                        double alf_d;

                        if (a <= (paddle.getLength() / 2.0))
                        {
                            alf_d = 30 + 60 * a / (paddle.getLength() / 2);
                            ball.setDir(-1, -1);
                        } else
                        {
                            alf_d = 150 - 60 * a / (paddle.getLength() / 2);
                            ball.setDir(1, -1);
                        }
                        ball.setAngle(alf_d);

                    }

                    Rectangle2D.Double ballCuboid = new Rectangle2D.Double(ball.getPosX(), ball.getPosY(), Ball.SIZE, Ball.SIZE);
                    Brick nearestBrick = null;
                    int nearestBrickRowNumber = 0;
                    int nearestBrickColNumber = 0;
                    double distance = Double.MAX_VALUE;

                    for (int i = 0; i < MapGenerator.ROW_BRICKS_NUMBER; i++)
                    {
                        for (int j = 0; j < MapGenerator.COL_BRICKS_NUMBER; j++)
                        {

                            Brick brick = bricks[i][j];
                            if (brick != null)
                            {
                                Rectangle2D.Double currentBrickCuboid = new Rectangle2D.Double(brick.getXPos(), brick.getYPos(), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);

                                if (ballCuboid.intersects(currentBrickCuboid))
                                {
                                    double currentDistance = Math.sqrt(Math.pow(ball.getCenterXPos() - brick.getCenterXPos(), 2) + Math.pow(ball.getCenterYPos() - brick.getCenterYPos(), 2));
                                    if (currentDistance < distance)
                                    {
                                        distance = currentDistance;
                                        nearestBrick = brick;
                                        nearestBrickRowNumber = i;
                                        nearestBrickColNumber = j;
                                    }
                                }
                            }
                        }
                    }

                    if (nearestBrick != null)
                    {
                        Rectangle2D.Double nearestBrickCuboid = new Rectangle2D.Double(nearestBrick.getXPos(), nearestBrick.getYPos(), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);
                        Rectangle2D intersectedRect = ballCuboid.createIntersection(nearestBrickCuboid);
                        if (!fireBall)
                        {
                            if (intersectedRect.getWidth() >= intersectedRect.getHeight())
                            {
                                try
                                {
                                    if (bricks[nearestBrickRowNumber - 1][nearestBrickColNumber] == null || bricks[nearestBrickRowNumber + 1][nearestBrickColNumber] == null)
                                    {
                                        ball.setDir(ball.getXDir(), -ball.getYDir());
                                    } else
                                    {
                                        ball.setDir(-ball.getXDir(), ball.getYDir());
                                    }
                                }catch (ArrayIndexOutOfBoundsException e)
                                {
                                    ball.setDir(ball.getXDir(), -ball.getYDir());
                                }

                            } else
                            {
                                try
                                {
                                    if (bricks[nearestBrickRowNumber][nearestBrickColNumber - 1] == null || bricks[nearestBrickRowNumber][nearestBrickColNumber + 1] == null)
                                    {
                                        System.out.println("fffffffffff");
                                        //setPause(true);
                                        ball.setDir(-ball.getXDir(), ball.getYDir());
                                    } else
                                    {

                                        ball.setDir(ball.getXDir(), -ball.getYDir());
                                    }
                                }catch (ArrayIndexOutOfBoundsException e)
                                {

                                    ball.setDir(-ball.getXDir(), ball.getYDir());
                                }
                            }
                            nearestBrick.setHealth(nearestBrick.getHealth() - 1);
                            score += 5;
                        } else
                        {
                            score += nearestBrick.getHealth() * 5;
                            nearestBrick.setHealth(nearestBrick.getHealth() - 5);

                        }

                        if (nearestBrick.getHealth() < 1)
                        {
                            randBonus((int) nearestBrick.getXPos(), (int) nearestBrick.getYPos());
                            for (int i = 0; i < MapGenerator.ROW_BRICKS_NUMBER; i++)
                            {
                                for (int j = 0; j < MapGenerator.COL_BRICKS_NUMBER; j++)
                                {
                                    if (bricks[i][j] == nearestBrick)
                                    {
                                        bricks[i][j] = null;
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
                if (ball.getPosY() > GameplayGui.HEIGHT)
                {
                    break;
                }

            }
            System.out.println("koncze");
        }

    }
}
