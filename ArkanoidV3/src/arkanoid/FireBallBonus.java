package arkanoid;

import java.util.logging.Level;
import java.util.logging.Logger;


public class FireBallBonus implements IBonus {

    private boolean active;
    private int posX;
    private int posY;
    private int id;
    private int duration;
    Boolean fireBall;
    private Thread action;
    private Gameplay game;


    public FireBallBonus(Gameplay g, int x, int y) {
       // fireBall = fb;
        game =g;
        active = true;
        posX = x;
        posY = y;
        id = 1;
        duration = 15000;
        action = new Thread(){
            @Override
            public void run(){
                g.setFBall(true);
                try {
                    sleep(duration);
                } catch (InterruptedException ex) {
                    Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                }
                g.setFBall(false);
            }
        };

    }

    @Override
    public void UseAbility() {
        action.start();
    }

    @Override
    public void move() {
        posY += 1;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getX() {
        return posX;
    }

    @Override
    public int getY() {
        return posY;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean a) {
        active = a;
    }

    @Override
    public void setX(int x) {
    }

    @Override
    public void setY(int y) {
    }

}
