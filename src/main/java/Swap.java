public class Swap {
    private Client client1;
    private Client client2;

    public Swap(Client client1, Client client2) {
        this.client1 = client1;
        this.client2 = client2;
    }

    public Client getClient1() {
        return client1;
    }

    public Client getClient2() {
        return client2;
    }
}
