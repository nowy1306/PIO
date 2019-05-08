package arkanoid;

public class Ball
{
    private static final double START_X_POS = 420.0;
    private static final double START_Y_POS = 450.0;
    private static final double START_X_DIR = -1.0;
    private static final double START_Y_DIR = -1.0;
    private static final double BALL_SPEED = 2;
    private static final double START_ANGLE = 45.0;
    private double angle;
    private double xPos;
    private double yPos;
    private double xDir;
    private double yDir;
    public static final int SIZE = 20;

    public Ball()
    {
        xPos = START_X_POS;
        yPos = START_Y_POS;
        xDir = START_X_DIR;
        yDir = START_Y_DIR;
        angle = START_ANGLE;
    }

    public void setPos(double x, double y)
    {
        xPos = x;
        yPos = y;
    }

    public void setAngle(double angle)
    {
        this.angle = angle;
    }

    public double getAngle()
    {
        return angle;
    }


    public void setDir(double xDir, double yDir)
    {
        this.xDir = xDir;
        this.yDir = yDir;
    }

    public double getXDir()
    {
        return xDir;
    }

    public double getYDir()
    {
        return yDir;
    }

    public double getPosX()
    {
        return xPos;
    }

    public double getPosY()
    {
        return yPos;
    }

    public void move()
    {
        xPos += xDir * BALL_SPEED * Math.cos(Math.toRadians(angle));
        yPos += yDir * BALL_SPEED * Math.sin(Math.toRadians(angle));
    }

    public double getCenterXPos()
    {
        return xPos + SIZE / 2.0;
    }

    public double getCenterYPos()
    {
        return yPos + SIZE / 2.0;
    }
}
