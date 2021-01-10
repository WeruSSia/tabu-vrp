import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private final int capacity;
    private int availableCapacity;
    private List<Client> visitedClients;

    public Vehicle(int capacity) {
        this.capacity = capacity;
        this.availableCapacity = capacity;
        this.visitedClients = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Client> getVisitedClients() {
        return visitedClients;
    }

    public boolean containsClient(Client client) {
        return visitedClients.contains(client);
    }

    public int getClientIndex(Client client) {
        for (int i = 0; i < visitedClients.size(); i++) {
            if (visitedClients.get(i) == client) {
                return i;
            }
        }
        throw new RuntimeException();
    }

    public void printVisitedClients() {
        for (int i = 0; i < visitedClients.size() - 1; ++i) {
            System.out.print(visitedClients.get(i).getId().toString() + ":" + visitedClients.get(i).getName() + " -> ");
        }
        System.out.print(visitedClients.get(visitedClients.size() - 1).getId().toString() + ":" + visitedClients.get(visitedClients.size() - 1).getName());
        System.out.println();
    }

    public boolean addClient(Client client) {
        if (checkIfEnoughCapacity(client)) {
            this.visitedClients.add(client);
            this.availableCapacity -= client.getDemand();
            return true;
        }
        return false;
    }

    private boolean checkIfEnoughCapacity(Client client) {
        return this.availableCapacity - client.getDemand() > 0;
    }

    public boolean ifCapacityWillBeExceeded(Client clientToBeRemoved, Client clientToBeAdded) {
        return availableCapacity + clientToBeRemoved.getDemand() - clientToBeAdded.getDemand() < 0;
    }
}
