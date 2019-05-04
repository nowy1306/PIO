/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_arkanoid;

import arkanoid.IO;
import arkanoid.Player;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 *
 * @author mjgaj
 */
public class HighScoreGui extends JPanel {

    JButton btn1 = new JButton("Return");
    JFrame obj;
    JLabel title;
    ArrayList<Player> players;

    public HighScoreGui(JFrame o) throws IOException {
        obj = o;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addLabel("LEADERBOARD", CENTER_ALIGNMENT, new Dimension(250, 40), new Font("Verdana", 7, 30));

        players = new ArrayList<>();
        IO.readScores(players);
        Collections.sort(players);

        for (int i = 0; i < players.size() && i < 10; i++) {
            addLabel( (players.get(i)).getUsername() + " - - - - - " + (players.get(i).getScore()), CENTER_ALIGNMENT, new Dimension(250, 40), new Font("Verdana", 3, 15) );
        }

        btn1.setAlignmentX(CENTER_ALIGNMENT);
        btn1.setMaximumSize(new Dimension(300, 100));
        add(btn1);
        btn1.addActionListener(event -> {
            setVisible(false);
            obj.add(new Menu("Menu", obj));
        });
    }

    public void addLabel(String title, float xAlignment, Dimension maxSize, Font font) {
        JLabel label = new JLabel(title);
        label.setFont(font);
        label.setAlignmentX(xAlignment);
        label.setMaximumSize(maxSize);
        add(label);
    }
}
