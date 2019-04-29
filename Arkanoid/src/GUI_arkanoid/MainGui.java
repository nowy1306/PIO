/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI_arkanoid;

import java.awt.Component;
import javax.swing.JFrame;

/**
 *
 * @author witol
 */
public class MainGui {

    JFrame frame;

    public MainGui() {
        frame = new JFrame();
        frame.setBounds(10, 10, 800, 800);
        frame.setTitle("Arkanoid");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void start(){
        Menu menu = new Menu("Menu",frame);
        addContent(menu);
    }
    
    
    public void addContent( Component c){
        frame.add(c);
    }

}
