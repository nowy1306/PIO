package arkanoid;

class Paddle {

    private int paddleposX = 310;
    private int paddleposY = 720;
    private int paddleSize = 120;
    private int thick = 10;

    public void setPos(int x) {
        paddleposX = x;
    }

    public void setSize(int size) {
        paddleSize = size;
    }

    public int getPosX() {
        return paddleposX;
    }

    public int getPosY() {
        return paddleposY;
    }

    public int getSize() {
        return paddleSize;
    }
    public int getThick() {
        return thick;
    }

}
