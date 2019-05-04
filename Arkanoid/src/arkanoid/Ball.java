package arkanoid;

public class Ball {

    private double ballposX = 120;
    private double ballposY = 550;
    public double ballXdir = -1;
    public double ballYdir = -2;
    private int ballSize = 20;

    public void setPos(double x, double y) {
        ballposX = x;
        ballposY = y;
    }

    public void setSize(int size) {
        ballSize = size;
    }

    public void setDir(double dirX, double dirY) {
        ballXdir = dirX;
        ballYdir = dirY;
    }

    public double getPosX() {
        return ballposX;
    }

    public double getPosY() {
        return ballposY;
    }

    public int getSize() {
        return ballSize;
    }

    public void move() {
        ballposX += ballXdir;
        ballposY += ballYdir;
    }
}
