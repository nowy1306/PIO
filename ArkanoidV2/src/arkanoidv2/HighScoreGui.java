/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arkanoidv2;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

/**
 *
 * @author mjgaj
 */
public class HighScoreGui extends JPanel {

    JButton btn1 = new JButton("Return");
    JLabel title;
    String file = "leaderboard.bin";
    ArrayList<Player> players;

    public HighScoreGui() throws IOException, ClassNotFoundException {
        setPreferredSize(new Dimension(300, 300));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addLabel("LEADERBOARD", CENTER_ALIGNMENT, new Dimension(250, 40), new Font("Verdana", 7, 30));

        players = new ArrayList<>();

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Player tmpPlayer;
            while (true) {
                tmpPlayer = ((Player) inputStream.readObject());
                players.add(tmpPlayer);
            }
        } catch (EOFException e) {
        }
        Collections.sort(players);

        for (int i = 0; i < players.size() && i < 10; i++) {
            addLabel( (players.get(i).getScore()) + " - - - - - " +  (players.get(i)).getUsername(), CENTER_ALIGNMENT, new Dimension(250, 40), new Font("Verdana", 3, 15));
        }

        btn1.setAlignmentX(CENTER_ALIGNMENT);
        btn1.setMaximumSize(new Dimension(200, 50));
        add(btn1);
        btn1.addActionListener(event -> {
            displayMenu();
        });
    }

    public void addLabel(String title, float xAlignment, Dimension maxSize, Font font) {
        JLabel label = new JLabel(title);
        label.setFont(font);
        label.setAlignmentX(xAlignment);
        label.setMaximumSize(maxSize);
        add(label);
    }

    public void displayMenu() {

        setVisible(false);
        EventQueue.invokeLater(()
                -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            Menu menu = new Menu();
            frame.add(menu);
            menu.requestFocus();
            frame.pack();
        });

    }
}
