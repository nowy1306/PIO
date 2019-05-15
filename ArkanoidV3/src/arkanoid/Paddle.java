package arkanoid;

public class Paddle
{

    private int paddleposX = 310;
    private int paddleposY = 720;
    private int length;
    public static final int START_PADDLE_LENGTH = 200;
    public static final int START_PADDLE_THICKNESS = 10;


    {
        length = START_PADDLE_LENGTH;
    }

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

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

}
