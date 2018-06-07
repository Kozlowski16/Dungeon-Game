import javax.swing.*;
import java.awt.*;

public class DungeonGUI extends JFrame {
    private final long serialVersionUID = 1L;

    private JLabel map;

    protected DungeonGUI(Dungeon d) {

        setSize(400, 500);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Dungeon Game");

        JPanel panel = new JPanel();

        panel.setLayout(null);
        panel.setBounds(0, 150, 300, 300);
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(250, 250));

        map = new JLabel("<html><p style=\"font-family:'Lucida Console', monospace\">Hello&nbsp;World! <br/>wwwwwwwwwwww</p></html>");

        map.setBounds(0, 0, 400, 500);

        add(map);
        //add(panel);
        this.addKeyListener(new KeyInput(d));
    }

    public void setMap(String s) {
        map.setText(s);
    }
}


