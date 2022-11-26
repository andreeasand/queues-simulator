import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SimulationFrame2 extends JFrame {
    ArrayList<Task> generatedTasks;
    ArrayList<Server> cozi=new ArrayList<>();
    JPanel panel_mare1 =new JPanel(new GridLayout(1,2));
    JLabel timee;
    JPanel cozi1= new JPanel();
    JPanel clienti= new JPanel();
    JTextArea displayQueues= new JTextArea("");
    JScrollPane scrollQueues;
    JTextArea displayTasks= new JTextArea("");
    JScrollPane scrollTasks;
    public SimulationFrame2(ArrayList<Server> cozi, ArrayList<Task> generatedTasks,String currentTime) {
        this.generatedTasks=generatedTasks;
        this.cozi=cozi;

        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2,1));
        JLabel titlu = new JLabel("Server state & Waiting queue");
        titlu.setHorizontalAlignment(SwingConstants.CENTER);
        panel1.add(titlu);
        timee=new JLabel(currentTime);
        panel1.add(timee);
        this.add(panel1, BorderLayout.NORTH);

        cozi1.setLayout(new BoxLayout(cozi1, BoxLayout.Y_AXIS));
        displayQueues.setEditable(false);
        displayQueues.setLineWrap(true);
        displayQueues.setVisible(true);

        //cozi1.add(displayQueues);
        cozi1.setLayout(new GridLayout(cozi.size(),generatedTasks.size()));
        int index=0;
        for(Server s:cozi)
        {
            String string="";
            string= string + "coada "+index;
            index++;
            for(Task t:s.getTasks())
            {
                string=string+ "; "+t.toString();
            }
            //JTextField t= new JTextField(s);
            JLabel l=new JLabel(string);
            cozi1.add(l);
        }

        scrollQueues=new JScrollPane(cozi1);
        scrollQueues.setPreferredSize(new Dimension(150,300));
        scrollQueues.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //cozi1.add(scrollQueues);
        panel_mare1.add(scrollQueues);

        clienti.setLayout(new BoxLayout(clienti, BoxLayout.Y_AXIS));
        displayTasks.setEditable(false);
        displayTasks.setLineWrap(true);
        displayTasks.setVisible(true);

        clienti.setLayout(new GridLayout(generatedTasks.size()/2,3));
        JLabel t= new JLabel("clienti: ");
        clienti.add(t);
        for(Task i:generatedTasks)
        {
            //System.out.println("*");
            System.out.println(i.toString());
            JLabel cl=new JLabel(i.toString());
            clienti.add(cl);
        }
        scrollTasks=new JScrollPane(clienti);
        scrollTasks.setPreferredSize(new Dimension(150,300));
        scrollTasks.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel_mare1.add(scrollTasks);

        this.add(panel_mare1,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(450, 450);
        this.setVisible(true);
    }
    public void setTimeLabel( int time2)
    {
        String s = "TIME: " + time2;
        timee.setText(s);
    }

}
