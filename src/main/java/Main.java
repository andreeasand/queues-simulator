import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Task> q= new ArrayBlockingQueue(100);
        SimulationManager p= new SimulationManager();
        p.run();
        Server c= new Server();
        //Thread t1=new Thread(p);
        //t1.start();
        //Thread t2= new Thread(c);
        //t2.start();
    }
}
