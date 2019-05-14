package arkanoidv2;

public class MapGenerator
{
    public static final int ROW_BRICKS_NUMBER = 7;
    public static final int COL_BRICKS_NUMBER = 14;
    public static final double SPACE = 5;

    private MapGenerator(){};

    static Brick[][] generateBricks()
    {
        Brick[][] bricks = new Brick[ROW_BRICKS_NUMBER][COL_BRICKS_NUMBER];

        for(int i = 0; i < ROW_BRICKS_NUMBER; i++)
        {
            for (int j = 0; j < COL_BRICKS_NUMBER; j++)
            {
                bricks[i][j] = new Brick(SPACE + (SPACE + Brick.BRICK_WIDTH) * j, SPACE + (SPACE + Brick.BRICK_HEIGHT) * i, 5);
            }
        }

        return bricks;
    }
}
