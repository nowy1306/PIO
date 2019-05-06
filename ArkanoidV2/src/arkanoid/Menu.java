package arkanoid;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel
{

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;

    public Menu()
    {
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        b1 = new JButton("Start Game");
        b2 = new JButton("High Score");
        b3 = new JButton("Exit Game");

        b1.setAlignmentX(CENTER_ALIGNMENT);
        b1.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        b2.setAlignmentX(CENTER_ALIGNMENT);
        b2.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        b3.setAlignmentX(CENTER_ALIGNMENT);
        b3.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        add(Box.createRigidArea(new Dimension(0, (HEIGHT - 3 * BUTTON_HEIGHT) / 2)));
        add(b1);
        add(b2);
        add(b3);

        b1.addActionListener(actionEvent ->
        {
            displayArkanoidBoard();
        });
        b2.addActionListener(actionEvent ->
        {
            displayHighScore();
        });
        b3.addActionListener(actionEvent ->
        {
            System.exit(0);
        });

    }

    public void displayArkanoidBoard()
    {

        setVisible(false);
        EventQueue.invokeLater(()->
        {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            GameplayGui gui = new GameplayGui();
            frame.add(gui);
            gui.requestFocus();
            frame.pack();
        });

    }

    public void displayHighScore()
    {
        setVisible(false);
        EventQueue.invokeLater(()->
        {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            HighScore highScore = new HighScore();
            frame.add(highScore);
            highScore.requestFocus();
            frame.pack();
        });

    }

}
