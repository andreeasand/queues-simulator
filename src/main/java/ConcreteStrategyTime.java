import java.util.List;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addTsk(List<Server> servers, Task t) {
        //verificam ce coada are waitingTime-ul mai mic
        //si adaugam clientul la acea coada

        Server q_optim= servers.get(0);
        int index=0; //numarul cozii
        int min=q_optim.getWaitingPeriod().get();

        for(Server q:servers)
        {
            if(q.getWaitingPeriod().get()< min)
            {
                min=q.getWaitingPeriod().get();
                q_optim=q;
                index=servers.indexOf(q);
            }
        }
        //q_optim.addTask(t);
        servers.get(index).addTask(t);
    }

}
