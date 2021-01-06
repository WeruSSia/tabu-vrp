import java.util.Set;

public class Vehicle {
    private int capacity;
    private Set<Client> visitedClients;

    public Vehicle(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}
