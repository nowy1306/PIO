/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoid;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author mjgaj
 */
public class HighScoreIn extends JPanel {

    JFrame obj;
    JLabel label;
    JLabel label2;
    JButton btn1 = new JButton("SAVE");

    JTextField txt1 = new JTextField();
    int score;

    public HighScoreIn(JFrame o, int usersScore) {
        score = usersScore;
        obj = o;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        label = new JLabel("GAME OVER");
        label.setFont(new Font("Verdana", 1, 40));
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setMaximumSize(new Dimension(300, 40));
        add(label);

        add(Box.createRigidArea(new Dimension(0, 10)));

        label2 = new JLabel("Your score: " + score);
        label2.setFont(new Font("Verdana", 2, 30));
        label2.setAlignmentX(CENTER_ALIGNMENT);
        label2.setMaximumSize(new Dimension(250, 40));
        add(label2);

        add(Box.createRigidArea(new Dimension(0, 35)));

        label2 = new JLabel("Write your name and click SAVE button to join the scoreboard");
        label2.setFont(new Font("Verdana", 4, 20));
        label2.setAlignmentX(CENTER_ALIGNMENT);
        label2.setMaximumSize(new Dimension(650, 40));
        add(label2);

        add(Box.createRigidArea(new Dimension(0, 10)));
        txt1.setAlignmentX(CENTER_ALIGNMENT);
        txt1.setMaximumSize(new Dimension(200, 30));
        add(txt1);

        add(Box.createRigidArea(new Dimension(0, 10)));
        btn1.setAlignmentX(CENTER_ALIGNMENT);
        btn1.addActionListener(new ButtonAction());
        add(btn1);
    }

    private class ButtonAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            try (FileWriter fw = new FileWriter("leaderboard.txt", true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                out.println(txt1.getText() + " " + score);
            } catch (IOException e) {
            }
            setVisible(false);
            obj.add(new Menu("Menu", obj));
            obj.repaint();
        }
    }

}
