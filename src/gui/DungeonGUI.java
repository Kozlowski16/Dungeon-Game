package gui;

import main.Dungeon;

import javax.swing.*;
import java.awt.*;

public class DungeonGUI extends JFrame {
    private final long serialVersionUID = 1L;

    private JLabel map;
    private JPanel menu;
    private JPanel settings;
    private JButton start;
    private JButton setting;
    private JButton exit;
    private JLabel title;
    private KeyInput input;


    public DungeonGUI(Dungeon d) {
        input = new KeyInput(d);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("main.Dungeon Game");

        //////////////////////
        menu = new JPanel();
        menu.setBackground(Color.LIGHT_GRAY);
        menu.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        g.weightx = 0.5;
        g.weighty = 0.5;

        g.gridx = 0;
        g.gridy = 0;
        title = new JLabel("main.Dungeon Game");
        title.setBackground(Color.WHITE);
        menu.add(title, g);

        g.gridx = 0;
        g.gridy = 1;
        start = new JButton("start");
        start.addActionListener(e -> startGame());
        menu.add(start, g);

        g.gridx = 0;
        g.gridy = 2;
        setting = new JButton("Settings");
        setting.addActionListener(e -> setSettings());
        menu.add(setting, g);

        g.gridx = 0;
        g.gridy = 3;
        exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        menu.add(exit, g);

        ///////////////////////////
        //////////////////////////
        settings = new JPanel();
        settings.setBackground(Color.LIGHT_GRAY);
        settings.setLayout(new GridBagLayout());
        g = new GridBagConstraints();

        g.weightx = 0.5;
        g.weighty = 0.5;
        //Row 0///////
        g.gridx = 0;
        g.gridy = 0;
        JLabel roomLabel = new JLabel("Room Size");
        roomLabel.setBackground(Color.WHITE);
        settings.add(roomLabel, g);
        //////
        g.gridx = 2;
        g.gridy = 0;
        JLabel roomLabel2 = new JLabel("1");
        roomLabel2.setBackground(Color.WHITE);
        settings.add(roomLabel2, g);
        //////////
        g.gridx = 1;
        g.gridy = 0;
        JButton rooomD = new JButton("<");
        rooomD.addActionListener(e -> changeSetting(roomLabel2, -1, 5, 10));
        settings.add(rooomD, g);
        //////
        g.gridx = 3;
        g.gridy = 0;
        JButton roomU = new JButton(">");
        roomU.addActionListener(e -> changeSetting(roomLabel2, 1, 5, 10));
        settings.add(roomU, g);


        //Row 2
        g.gridx = 0;
        g.gridy = 1;
        JLabel floorLabel = new JLabel("Room Size");
        floorLabel.setBackground(Color.WHITE);
        settings.add(floorLabel, g);
        ///////////
        g.gridx = 2;
        g.gridy = 1;
        JLabel floorLabel2 = new JLabel("1");
        floorLabel2.setBackground(Color.WHITE);
        settings.add(floorLabel2, g);
        ///////////
        g.gridx = 1;
        g.gridy = 1;
        JButton floorD = new JButton("<");
        floorD.addActionListener(e -> changeSetting(floorLabel2, -1, 1, 5));
        settings.add(floorD, g);
        //////
        g.gridx = 3;
        g.gridy = 1;
        JButton floorU = new JButton(">");
        floorU.addActionListener(e -> changeSetting(floorLabel2, 1, 1, 5));
        settings.add(floorU, g);
        //////

        g.gridx = 0;
        g.gridy = 2;


        JButton back = new JButton("Back");
        back.addActionListener(e -> setMenu());
        settings.add(back, g);


        ///////////////////////////
        add(menu);

        map = new JLabel("<html><p style=\"font-family:'Lucida Console', monospace\">Hello&nbsp;World! <br/>wwwwwwwwwwww</p></html>");
        map.setBounds(0, 0, 400, 500);

        setSize(400, 500);

        //Display the window.
        this.setVisible(true);
        addKeyListener(input);
    }

    private void changeSetting(JLabel label, int d, int min, int max) {
        int number = Integer.parseInt(label.getText());
        number += d;
        if (number < min)
            number = min;
        else if (number > max)
            number = max;
        label.setText("" + number);
    }

    private void setSettings() {
        remove(menu);
        add(settings);
        setFocusable(true);
        requestFocus();
        validate();
        repaint();
    }

    private void startGame() {
        remove(menu);
        add(map);
        setFocusable(true);
        requestFocus();
        validate();
        repaint();
    }

    public void setMapText(String s) {
        map.setText(s);
    }

    public void setMenu() {
        setFocusable(false);
        remove(map);
        remove(settings);
        add(menu);
        validate();
        repaint();
    }
}


