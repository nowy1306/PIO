package arkanoid;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class HighScore extends JPanel {

    JButton btn1 = new JButton("Return");
    JFrame obj;
    ArrayList<Player> players = new ArrayList<>();

    public HighScore(JFrame o) throws IOException {
        obj = o;
        JLabel title = new JLabel("LEADERBOARD");
        title.setFont(new Font("Verdana", 7, 30));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setMaximumSize(new Dimension(250, 40));
        add(title);
        IO.readScores(players);
        Collections.sort(players);
        for (int i = 0; i < players.size() && i < 10; i++) {
            JLabel label = new JLabel((players.get(i)).getUsername() + " - - - - - " + (players.get(i).getScore()));
            label.setFont(new Font("Verdana", 3, 15));
            label.setAlignmentX(CENTER_ALIGNMENT);
            label.setMaximumSize(new Dimension(250, 40));
            add(label);
        }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        btn1.setAlignmentX(CENTER_ALIGNMENT);
        btn1.setMaximumSize(new Dimension(300, 100));
        add(btn1);
        btn1.addActionListener(new ButtonAction());
    }

    private class ButtonAction implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            setVisible(false);
            obj.add(new Menu("Menu", obj));

        }
    }
}
