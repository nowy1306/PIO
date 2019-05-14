package arkanoid;

import javax.swing.*;
import java.awt.*;

public class ArkanoidFrame extends JFrame
{
    private static final int X_POS = 100;
    private static final int Y_POS = 100;

    public ArkanoidFrame(String title)
    {
        super(title);
        setLocation(new Point(X_POS, Y_POS));
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
