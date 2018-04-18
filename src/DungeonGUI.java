import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonGUI extends JFrame {
    private JButton upButton;
    private JButton downButton;
    private JButton rightButton;
    private JButton leftButton;

    public static JLabel map;

    public static void main(String[] args) {

        DungeonGUI hello = new DungeonGUI();
        hello.setSize(400,400);
        hello.setVisible(true);

    }
    public class Buttons extends JPanel{
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

                }
            });

            JButton leftButton= new JButton("Left");
            leftButton.setBounds(5*2,30*2,20*2,20*2);
            leftButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {

                }
            });

            JButton rightButton= new JButton("Right");
            rightButton.setBounds(55*2,30*2,20*2,20*2);
            rightButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {

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


        map=new JLabel("<html>Hello World!</br>blah&nbsp;blahblah</html>");

        map.setBounds(10,10,200,200);
        add(new Buttons());

        panel.add(map);

        add(panel);
        add(new JPanel());
        //add swing compnets to content pane
        //Container c = getContentPane();

        //c.add(textArea,BorderLayout.CENTER);
       // c.add(button,BorderLayout.SOUTH);

    }
}

