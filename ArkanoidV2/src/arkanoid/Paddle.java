package arkanoid;

public class Paddle
{

    private int paddleposX = 310;
    private int paddleposY = 720;
    public static final int PADDLE_LENGTH = 180;
    public static final int PADDLE_THICKNESS = 10;

    public void setPos(int x)
    {
        paddleposX = x;
    }

    public int getPosX()
    {
        return paddleposX;
    }

    public int getPosY()
    {
        return paddleposY;
    }

    public void moveRight()
    {
        paddleposX += 20;
    }

    public void moveLeft()
    {
        paddleposX -= 20;
    }

}
