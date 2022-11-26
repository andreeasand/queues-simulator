import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod=new AtomicInteger();

    public Server() {
        tasks= new ArrayBlockingQueue<Task>(150);
        waitingPeriod.set(0);
    }

    void addTask(Task newTask)
    {
         tasks.add(newTask);
         waitingPeriod.addAndGet(newTask.getServiceTime());
    }

    @Override
    public void run() {
//        while(tasks!=null)
//        {
//            try {
//                Task c= tasks.take();
//                //System.out.println(c);
//                Thread.sleep(c.getServiceTime());
//                waitingPeriod.getAndDecrement();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        Task t;
        while(SimulationManager.isDone()==false)
        {
            try {
                if(tasks.size()==0)
                    Thread.sleep(40);
                else
                {

                    t=tasks.element();

                    int var=t.getServiceTime();
                    for (int i=0; i<var; i++)
                    {	waitingPeriod.decrementAndGet();
                        t.setServiceTime(var-i-1);
                        Thread.sleep(40);
                    }

                    tasks.remove(t);
                } }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }
}
