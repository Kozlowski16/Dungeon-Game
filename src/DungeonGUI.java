import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static JLabel map;

    public static void main(String[] args) {

        DungeonGUI hello = new DungeonGUI();
        hello.setSize(400,400);
        hello.setVisible(true);

    }
    private class Buttons extends JPanel{

		private static final long serialVersionUID = 1L;

		Buttons(){
            setLayout(null);
            setBackground(Color.LIGHT_GRAY);
            setBounds(10,10,160,110);
            JButton upButton= new JButton("Up");
            upButton.setBounds(30*2,5*2,20*2,20*2);
            upButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Dungeon.userAction("w");
                }
            });


            JButton downButton= new JButton("Down");
            downButton.setBounds(30*2,30*2,20*2,20*2);
            downButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Dungeon.userAction("s");
                }
            });

            JButton leftButton= new JButton("Left");
            leftButton.setBounds(5*2,30*2,20*2,20*2);
            leftButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Dungeon.userAction("a");
                }
            });

            JButton rightButton= new JButton("Right");
            rightButton.setBounds(55*2,30*2,20*2,20*2);
            rightButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    Dungeon.userAction("d");
                }
            });
            add(upButton);
            add(downButton);
            add(leftButton);
            add(rightButton);
        }

    }

    public DungeonGUI()  {
        setSize(350, 500);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Dungeon Game");

        JPanel panel = new JPanel();

        add(new Buttons());
        panel.setLayout(null);
        panel.setBounds(0,150,300,300);
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize(new Dimension(250,250));


        map=new JLabel("<html><p style=\"font-family:'Lucida Console', monospace\">Hello&nbsp;World! <br/>wwwwwwwwwwww</p></html>");

        map.setBounds(200,10,200,200);
        add(new Buttons());
        add(map);

    }
}

