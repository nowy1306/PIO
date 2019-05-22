package tests;

import arkanoid.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class MultipleBallBonusTest
{

    @Test
    public void useAbility()
    {
        List<Ball> ballList = new ArrayList<>(3);
        for(int i = 0; i < 10; i++)
        {
            ballList.add(new Ball());
        }
        MultipleBallBonus multipleBallBonus = new MultipleBallBonus(ballList, 100, 100);
        multipleBallBonus.UseAbility();
        assertEquals(30, ballList.size());
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