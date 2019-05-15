package arkanoid;

public class BigBallBonus implements IBonus
{
    private int xPos;
    private int yPos;
    private int id;
    private boolean active;
    private static int actionTime = 10000; //in ms
    private Thread abilityThread;
    private static double LENGTH_INCREASE = 1.25;
    private static int counter;

    public BigBallBonus(Paddle paddle, int x, int y)
    {
        xPos = x;
        yPos = y;
        active = true;
        abilityThread = new Thread(() ->
        {
            int prevLength = paddle.getLength();

            counter++;
            paddle.setLength((int) (prevLength * LENGTH_INCREASE));
            try
            {
                Thread.sleep(actionTime);
            }
            catch (InterruptedException e)
            {
                System.err.println("blad!");
            }
            counter--;
            if(counter == 0)
                paddle.setLength(Paddle.START_PADDLE_LENGTH);

        });
    }

    @Override
    public void UseAbility()
    {
        abilityThread.start();
    }

    @Override
    public void move()
    {
        yPos += 2;
    }

    @Override
    public int getID()
    {
        return id;
    }

    @Override
    public int getX()
    {
        return xPos;
    }

    @Override
    public int getY()
    {
        return yPos;
    }

    @Override
    public void setX(int x)
    {
        xPos = x;
    }

    @Override
    public void setY(int y)
    {
        yPos = y;
    }

    @Override
    public boolean getActive()
    {
        return active;
    }

    @Override
    public void setActive(boolean a)
    {
        active = a;
    }


}
