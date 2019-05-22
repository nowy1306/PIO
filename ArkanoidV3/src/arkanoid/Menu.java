package arkanoid;

import static arkanoid.GameplayGui.HEIGHT;
import static arkanoid.GameplayGui.WIDTH;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Menu extends JPanel
{

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private static final Color BUTTON_BG_COLOR;
    private static final Color BUTTON_BORDER_COLOR;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private Image backgroundImg;
    
    static
    {
        BUTTON_BG_COLOR = new Color(200, 200, 200);
        BUTTON_BORDER_COLOR = new Color(150, 150, 150);
    }

    public Menu()
    {
        setFocusable(true);

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        b1 = new JButton("Start Game");
        b2 = new JButton("High Score");
        b3 = new JButton("Exit Game");
        try {
            BufferedImage background;
            background = ImageIO.read(new File("src\\Graphics\\backgroundmenu.png"));
            backgroundImg = background.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            System.err.println("bladbackM");
        }
        
        
        
        
        b1.setBorder(BorderFactory.createLineBorder(BUTTON_BORDER_COLOR));
        b2.setBorder(BorderFactory.createLineBorder(BUTTON_BORDER_COLOR));
        b3.setBorder(BorderFactory.createLineBorder(BUTTON_BORDER_COLOR));

        MouseListener mouseAction = new MouseAction();
        b1.addMouseListener(mouseAction);
        b2.addMouseListener(mouseAction);
        b3.addMouseListener(mouseAction);

         

        b1.setAlignmentX(CENTER_ALIGNMENT);
        b1.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        b2.setAlignmentX(CENTER_ALIGNMENT);
        b2.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        b3.setAlignmentX(CENTER_ALIGNMENT);
        b3.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));

        add(Box.createRigidArea(new Dimension(0, (HEIGHT - 3 * BUTTON_HEIGHT) / 2)));
        add(b1);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(b2);
        add(Box.createRigidArea(new Dimension(0, 50)));
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
    
    @Override
    public void paintComponent(Graphics g){
        g.drawImage(backgroundImg, 0, 0, null);
       // b1.repaint();
        //b2.repaint();
        //b3.repaint();
    }
    
    class MouseAction extends MouseAdapter
    {
        Color bg;

        @Override
        public void mouseEntered(MouseEvent mouseEvent)
        {
            JButton button = (JButton) mouseEvent.getSource();
            bg = button.getBackground();
            button.setBackground(BUTTON_BG_COLOR);
        }

        @Override
        public void mouseExited(MouseEvent mouseEvent)
        {
            JButton button = (JButton) mouseEvent.getSource();
            button.setBackground(bg);
        }
    }

    ;

    public void displayArkanoidBoard()
    {

        setVisible(false);
        EventQueue.invokeLater(() ->
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
        EventQueue.invokeLater(() ->
        {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            HighScoreGui highScore = null;
            try
            {
                highScore = new HighScoreGui();
            } catch (IOException ex)
            {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex)
            {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.add(highScore);
            highScore.requestFocus();
            frame.pack();
        });

    }

}
