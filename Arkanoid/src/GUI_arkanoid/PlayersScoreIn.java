/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_arkanoid;

/**
 *
 * @author mjgaj
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;


/**
 *
 * @author mjgaj
 */
public class PlayersScoreIn extends JPanel {
    
    JFrame obj;    
    JTextField txt1;
    int score;
    
    public PlayersScoreIn(JFrame o, int usersScore) {
        score = usersScore;
        obj = o;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        
        addLabel("GAME OVER", CENTER_ALIGNMENT, new Dimension(300, 40), new Font("Verdana", 1, 40));
        add(Box.createRigidArea(new Dimension(0, 10)));
        addLabel("Your score: " + score, CENTER_ALIGNMENT, new Dimension(350, 30), new Font("Verdana", 2, 30));
        add(Box.createRigidArea(new Dimension(0, 35)));
        addLabel("Write your name and click SAVE button to join the scoreboard", CENTER_ALIGNMENT, new Dimension(650, 40), new Font("Verdana", 4, 20));
        
        txt1 = new JTextField();
        add(Box.createRigidArea(new Dimension(0, 10)));
        txt1.setAlignmentX(CENTER_ALIGNMENT);
        txt1.setMaximumSize(new Dimension(200, 30));
        add(txt1);
        
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        addButton("Save", CENTER_ALIGNMENT, 0, event -> {
            try (FileWriter fw = new FileWriter("leaderboard.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println(txt1.getText() + " " + score);
            } catch (IOException e) {
            }
            setVisible(false);
            obj.add(new Menu("Menu", obj));
            obj.repaint();
        });
        
        add(Box.createRigidArea(new Dimension(0, 500)));
        
        addButton("Dont add..", CENTER_ALIGNMENT, 0, event -> {
            setVisible(false);
            obj.add(new Menu("Menu", obj));
            obj.repaint();
        });
    }
    
    public void addButton(String title, float xAlignment, float yAlignment, ActionListener listener) {
        JButton btn = new JButton(title);
        btn.setAlignmentX(xAlignment);
        btn.setAlignmentY(yAlignment);
        btn.addActionListener(listener);
        add(btn);
    }
    
    public void addLabel(String title, float xAlignment, Dimension maxSize, Font font) {
        JLabel label = new JLabel(title);
        label.setFont(font);
        label.setAlignmentX(xAlignment);
        label.setMaximumSize(maxSize);
        add(label);
    }
}
