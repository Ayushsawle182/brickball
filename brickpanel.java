package game;

import java.awt.*;
import java.awt.Graphics2D;

public class brickpanel
{
    public int[][] brick;
    public int brickwidth;
    public int brickheight;

    public brickpanel(int row, int col)
    {
        brick = new int[row][col]; // Initialize the brick array

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                brick[i][j] = 1;
            }
        }
        brickwidth=540/col;
        brickheight=150/row;
    }

    public void setBrick(int value, int r, int c) {
        brick[r][c] = value;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < brick.length; i++)
        {
            for (int j = 0; j < brick[0].length; j++)
            {
                if (brick[i][j] > 0)
                {
                    g.setColor(Color.lightGray);
                    g.fillRect(j * brickwidth+80, i * brickheight+50, brickwidth, brickheight);
                    // bricks visiblity
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j * brickwidth+80, i * brickheight+50, brickwidth, brickheight);

                }
            }
        }
    }
}
