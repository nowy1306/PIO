package tests;

import arkanoid.Paddle;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaddleTest
{

    @Test
    public void moveRightTest()
    {
        double[] expected = new double[2];
        double[] actual = new double[2];
        Paddle paddle = new Paddle();
        paddle.moveRight();
        actual[0] = paddle.getPosX();
        actual[1] = paddle.getPosY();
        expected[0] = 330;
        expected[1] = 720;
        assertArrayEquals(expected, actual, 1e-6);

    }

    @Test
    public void moveLeftTest()
    {
        double[] expected = new double[2];
        double[] actual = new double[2];
        Paddle paddle = new Paddle();
        paddle.moveLeft();
        actual[0] = paddle.getPosX();
        actual[1] = paddle.getPosY();
        expected[0] = 290;
        expected[1] = 720;
        assertArrayEquals(expected, actual, 1e-6);
    }
}