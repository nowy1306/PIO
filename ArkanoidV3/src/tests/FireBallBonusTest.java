package tests;

import arkanoid.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class FireBallBonusTest
{

    @Test
    public void useAbility()
    {
        Gameplay gameplay = new Gameplay();
        FireBallBonus fireBallBonus = new FireBallBonus(gameplay, 100, 100);

        try
        {
            fireBallBonus.UseAbility();
            Thread.sleep(1000);
            assertTrue(gameplay.getFBall());
            Thread.sleep(15000);
            assertFalse(gameplay.getFBall());
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