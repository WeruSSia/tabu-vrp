import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader file = getInputFile();

        List<Vehicle> vehicles = initializeVehicles(file);
        List<Client> clients = getClients(file);
        double[][] distanceMatrix = createDistanceMatrix(clients);

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getCapacity());
        }

        for (Client client : clients) {
            System.out.println(client.getId().toString() + ", " + client.getName() + ", " + client.getDemand());
        }

        for (int i = 0; i < distanceMatrix.length; i++) {
            for (int j = 0; j < distanceMatrix.length; j++) {
                System.out.print(distanceMatrix[i][j] + " ");
            }
            System.out.println();
        }

        TabuVRP tabuVrpInstance = new TabuVRP(vehicles, clients, distanceMatrix);
        tabuVrpInstance.solve();
    }

    private static List<Vehicle> initializeVehicles(BufferedReader file) throws IOException {
        List<Vehicle> vehicles = new ArrayList<>();
        int numberOfVehicles = Integer.parseInt(file.readLine());
        int vehicleCapacity = Integer.parseInt(file.readLine());
        for (int i = 0; i < numberOfVehicles; i++) {
            vehicles.add(new Vehicle(vehicleCapacity));
        }
        return vehicles;
    }

    private static List<Client> getClients(BufferedReader file) throws IOException {
        List<Client> clients = new ArrayList<>();
        String line = file.readLine();
        Integer id = 0;
        while (line != null) {
            String[] clientData = line.split(",");
            System.out.println(clientData[0]);
            clients.add(new Client(id++, clientData[0], Integer.valueOf(clientData[1]), Double.parseDouble(clientData[2]), Double.parseDouble(clientData[3])));
            line = file.readLine();
        }
        return clients;
    }

    private static BufferedReader getInputFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input file: ");
        String fileName = scanner.next();
        return new BufferedReader(new FileReader(fileName));
    }

    private static double[][] createDistanceMatrix(List<Client> clients) {
        List<Client> clientsList = new ArrayList<>(clients);
        double[][] distanceMatrix = new double[clients.size()][clients.size()];
        for (int i = 0; i < clientsList.size(); i++) {
            for (int j = 0; j < clientsList.size(); j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0.0;
                } else {
                    distanceMatrix[i][j] = clientsList.get(i).getDistanceTo(clientsList.get(j));
                }
            }
        }
        return distanceMatrix;
    }
}
