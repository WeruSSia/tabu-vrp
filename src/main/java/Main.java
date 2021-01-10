import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader file = getVRPInputFile();
        Configuration configuration = getConfiguration();

        List<Vehicle> vehicles = initializeVehicles(file);
        List<Client> clients = getClients(file);

        for (Client client : clients) {
            System.out.println(client.getId().toString() + ", " + client.getName() + ", " + client.getDemand());
        }

        new TabuVRP(vehicles, clients, configuration).solve();
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
        for (int id = 0; line != null; id++) {
            String[] clientData = line.split(",");
            clients.add(new Client(id, clientData[0], Integer.valueOf(clientData[1]), Double.parseDouble(clientData[2]), Double.parseDouble(clientData[3])));
            line = file.readLine();
        }
        return clients;
    }

    private static BufferedReader getVRPInputFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("VRP Input file: ");
        String fileName = scanner.next();
        return new BufferedReader(new FileReader(fileName));
    }

    private static Configuration getConfiguration() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Configuration file: ");
        String fileName = scanner.next();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        int maximumIterations = Integer.parseInt(file.readLine());
        int tabuListSize = Integer.parseInt(file.readLine());
        int cadence = Integer.parseInt(file.readLine());
        int randomGeneratorSeed = Integer.parseInt(file.readLine());
        return new Configuration(maximumIterations, tabuListSize, cadence, randomGeneratorSeed);
    }
}
