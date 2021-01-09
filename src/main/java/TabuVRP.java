import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TabuVRP {

    private final ArrayList<Vehicle> vehicles;
    private final ArrayList<Client> clients;
    private final double[][] distanceMatrix;

    private Double bestFitness;
    private ArrayList<Vehicle> bestSolution;

    public TabuVRP(List<Vehicle> vehicles, List<Client> clients, double[][] distanceMatrix) {
        this.vehicles = new ArrayList<>(vehicles);
        this.clients = new ArrayList<>(clients);
        this.distanceMatrix = distanceMatrix;
        this.bestFitness = Double.MAX_VALUE;
        initialize();
    }

    public void solve() {
        System.out.println("SOLVE xD");
    }

    private void initialize() {
        // Start from depot
        vehicles.forEach(v -> v.addClient(clients.get(0)));

        // First random solution, remove depot
        List<Client> clientsCopy = new ArrayList<>(clients);
        clientsCopy.remove(0);
        Random random = new Random();

        while (clientsCopy.size() > 0) {
            int randomClientNumber = random.nextInt(((clientsCopy.size())));
            int randomVehicleNumber = random.nextInt(((vehicles.size())));
            if (vehicles.get(randomVehicleNumber).addClient(clientsCopy.get(randomClientNumber))) {
                clientsCopy.remove(randomClientNumber);
            }
        }

        // End at depot
        vehicles.forEach(v -> v.addClient(clients.get(0)));

        for (int i = 0; i < vehicles.size(); ++i) {
            System.out.println("Vehicle: " + (i + 1));
            vehicles.get(i).printVisitedClients();
            System.out.println();
        }
        bestSolution = new ArrayList<>(vehicles);
        bestFitness = calculateFitness(bestSolution);
        System.out.println("Fitness of first random solution: " + bestFitness.toString() + " km");
    }

    private Double calculateFitness(ArrayList<Vehicle> vehicles) {
        Double totalFitness = 0.0;
        for (Vehicle vehicle: vehicles) {
            for (int i = 0; i < vehicle.getVisitedClients().size() - 1; ++i) {
                totalFitness += distanceMatrix[vehicle.getVisitedClients().get(i).getId()][vehicle.getVisitedClients().get(i + 1).getId()];
            }
        }
        return totalFitness;
    }

}
