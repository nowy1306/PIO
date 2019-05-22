package tests;

import arkanoid.BigBallBonus;
import arkanoid.IBonus;
import arkanoid.Paddle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;



public class BigBallBonusTest
{


    @Test
    public void useAbilityTest()
    {
        Paddle paddle = new Paddle();
        IBonus bigBallBonus = new BigBallBonus(paddle, 100, 100);

        bigBallBonus.UseAbility();
        try
        {
            Thread.sleep(1000);
            assertEquals(180, paddle.getLength(), 1e-6);
            Thread.sleep(10000);
            assertEquals(120, paddle.getLength(), 1e-6);
        }catch (InterruptedException e)
        {
            fail();
        }

    }



    @Test
    public void moveTest()
    {
        double[] actual = new double[2];
        double[] expected = new double[2];
        IBonus bigBallBonus = new BigBallBonus(new Paddle(), 100, 100);
        bigBallBonus.move();
        expected[0] = 100;
        expected[1] = 101;
        actual[0] = bigBallBonus.getX();
        actual[1] = bigBallBonus.getY();
        assertArrayEquals(expected, actual, 1e-6);
    }
}