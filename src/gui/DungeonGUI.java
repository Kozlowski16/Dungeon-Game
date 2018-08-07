package gui;

import main.Dungeon;

import javax.swing.*;
import java.awt.*;

public class DungeonGUI extends JFrame {
    private final long serialVersionUID = 1L;
    private Dungeon dungeon;

    private JLabel map;
    private JPanel menu;
   // private JPanel settings;

    private Settings settings;


    public DungeonGUI(Dungeon d) {
        dungeon=d;
        KeyInput input = new KeyInput(d);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dungeon Game");

        //////////////////////
        menu = new JPanel();
        menu.setBackground(Color.LIGHT_GRAY);
        menu.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();

        g.weightx = 0.5;
        g.weighty = 0.5;

        g.gridx = 0;
        g.gridy = 0;
        JLabel title = new JLabel("Dungeon Game");
        title.setBackground(Color.WHITE);
        menu.add(title, g);

        g.gridx = 0;
        g.gridy = 1;
        JButton  start = new JButton("start");
        start.addActionListener(e -> startGame());
        menu.add(start, g);

        g.gridx = 0;
        g.gridy = 2;
        JButton setting = new JButton("Settings");
        setting.addActionListener(e -> setSettings());
        menu.add(setting, g);

        g.gridx = 0;
        g.gridy = 3;
        JButton  exit = new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        menu.add(exit, g);

        ///////////////////////////
        add(menu);

        map = new JLabel("<html><p style=\"font-family:'Lucida Console', monospace\">Hello&nbsp;World! <br/>Error Failed to get data</p></html>");
        map.setBounds(0, 0, 400, 500);

        setSize(400, 500);

        //Display the window.
        this.setVisible(true);
        addKeyListener(input);
        settings= new Settings();
    }

    public void changeSetting(JLabel label, int d, int min, int max) {
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
        dungeon.newGame();
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
    public int getRoomSize(){
        return settings.getRoomSize();
    }
    public int getFloorSize(){
        return settings.getFloorSize();
    }
    private class Settings extends JPanel{
        private JLabel roomLabel2;
        private JLabel floorLabel2;

        Settings(){
            //////////////////////////
            setBackground(Color.LIGHT_GRAY);
            setLayout(new GridBagLayout());
            GridBagConstraints g = new GridBagConstraints();

            g.weightx = 0.5;
            g.weighty = 0.5;
            //Row 0///////
            g.gridx = 0;
            g.gridy = 0;
            JLabel roomLabel = new JLabel("Room Size");
            roomLabel.setBackground(Color.WHITE);
            add(roomLabel, g);
            //////
            g.gridx = 2;
            g.gridy = 0;
            roomLabel2 = new JLabel("8");
            roomLabel2.setBackground(Color.WHITE);
            add(roomLabel2, g);
            //////////
            g.gridx = 1;
            g.gridy = 0;
            JButton rooomD = new JButton("<");
            rooomD.addActionListener(e -> changeSetting(roomLabel2, -1, 5, 10));
            add(rooomD, g);
            //////
            g.gridx = 3;
            g.gridy = 0;
            JButton roomU = new JButton(">");
            roomU.addActionListener(e -> changeSetting(roomLabel2, 1, 5, 10));
            add(roomU, g);

            //Row 2
            g.gridx = 0;
            g.gridy = 1;
            JLabel floorLabel = new JLabel("Floor Size");
            floorLabel.setBackground(Color.WHITE);
            add(floorLabel, g);
            ///////////
            g.gridx = 2;
            g.gridy = 1;
            floorLabel2 = new JLabel("3");
            floorLabel2.setBackground(Color.WHITE);
            add(floorLabel2, g);
            ///////////
            g.gridx = 1;
            g.gridy = 1;
            JButton floorD = new JButton("<");
            floorD.addActionListener(e -> changeSetting(floorLabel2, -1, 2, 5));
            add(floorD, g);
            //////
            g.gridx = 3;
            g.gridy = 1;
            JButton floorU = new JButton(">");
            floorU.addActionListener(e -> changeSetting(floorLabel2, 1, 1, 5));
            add(floorU, g);
            //////

            g.gridx = 0;
            g.gridy = 2;

            JButton back = new JButton("Back");
            back.addActionListener(e -> setMenu());
            add(back, g);
            ///////////////////////////
        }
        public int getRoomSize(){
            return Integer.parseInt(roomLabel2.getText());
        }
        public int getFloorSize(){
            return Integer.parseInt(floorLabel2.getText());
        }
    }
}


