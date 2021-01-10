import java.util.*;

public class TabuVRP {

    private final List<Vehicle> vehicles;
    private final List<Client> clients;
    private final double[][] distanceMatrix;
    private final Configuration configuration;
    private final Random random;
    private final TabuList tabuList;

    private Double bestFitness;
    private List<Vehicle> bestSolution;

    public TabuVRP(List<Vehicle> vehicles, List<Client> clients, Configuration configuration) {
        this.vehicles = vehicles;
        this.clients = clients;
        this.distanceMatrix = createDistanceMatrix(clients);
        this.bestFitness = Double.MAX_VALUE;
        this.configuration = configuration;
        this.random = new Random(configuration.getRandomGeneratorSeed());
        this.tabuList = new TabuList(configuration.getTabuListSize());
        generateFirstRandomSolution();
    }

    private void generateFirstRandomSolution() {
        Client depot = clients.get(0);
        //start at depot
        vehicles.forEach(vehicle -> vehicle.addClient(depot));

        List<Client> clientsCopy = new ArrayList<>(clients);
        List<Vehicle> vehiclesCopy = new ArrayList<>(vehicles);

        clientsCopy.remove(depot);

        //sort clients by demand in descending order
        clientsCopy.sort((client1, client2) -> client2.getDemand().compareTo(client1.getDemand()));

        //add clients to vehicles
        while (!clientsCopy.isEmpty()) {
            //shuffle vehicles each time before adding another client
            Collections.shuffle(vehiclesCopy, random);
            for (Vehicle vehicle : vehiclesCopy) {
                if (!clientsCopy.isEmpty() && vehicle.addClient(clientsCopy.get(0))) {
                    clientsCopy.remove(clientsCopy.get(0));
                }
            }
        }

        //end at depot
        vehicles.forEach(v -> v.addClient(clients.get(0)));

        //print first solution
        System.out.println("First random solution: ");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }

        //set best solution to first solution
        bestSolution = new ArrayList<>(vehicles);
        bestFitness = calculateFitness(bestSolution);

        System.out.println("Fitness of first random solution: " + bestFitness.toString() + " km");
        System.out.println();
    }

    public void solve() {
        for (int i = 0; i < configuration.getMaximumIterations(); i++) {
            swapTwoRandomClients();

//            for (Vehicle vehicle : vehicles) {
//                System.out.println(vehicle.toString());
//            }

            Double currentFitness = calculateFitness(vehicles);

//            System.out.println("Fitness: " + currentFitness);
//            System.out.println();

            if (currentFitness < bestFitness) {
                bestFitness = currentFitness;
                bestSolution = vehicles;
            }
        }

        System.out.println("Best fitness: " + bestFitness + ", ");
        System.out.println("Best solution: ");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
        }
    }

    private Double calculateFitness(List<Vehicle> vehicles) {
        double totalFitness = 0.0;
        for (Vehicle vehicle : vehicles) {
            for (int i = 0; i < vehicle.getVisitedClients().size() - 1; ++i) {
                totalFitness += distanceMatrix[vehicle.getVisitedClients().get(i).getId()][vehicle.getVisitedClients().get(i + 1).getId()];
            }
        }
        return totalFitness;
    }

    private void swapTwoRandomClients() {
        List<Integer> clientsIndexes = new ArrayList<>();
        for (int i = 1; i < clients.size(); i++) {
            clientsIndexes.add(i);
        }

        while (true) {
            Collections.shuffle(clientsIndexes, random);
            Client client1 = clients.get(clientsIndexes.get(0));
            Client client2 = clients.get(clientsIndexes.get(1));
//            System.out.println(client1.getName() + " | " + client2.getName());
            Swap swap = new Swap(client1, client2, configuration.getCadence());
            if (!tabuList.doesContainSwap(swap)) {
                Vehicle vehicle1 = getVehicleWithClient(client1);
                Vehicle vehicle2 = getVehicleWithClient(client2);
                //check if vehicle capacity will be exceeded after swap
                if (vehicle1.ifCapacityWillBeExceeded(client1, client2) && vehicle2.ifCapacityWillBeExceeded(client2, client1)) {
                    break;
                }
                int client1Index = vehicle1.getIndex(client1);
                int client2Index = vehicle2.getIndex(client2);
                vehicle1.getVisitedClients().set(client1Index, client2);
                vehicle2.getVisitedClients().set(client2Index, client1);
                tabuList.checkSwapsCadence();
                tabuList.addSwap(swap);
                break;

            }
            tabuList.checkSwapsCadence();
        }

//        tabuList.printTabuList();
//        System.out.println();
    }

    private Vehicle getVehicleWithClient(Client client) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.containsClient(client)) {
                return vehicle;
            }
        }
        throw new RuntimeException();
    }

    private double[][] createDistanceMatrix(List<Client> clients) {
        double[][] distanceMatrix = new double[clients.size()][clients.size()];
        for (int i = 0; i < clients.size(); i++) {
            for (int j = 0; j < clients.size(); j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0.0;
                } else {
                    distanceMatrix[i][j] = clients.get(i).getDistanceTo(clients.get(j));
                }
            }
        }
        return distanceMatrix;
    }

}
