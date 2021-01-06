import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader file = getInputFile();

        Integer numberOfVehicles = Integer.valueOf(file.readLine());
        Integer vehicleCapacity = Integer.valueOf(file.readLine());
        Set<Client> clients = getClients(file);

        for (Client client : clients) {
            System.out.println(client.getName() + ", " + client.getDemand());
        }
    }

    private static Set<Client> getClients(BufferedReader file) throws IOException {
        Set<Client> clients = new HashSet<>();
        String line = file.readLine();
        while (line != null) {
            String[] clientData = line.split(",");
            clients.add(new Client(clientData[0], Integer.valueOf(clientData[1]), Double.valueOf(clientData[2]), Double.valueOf(clientData[3])));
            line = file.readLine();
        }
        return clients;
    }

    private static BufferedReader getInputFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input file: ");
        String filename = scanner.next();
        return new BufferedReader(new FileReader(filename));
    }
}
