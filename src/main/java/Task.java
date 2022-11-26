import java.util.Random;

public class Task implements Comparable<Task>{
    private  int id;
    private  int arrivalTime;
    private  int serviceTime;

    public Task(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "(" +id +" "+ arrivalTime +" " + serviceTime +")";
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int compareTo(Task c)
    {
        if (this.arrivalTime > c.getArrivalTime())
            return 1;
        else if (this.arrivalTime < c.getArrivalTime())
            return -1;
        return 0;
    }
}
