package arkanoidv2;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class Gameplay extends KeyAdapter implements ActionListener {

    private boolean play;
    private boolean pause;
    private int score = 0;
    private Timer animationTimer;
    private Timer paddleTimer;
    private int keyCodeLeft;
    private int keyCodeRight;
    private static final int BALL_DELAY = 4;
    private static final int PADDLE_DELAY = 20;

    private Brick[][] bricks;
    private Paddle paddle;

    private List<Ball> ballList;
    private List<BallThread> ballThreadList;

    private Brick curr = null;
    private int numberOfThreads;

    public Brick get() {
        return curr;
    }

    public void set() {
        curr = null;
    }

    public Gameplay() {
        ballList = new ArrayList<>();
        ballThreadList = new ArrayList<>();
        ballList.add(new Ball());
        ballThreadList.add(new BallThread(0));
        numberOfThreads = 1;
        paddle = new Paddle();
        bricks = MapGenerator.generateBricks();
        pause = false;

        animationTimer = new Timer(BALL_DELAY, this);
        paddleTimer = new Timer(PADDLE_DELAY, actionEvent -> {
            if (keyCodeRight == 1 && !pause) {
                if (paddle.getPosX() < GameplayGui.WIDTH - Paddle.PADDLE_LENGTH) {
                    paddle.moveRight();
                }
            }
            if (keyCodeLeft == 1 && !pause) {
                if (paddle.getPosX() > 0) {
                    paddle.moveLeft();
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (play && !pause) {

            if (ballList.size() > ballThreadList.size()) {
                for (int i = ballThreadList.size(); i < ballList.size(); i++) {
                    ballThreadList.add(new BallThread(i));
                    numberOfThreads++;
                }
            }

            for (int i = 0; i < ballThreadList.size(); i++) {
                if (ballThreadList.get(i).getState() == Thread.State.NEW) {
                    ballThreadList.get(i).start();
                } else if (ballThreadList.get(i).getState() == Thread.State.TERMINATED) {
                    numberOfThreads -= 1;
                    ballThreadList.remove(i);
                    ballList.remove(i);
                }

            }

        }

    }

    public int getNofT() {
        return numberOfThreads;
    }

    public void setPause(boolean p) {
        pause = p;
    }

    public boolean getPause() {
        return pause;
    }

    public Ball getBall(int i) {
        return ballList.get(i);
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Brick[][] getBricks() {
        return bricks;
    }

    public int getScore() {
        return score;
    }

    public void start() {
        animationTimer.start();
        paddleTimer.start();

    }

    public void stop() {
        animationTimer.stop();
        paddleTimer.stop();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        play = true;
        if (e.getKeyCode() == KeyEvent.VK_P) {
            pause = !pause;
        } else if (e.getKeyCode() == KeyEvent.VK_F8) {
            ballList.add(new Ball());
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyCodeLeft = 1;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyCodeRight = 1;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyCodeLeft = 0;
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyCodeRight = 0;
        }
    }

    class BallThread extends Thread {

        private Ball ball;
        private int ballIndex;

        public BallThread(int i) {
            ball = ballList.get(i);
            ballIndex = i;

        }

        @Override
        public void run() {
            System.out.println("dzialam");
            while (true) {
                try {
                    sleep(BALL_DELAY);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Gameplay.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (play && !pause) {
                    if (new Rectangle((int) ball.getPosX(), (int) ball.getPosY(), Ball.SIZE, Ball.SIZE).intersects(new Rectangle(paddle.getPosX(), paddle.getPosY(), Paddle.PADDLE_LENGTH, Paddle.PADDLE_THICKNESS))) {

                        double a = ball.getPosX() - paddle.getPosX() + Ball.SIZE / 2.0;
                        double alf_d;

                        if (a <= (Paddle.PADDLE_LENGTH / 2.0)) {
                            alf_d = 30 + 60 * a / (Paddle.PADDLE_LENGTH / 2);
                            ball.setDir(-1, -1);
                        } else {
                            alf_d = 150 - 60 * a / (Paddle.PADDLE_LENGTH / 2);
                            ball.setDir(1, -1);
                        }
                        ball.setAngle(alf_d);

                    }

                    Rectangle2D.Double ballCuboid = new Rectangle2D.Double(ball.getPosX(), ball.getPosY(), Ball.SIZE, Ball.SIZE);
                    Brick nearestBrick = null;
                    double distance = Double.MAX_VALUE;

                    for (int i = 0; i < MapGenerator.ROW_BRICKS_NUMBER; i++) {
                        for (int j = 0; j < MapGenerator.COL_BRICKS_NUMBER; j++) {

                            Brick brick = bricks[i][j];
                            if (brick != null) {
                                Rectangle2D.Double currentBrickCuboid = new Rectangle2D.Double(brick.getXPos(), brick.getYPos(), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);

                                if (ballCuboid.intersects(currentBrickCuboid)) {
                                    double currentDistance = Math.sqrt(Math.pow(ball.getCenterXPos() - brick.getCenterXPos(), 2) + Math.pow(ball.getCenterYPos() - brick.getCenterYPos(), 2));
                                    if (currentDistance < distance) {
                                        distance = currentDistance;
                                        nearestBrick = brick;
                                    }
                                }
                            }
                        }
                    }

                    if (nearestBrick != null) {
                        Rectangle2D.Double nearestBrickCuboid = new Rectangle2D.Double(nearestBrick.getXPos(), nearestBrick.getYPos(), Brick.BRICK_WIDTH, Brick.BRICK_HEIGHT);
                        Rectangle2D intersectedRect = ballCuboid.createIntersection(nearestBrickCuboid);

                        if (intersectedRect.getWidth() >= intersectedRect.getHeight()) {
                            ball.setDir(ball.getXDir(), -ball.getYDir());
                        } else {
                            ball.setDir(-ball.getXDir(), ball.getYDir());
                        }
                        nearestBrick.setHealth(nearestBrick.getHealth() - 1);
                        score += 5;
                        if (nearestBrick.getHealth() < 1) {
                            
                            for (int i = 0; i < MapGenerator.ROW_BRICKS_NUMBER; i++) {
                                for (int j = 0; j < MapGenerator.COL_BRICKS_NUMBER; j++) {
                                    if (bricks[i][j] == nearestBrick) {
                                        bricks[i][j] = null;
                                    }
                                }
                            }
                        }
                    }

                    ball.move();

                    if (ball.getPosX() < 0) {
                        ball.setDir(-ball.getXDir(), ball.getYDir());
                    }
                    if (ball.getPosY() < 0) {
                        ball.setDir(ball.getXDir(), -ball.getYDir());
                    }
                    if (ball.getPosX() > GameplayGui.WIDTH - Ball.SIZE) {
                        ball.setDir(-ball.getXDir(), ball.getYDir());

                    }
                }
                if (ball.getPosY() > GameplayGui.HEIGHT) {
                    break;
                }

            }
            System.out.println("koncze");
        }

    }
}
