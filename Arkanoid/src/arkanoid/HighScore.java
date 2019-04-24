
package arkanoid;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


class HighScore extends JPanel {
    
    JButton btn1 = new JButton("Return");
    JFrame obj;
    
    public HighScore(JFrame o){
        obj = o;
        setLayout(new BoxLayout(this , BoxLayout.Y_AXIS));
        btn1.setAlignmentX(CENTER_ALIGNMENT);
        btn1.setMaximumSize(new Dimension(300, 100));
        add(btn1);
        btn1.addActionListener(new ButtonAction() );
    }
/*
    @Override
    public void paint(Graphics g){
        //tło
        //g.setColor(Color.blue);
        //g.fillRect(0, 0, 800, 800);
    }*/
    private class ButtonAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            setVisible(false);
            obj.add(new Menu("Menu", obj ) );
            
            
        }
    }
}
