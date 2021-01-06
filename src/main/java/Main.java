import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader file = getInputFile();

        Set<Vehicle> vehicles = initializeVehicles(file);
        Set<Client> clients = getClients(file);

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getCapacity());
        }

        for (Client client : clients) {
            System.out.println(client.getName() + ", " + client.getDemand());
        }

        System.out.println(new TabuVRP().route(vehicles, clients));
    }

    private static Set<Vehicle> initializeVehicles(BufferedReader file) throws IOException {
        Set<Vehicle> vehicles = new HashSet<>();
        int numberOfVehicles = Integer.parseInt(file.readLine());
        int vehicleCapacity = Integer.parseInt(file.readLine());
        for (int i = 0; i < numberOfVehicles; i++) {
            vehicles.add(new Vehicle(vehicleCapacity));
        }
        return vehicles;
    }

    private static Set<Client> getClients(BufferedReader file) throws IOException {
        Set<Client> clients = new HashSet<>();
        String line = file.readLine();
        while (line != null) {
            String[] clientData = line.split(",");
            clients.add(new Client(clientData[0], Integer.valueOf(clientData[1]), Double.parseDouble(clientData[2]), Double.parseDouble(clientData[3])));
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
}
