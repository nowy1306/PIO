package arkanoid;

public class Ball
{

    private double ballposX = 120;
    private double ballposY = 550;
    public double ballXdir = -1;
    public double ballYdir = -2;
    public static final int SIZE = 20;

    public void setPos(double x, double y)
    {
        ballposX = x;
        ballposY = y;
    }


    public void setDir(double dirX, double dirY)
    {
        ballXdir = dirX;
        ballYdir = dirY;
    }

    public double getPosX()
    {
        return ballposX;
    }

    public double getPosY()
    {
        return ballposY;
    }

    public void move()
    {
        ballposX += ballXdir;
        ballposY += ballYdir;
    }
}
