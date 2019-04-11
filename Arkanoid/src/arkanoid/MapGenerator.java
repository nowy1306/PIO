package arkanoid;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

    public int map[][] = new int[10][15];
    public int brickWidth = 40;
    public int brickHeight = 25;

    public MapGenerator(int l_map[][]) {
        map = l_map;
    }

    public MapGenerator() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 4;
            }
        }
    }

    public int getScoreLvl() {
        int scoreLvl = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                scoreLvl += map[i][j];
            }
        }
        return scoreLvl;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    switch (map[i][j]) {
                        case 1:
                            g.setColor(Color.white);
                            break;
                        case 2:
                            g.setColor(Color.yellow);
                            break;
                        case 3:
                            g.setColor(Color.orange);
                            break;
                        case 4:
                            g.setColor(Color.red);
                            break;
                    }

                    g.fillRect(j * brickWidth + 62 + j * 5, i * brickHeight + 60 + i * 5, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
