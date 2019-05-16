/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.util.List;

/**
 *
 * @author mjgaj
 */
public class MultipleBallBonus implements IBonus {

    private int xPos;
    private int yPos;
    private int id;
    private boolean active;
    private int numberOfBalls;
    private List<Ball> ballList;

    public MultipleBallBonus(List<Ball> list, int x, int y) {
        xPos = x;
        yPos = y;
        ballList = list;
        numberOfBalls = ballList.size();
        active = true;
    }

    @Override
    public void UseAbility() {
        for (int i = 0; i < numberOfBalls; i++) {
            Ball ball = ballList.get(i);
            ballList.add(new Ball(ball.getPosX(), ball.getPosY(), ball.getXDir(), ball.getYDir(), ball.getAngle() + 11));
            ballList.add(new Ball(ball.getPosX(), ball.getPosY(), ball.getXDir(), ball.getYDir(), ball.getAngle() - 11));
        }
    }

    @Override
    public void move() {
        yPos += 1;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }

    @Override
    public void setX(int x) {
        xPos = x;
    }

    @Override
    public void setY(int y) {
        yPos = y;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean a) {
        active = a;
    }

}
