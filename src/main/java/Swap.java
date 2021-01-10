import java.util.Objects;

public class Swap {
    private final Client client1;
    private final Client client2;

    public int getCadence() {
        return cadence;
    }

    private int cadence;

    public Swap(Client client1, Client client2, int cadence) {
        this.client1 = client1;
        this.client2 = client2;
        this.cadence = cadence;
    }

    public Client getClient1() {
        return client1;
    }

    public Client getClient2() {
        return client2;
    }

    public int decrementCadence() {
        return --cadence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Swap swap = (Swap) o;
        return Objects.equals(client1.getName(), swap.client1.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(client1, client2);
    }
}
