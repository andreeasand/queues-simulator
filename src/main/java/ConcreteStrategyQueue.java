import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addTsk(List<Server> servers, Task t) {
        //verificam ce coada are nr de clienti mai mic
        //si adaugam clientul la acea coada

        Server q_optim= servers.get(0);
        int index=0; //numarul cozii
        int min=q_optim.getTasks().size();

        for(Server q:servers)
        {
            if(q.getTasks().size()< min)
            {
                min=q.getTasks().size();
                q_optim=q;
                index=servers.indexOf(q);
            }
        }
        //q_optim.addTask(t);
        servers.get(index).addTask(t);
    }
}
