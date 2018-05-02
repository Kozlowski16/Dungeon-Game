import javax.swing.*;
import java.awt.*;

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
            JButton upButton= new JButton("W");
            upButton.setBounds(30*2,5*2,20*2,20*2);
            upButton.addActionListener(e -> Dungeon.userAction("w"));


            JButton downButton= new JButton("S");
            downButton.setBounds(30*2,30*2,20*2,20*2);
            downButton.addActionListener(e -> Dungeon.userAction("s"));

            JButton leftButton= new JButton("A");
            leftButton.setBounds(5*2,30*2,20*2,20*2);
            leftButton.addActionListener(e -> Dungeon.userAction("a"));

            JButton rightButton= new JButton("D");
            rightButton.setBounds(55*2,30*2,20*2,20*2);
            rightButton.addActionListener(e -> Dungeon.userAction("d"));

            JButton waitButton= new JButton("N");
            waitButton.setBounds(5*2,5*2,20*2,20*2);
            waitButton.addActionListener(e -> Dungeon.userAction("n"));

            JButton monsterButton= new JButton("M");
            monsterButton.setBounds(55*2,5*2,20*2,20*2);
            monsterButton.addActionListener(e -> {Monster_list.runMonsters();Dungeon.printLevel(Dungeon.getRoom());});
            add(monsterButton);
            add(upButton);
            add(downButton);
            add(leftButton);
            add(rightButton);
            add(waitButton);
        }

    }

    public DungeonGUI()  {
        setSize(350, 500);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

