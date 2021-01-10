import java.util.ArrayList;
import java.util.List;

public class Vehicle {
    private int availableCapacity;
    private final List<Client> visitedClients;

    public Vehicle(int capacity) {
        this.availableCapacity = capacity;
        this.visitedClients = new ArrayList<>();
    }

    public List<Client> getVisitedClients() {
        return visitedClients;
    }

    public boolean containsClient(Client client) {
        return visitedClients.contains(client);
    }

    public int getIndex(Client client) {
        for (int i = 0; i < visitedClients.size(); i++) {
            if (visitedClients.get(i) == client) {
                return i;
            }
        }
        throw new IllegalStateException();
    }

    public boolean addClient(Client client) {
        if (this.availableCapacity - client.getDemand() > 0) {
            this.visitedClients.add(client);
            this.availableCapacity -= client.getDemand();
            return true;
        }
        return false;
    }

    public boolean ifCapacityWillBeExceeded(Client clientToBeRemoved, Client clientToBeAdded) {
        return availableCapacity + clientToBeRemoved.getDemand() - clientToBeAdded.getDemand() < 0;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < visitedClients.size() - 1; ++i) {
            builder.append(visitedClients.get(i).getId().toString()).append(":").append(visitedClients.get(i).getName()).append(" -> ");
        }
        builder.append(visitedClients.get(visitedClients.size() - 1).getId().toString()).append(":").append(visitedClients.get(visitedClients.size() - 1).getName());
        return builder.toString();
    }
}
