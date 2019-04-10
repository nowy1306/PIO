import javax.swing.*;
import java.awt.*;


public class Menu extends JPanel
{
	
	public Menu(String name)
	{
		

		JFrame f = new JFrame(name);
		f.setMinimumSize(new Dimension(300, 400));
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JButton b1 = new JButton("Start Game");
		JButton b2 = new JButton("High Score");
		JButton b3 = new JButton("Exit Game");
		
		b1.setAlignmentX(CENTER_ALIGNMENT);
		b1.setMaximumSize(new Dimension(100, 50));
		
		b2.setAlignmentX(CENTER_ALIGNMENT);
		b2.setMaximumSize(new Dimension(100, 50));
		
		b3.setAlignmentX(CENTER_ALIGNMENT);
		b3.setMaximumSize(new Dimension(100, 50));
		
		
		add(Box.createRigidArea(new Dimension(0, 50)));
		add(b1);
		add(Box.createRigidArea(new Dimension(0, 50)));
		add(b2);
		add(Box.createRigidArea(new Dimension(0, 50)));
		add(b3);
		
		f.setVisible(true);
	}
	
	public static void main(String[] args) 
	{

		EventQueue.invokeLater(() ->
			{
				Menu m = new Menu("Arcanoid 2D Menu");
			}
		);

	}
}

		
  
