import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.*;

public class SimulationFrame extends JFrame{

    JPanel panel_mare= new JPanel(new GridLayout(3,1));
    private boolean okk=false;  //verificam daca s-a apasat ok
   private JTextField text1;
    private JTextField text2;
    private JTextField text3;
    private JTextField text4;
    private JTextField text5;
    private JTextField text6;
    private JTextField text7;
    private JLabel cozi;
    private JLabel clienti;
    private JLabel interval;
    private JLabel arrival_min;
    private JLabel arrival_max;
    private JLabel wait_min;
    private JLabel wait_max;

    int nr1,nr2,nr3,nr4,nr5,nr6,nr7;

    public boolean isOkk() {
        return okk;
    }

    public SimulationFrame()
    {

        JPanel panel_1= new JPanel();
        JLabel titlu1= new JLabel("Introduceti datele ");
        titlu1.setFont(new Font("Calibri", Font.BOLD, 20));
        titlu1.setHorizontalAlignment(SwingConstants.CENTER);
        panel_1.add(titlu1);
        panel_mare.add(panel_1);

        JPanel panel_2= new JPanel();
        panel_2.setLayout(new GridLayout(7,2));
        cozi = new JLabel("Introduceti numarul de cozi: ");
        panel_2.add(cozi);
        text1= new JTextField();
        text1.setColumns(20);
        panel_2.add(text1);
        clienti= new JLabel("Introduceti numarul de clienti: ");
        panel_2.add(clienti);
        text2= new JTextField();
        text2.setColumns(20);
        panel_2.add(text2);
        interval= new JLabel("Introduceti timpul de simulare: ");
        panel_2.add(interval);
        text3= new JTextField();
        text3.setColumns(20);
        panel_2.add(text3);
        arrival_min= new JLabel("Introduceti timpul minim de sosire: ");
        panel_2.add(arrival_min);
        text4= new JTextField();
        text4.setColumns(20);
        panel_2.add(text4);
        arrival_max= new JLabel("Introduceti timpul maxim de sosire: ");
        panel_2.add(arrival_max);
        text5= new JTextField();
        text5.setColumns(20);
        panel_2.add(text5);
        wait_min= new JLabel("Introduceti timpul minim de asteptare: ");
        panel_2.add(wait_min);
        text6= new JTextField();
        text6.setColumns(20);
        panel_2.add(text6);
        wait_max= new JLabel("Introduceti timpul maxim de asteptare: ");
        panel_2.add(wait_max);
        text7= new JTextField();
        text7.setColumns(20);
        panel_2.add(text7);
        panel_mare.add(panel_2);

        JPanel panel2=new JPanel();
        JButton ok= new JButton("ok");
        panel2.add(ok);
        panel_mare.add(panel2);

        /*JPanel panel_2= new JPanel();
        panel_2.setLayout(new FlowLayout());
        for(int i= 1;i<= this.getNumarulCozi();i++)
        {
            JLabel c= new JLabel("coada %d",i);
            panel_2.add(c);
        }
        this.add(panel_2, BorderLayout.CENTER);

         */
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okk=true;
                 nr1= Integer.parseInt(text1.getText());
                //System.out.println(nr1);
                 nr2= Integer.parseInt(text2.getText());
                 nr3=Integer.parseInt(text3.getText());
                 nr4=Integer.parseInt(text4.getText());
                 nr5=Integer.parseInt(text5.getText());
                 nr6=Integer.parseInt(text6.getText());
                 nr7=Integer.parseInt(text7.getText());
            }
        });

        this.add(panel_mare);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450,450);
        this.setVisible(true);
    }

    //cozi
    public int getText1() {
        return nr1;
    }

    //clienti
    public int getText2() {
        return nr2;
    }

    //intervalul de simulare
    public int getText3() {
        return nr3;
    }

    //arrival min
    public int getText4() {
        return nr4;
    }

    //arrival max
    public int getText5() {
        return nr5;
    }

    //wait min
    public int getText6() {
        return nr6;
    }

    //wait max
    public int getText7() {
        return nr7;
    }
}
