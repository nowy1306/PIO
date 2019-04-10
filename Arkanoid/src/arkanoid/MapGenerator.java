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
                map[i][j] = 1;
            }
        }

    }
    public void draw(Graphics2D g){
        for(int i = 0; i < map.length; i++) {
            for(int j=0; j<map[0].length; j++){
                if(map[i][j] > 0) {
                    g.setColor(Color.white);
                    g.fillRect(j * brickWidth + 62 +j*5, i * brickHeight + 60 +i*5, brickWidth, brickHeight);
                    
                   // g.setStroke(new BasicStroke(5));
                    //g.setColor(Color.);
                    //g.drawRect(j * brickWidth + 57, i * brickHeight + 60, brickWidth+5, brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value, int row, int col){
        map[row][col] = value;
    }
}
