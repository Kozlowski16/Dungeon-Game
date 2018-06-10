package gui;
import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel{
    Menu(){
        GridBagConstraints g = new GridBagConstraints();

        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridBagLayout());
        g = new GridBagConstraints();

        g.weightx = 0.5;
        g.weighty = 0.5;
        //Row 0///////
        g.gridx = 0;
        g.gridy = 0;
        JLabel roomLabel = new JLabel("Room Size");
        roomLabel.setBackground(Color.WHITE);
        this.add(roomLabel, g);
        //////
        g.gridx = 2;
        g.gridy = 0;
        JLabel roomLabel2 = new JLabel("1");
        roomLabel2.setBackground(Color.WHITE);
        this.add(roomLabel2, g);
        //////////
        g.gridx = 1;
        g.gridy = 0;
        JButton rooomD = new JButton("<");
        //rooomD.addActionListener(e -> changeSetting(roomLabel2, -1, 5, 10));
        this.add(rooomD, g);
        //////
        g.gridx = 3;
        g.gridy = 0;
        JButton roomU = new JButton(">");
        //roomU.addActionListener(e -> changeSetting(roomLabel2, 1, 5, 10));
        this.add(roomU, g);


        //Row 2
        g.gridx = 0;
        g.gridy = 1;
        JLabel floorLabel = new JLabel("Room Size");
        floorLabel.setBackground(Color.WHITE);
        this.add(floorLabel, g);
        ///////////
        g.gridx = 2;
        g.gridy = 1;
        JLabel floorLabel2 = new JLabel("1");
        floorLabel2.setBackground(Color.WHITE);
        this.add(floorLabel2, g);
        ///////////
        g.gridx = 1;
        g.gridy = 1;
        JButton floorD = new JButton("<");
        //floorD.addActionListener(e -> changeSetting(floorLabel2, -1, 1, 5));
        this.add(floorD, g);
        //////
        g.gridx = 3;
        g.gridy = 1;
        JButton floorU = new JButton(">");
        ////////////floorU.addActionListener(e -> changeSetting(floorLabel2, 1, 1, 5));
        this.add(floorU, g);
        //////

        g.gridx = 0;
        g.gridy = 2;


        JButton back = new JButton("Back");
       //////////////// back.addActionListener(e -> setMenu());
        this.add(back, g);


        ///////////////////////////

    }
}
