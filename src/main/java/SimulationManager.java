import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.Collections;

import java.awt.event.ActionListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SimulationManager implements Runnable {
    public int timeLimit;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public int maxArrivalTime;
    public int minArrivalTime;
    private static boolean done = false;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    //entity responsible with queue management and client distribution
    private Scheduler scheduler;
    //frame for dispalying simulation
    SimulationFrame frame = new SimulationFrame();
    SimulationFrame2 frame2;
    //pool of tasks (client shopping in the store)
    private ArrayList<Task> generatedTasks = new ArrayList<Task>(150);
    private ArrayList<String> cozi = new ArrayList<String>();
    JButton da = new JButton("DA");
    JButton nu = new JButton("NU");
    List<Task> toRemove = new ArrayList<>(150);

    int currentTime = 0;
    int average_waiting_time = 0;
    int nr_clienti = 0;


    public SimulationManager() {

        while (frame.isOkk() == false) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //initialize the scheduler
        numberOfServers = frame.getText1();
        scheduler = new Scheduler(numberOfServers);

        timeLimit = frame.getText3();
        maxProcessingTime = frame.getText7();
        minProcessingTime = frame.getText6();
        numberOfClients = frame.getText2();
        maxArrivalTime = frame.getText5();
        minArrivalTime = frame.getText4();

//        System.out.println(numberOfServers);
//        System.out.println(numberOfClients);
//        System.out.println(timeLimit);

        //generam clinetii

        generateNRandomTasks();
        Collections.sort(generatedTasks);

        //le adaugam in frame2

        frame2 = new SimulationFrame2((ArrayList<Server>) scheduler.getServers(), generatedTasks, "TIME: " + 0);
        frame2.setVisible(true);
    }

    public void generateNRandomTasks() {
        for (int i = 0; i < numberOfClients; i++) {
            int id = i;
            Random rand = new Random();
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            int serviceTime = rand.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            Task c = new Task(id, arrivalTime, serviceTime);
            generatedTasks.add(c);
            //System.out.println(c);
            //Thread.sleep(4000);
        }
    }

    public void run() {

        //int currentTime=0;
        boolean okk = true;

        while (currentTime < timeLimit && frame.isOkk() == true) {
            //frame2.setTimeLabel(currentTime);
            toRemove.clear();
            for (Task t : generatedTasks) {
                if (t.getArrivalTime() == currentTime) {
                    average_waiting_time += t.getServiceTime();
                    nr_clienti++;
                    scheduler.dispatchTask(t);
                    toRemove.add(t);
                }
            }
            generatedTasks.removeAll(toRemove);

            //*******AFISARE INTERFATA******
            frame2.setVisible(false);
            frame2 = new SimulationFrame2((ArrayList<Server>) scheduler.getServers(), generatedTasks, "TIME: " + currentTime);
            //*******AFISARE INTERFATA******


            //****AFISARE CONSOLA**********
            System.out.println("Time: " + currentTime);
            System.out.println("Waiting clients");
            for (Task t : generatedTasks) {
                System.out.println(t.toString());
            }
            System.out.println("Queues:");
            for (int i = 0; i < numberOfServers; i++) {
                System.out.println("coada " + i);
                Server s = scheduler.getServers().get(i);
                for (Task t : s.getTasks()) {
                    System.out.println(t.toString());
                }
            }
            //****AFISARE CONSOLA**********


            //******AFISARE FISIER********
            try {
                FileWriter myWriter = new FileWriter("afisare.txt", true);
                myWriter.write("Time: " + currentTime + '\n');
                myWriter.write("Waiting clients" + '\n');
                for (Task t : generatedTasks) {
                    myWriter.write(t.toString());
                }
                myWriter.write("\nQueues:" + '\n');
                for (int i = 0; i < numberOfServers; i++) {
                    myWriter.write("queue " + i + '\n');
                    Server s = scheduler.getServers().get(i);
                    for (Task t : s.getTasks()) {
                        myWriter.write(t.toString() + " ");
                    }
                    myWriter.write('\n');
                }
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            //******AFISARE FISIER********

            currentTime++;
//

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        if (generatedTasks.size() == 0) {
            JFrame frame3 = new JFrame();
            JPanel panel_mare = new JPanel(new GridLayout(1, 1));
            average_waiting_time /= nr_clienti;
            JLabel afisare_timp = new JLabel("Timpul mediu de asteptare: " + average_waiting_time);
            panel_mare.add(afisare_timp);
            frame3.add(panel_mare);
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setSize(200, 200);
            frame3.setVisible(true);
        }
//        else
//        {
//            JFrame frame3 = new JFrame();
//            JPanel panel_mare = new JPanel(new GridLayout(1, 1));
//            average_waiting_time/=nr_clienti;
//            JLabel afisare_timp = new JLabel("Timpul mediu de asteptare: " + average_waiting_time);
//            panel_mare.add(afisare_timp);
//            frame3.add(panel_mare);
//            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame3.setSize(200, 200);
//            frame3.setVisible(true);
//        }
        // System.out.println(generatedTasks.size());
        if (generatedTasks.size() != 0) {

            JFrame frame3 = new JFrame();
            JPanel panel_mare = new JPanel(new GridLayout(3, 1));
            JLabel afisare_timp = new JLabel("Timpul mediu de asteptare: " + average_waiting_time / nr_clienti);
            JLabel titlu = new JLabel("Timpul simularii s-a terminat. Doriti sa continuati?");
            JPanel panel_mic = new JPanel(new GridLayout(1, 2));
            panel_mic.add(da);
            panel_mic.add(nu);
            panel_mare.add(afisare_timp);
            panel_mare.add(titlu);
            panel_mare.add(panel_mic);
            frame3.add(panel_mare);
            frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame3.setSize(300, 300);
            frame3.setVisible(true);

            da.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e1) {

                    frame3.setVisible(false);
                    //frame.setVisible(false);
                    toRemove.clear();
                    continua();
                }

            });

            nu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame3.setVisible(false);
                    frame2.setVisible(false);
                    frame.setVisible(false);
                    JFrame frame3 = new JFrame();
                    JPanel panel_mare = new JPanel(new GridLayout(1, 1));
                    JLabel afisare_timp = new JLabel("Timpul mediu de asteptare: " + average_waiting_time / nr_clienti);
                    panel_mare.add(afisare_timp);
                    frame3.add(panel_mare);
                    frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame3.setSize(200, 200);
                    frame3.setVisible(true);
                }
            });
        }
        done = true;
    }

    public void continua() {
//    try {
//        Thread.sleep(4000);
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
        average_waiting_time *= nr_clienti;
        //System.out.println("****");
        while (generatedTasks.size() != 0) {
            toRemove.clear();
            for (Task t : generatedTasks) {
                if (t.getArrivalTime() == currentTime) {
                    nr_clienti++;
                    average_waiting_time += t.getServiceTime();
                    scheduler.dispatchTask(t);
                    toRemove.add(t);
                }
            }
            generatedTasks.removeAll(toRemove);

            frame2.setVisible(false);
            frame2 = new SimulationFrame2((ArrayList<Server>) scheduler.getServers(), generatedTasks, "TIME: " + currentTime);
            frame2.setVisible(true);

            System.out.println("Time: " + currentTime);
            System.out.println("Waiting clients");
            for (Task t : generatedTasks) {
                System.out.println(t.toString());
            }

            System.out.println("Queues:");
            for (int i = 0; i < numberOfServers; i++) {
                System.out.println("coada " + i);

                Server s = scheduler.getServers().get(i);
                for (Task t : s.getTasks()) {
                    System.out.println(t.toString());
                }
            }

            currentTime++;
//            System.out.println(currentTime);
//            System.out.println(generatedTasks.size());

            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        JFrame frame3 = new JFrame();
        JPanel panel_mare = new JPanel(new GridLayout(1, 1));
        average_waiting_time/=nr_clienti;
        JLabel afisare_timp = new JLabel("Timpul mediu de asteptare: " + average_waiting_time);
        panel_mare.add(afisare_timp);
        frame3.add(panel_mare);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.setSize(200, 200);
        frame3.setVisible(true);
    }

    public ArrayList<Task> getGeneratedTasks() {
        return generatedTasks;
    }

    public ArrayList<String> getCozi() {
        return cozi;
    }

    public static boolean isDone() {
        return done;
    }

}
