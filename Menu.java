import javax.swing.*;
import java.awt.*;


public class Menu extends JFrame
{
	
	public Menu(String name)
	{
		super(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 300, 400);

		JButton b1 = new JButton("Start Game");
		JButton b2 = new JButton("High Score");
		JButton b3 = new JButton("Exit Game");
		
		
		setLayout(null);
		
		b1.setLocation(100, 50);
		b1.setSize(100, 50);
		b2.setLocation(100, 150);
		b2.setSize(100, 50);
		b3.setLocation(100, 250);
		b3.setSize(100, 50);

		add(b1);
		add(b2);
		add(b3);

		setVisible(true);
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

		
  
