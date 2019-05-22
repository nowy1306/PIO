package tests;

import arkanoid.Ball;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BallTest
{

    @Test
    public void moveTest()
    {
        double[] actual = new double[2];
        double[] expected = new double[2];
        Ball ball = new Ball();
        ball.move();
        expected[0] = 419.4;
        expected[1] = 448.960769;
        actual[0] = ball.getPosX();
        actual[1] = ball.getPosY();
        assertArrayEquals(expected, actual, 1e-6);

    }
}