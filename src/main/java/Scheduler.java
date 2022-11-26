import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers= new ArrayList<Server>();
    private int maxNoServers;
    private Strategy strategy;


    public Scheduler(int maxNoServers)
    {
        for(int i=0; i< maxNoServers; i++)
        {
            //create server object
            Server q= new Server();
            servers.add(q);

            //create thread with the object
            Thread t= new Thread(q);
            t.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy)
    {
        if(policy== SelectionPolicy.SHORTEST_QUEUE)
        {
            strategy = new ConcreteStrategyQueue();
        }

        if(policy == SelectionPolicy.SHORTEST_TIME)
        {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Task t)
    {
        //call the strategy addTask method
        changeStrategy(SelectionPolicy.SHORTEST_TIME);
        strategy.addTsk(servers,t);
    }

    public List<Server> getServers() {
        return servers;
    }

}
