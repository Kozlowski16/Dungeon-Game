import javax.swing.*;
import java.awt.*;

public class DungeonGUI extends JFrame {
    private final long serialVersionUID = 1L;

    private JLabel map;
    private JPanel menu;
    private JButton start;
    private JButton settings;
    private JButton exit;
    private JLabel title;
    private KeyInput input;


    protected DungeonGUI(Dungeon d) {
        input= new  KeyInput(d);
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
        title =  new JLabel("Dungeon Game");
        title.setBackground(Color.WHITE);
        menu.add(title, g);

        g.gridx = 0;
        g.gridy = 1;
        start =  new JButton("start");
        start.addActionListener(e -> startGame());
        menu.add(start, g);

        g.gridx = 0;
        g.gridy = 2;
        settings =  new JButton("Settings");
        settings.addActionListener(e -> new Dungeon());
        menu.add(settings, g);

        g.gridx = 0;
        g.gridy = 3;
        exit= new JButton("Exit");
        exit.addActionListener(e -> System.exit(0));
        menu.add(exit,g);

        ///////////////////////////
        add(menu);

        map = new JLabel("<html><p style=\"font-family:'Lucida Console', monospace\">Hello&nbsp;World! <br/>wwwwwwwwwwww</p></html>");
        map.setBounds(0, 0, 400, 500);

        setSize(400, 500);

        //Display the window.
        this.setVisible(true);
        addKeyListener(input);
    }
    private void startGame(){
        remove(menu);
        add(map);
        setFocusable(true);
        requestFocus();
        validate();
        repaint();
    }

    public void setMap(String s) {
        map.setText(s);
    }
    public void removeMap(){
        setFocusable(false);
        remove(map);
        add(menu);
        validate();
        repaint();
    }
}


