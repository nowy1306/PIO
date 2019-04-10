package arkanoid;

class Ball {

    private int ballposX = 120;
    private int ballposY = 550;
    public int ballXdir = -1;
    public int ballYdir = -2;
    private int ballSize = 20;

    public void setPos(int x, int y) {
        ballposX = x;
        ballposY = y;
    }

    public void setSize(int size) {
        ballSize = size;
    }

    public void setDir(int dirX, int dirY) {
        ballXdir = dirX;
        ballYdir = dirY;
    }

    public int getPosX() {
        return ballposX;
    }

    public int getPosY() {
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
