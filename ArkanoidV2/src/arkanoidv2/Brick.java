package arkanoidv2;

public class Brick {

    public static final double BRICK_WIDTH;
    public static final double BRICK_HEIGHT = 20;
    private double posX;
    private double posY;
    private int health;

    static
    {
        BRICK_WIDTH = (GameplayGui.WIDTH - (MapGenerator.COL_BRICKS_NUMBER + 1) * MapGenerator.SPACE) / MapGenerator.COL_BRICKS_NUMBER;
    }

    public Brick(double x, double y, int hp) {
        posX = x;
        posY = y;
        health = hp;

    }

    public void setXPos(double x) {
        posX = x;
    }

    public double getXPos() {
        return posX;
    }

    public void setYPos(double y) {
        posY = y;
    }

    public double getYPos() {
        return posY;
    }

    public void setHealth(int h) {
        health = h;
    }

    public int getHealth() {
        return health;
    }

    public double getCenterXPos()
    {
        return posX + BRICK_WIDTH / 2;
    }

    public double getCenterYPos()
    {
        return posX + BRICK_HEIGHT / 2;
    }

}
