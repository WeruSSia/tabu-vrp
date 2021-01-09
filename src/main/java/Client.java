public class Client {
    private final Integer id;
    private final String name;
    private final Integer demand;
    private final double latitude;
    private final double longitude;

    public Client(Integer id, String name, Integer demand, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.demand = demand;
        this.latitude = latitude * Math.PI / 180;
        this.longitude = longitude * Math.PI / 180;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getDemand() {
        return demand;
    }

    public Double getDistanceTo(Client client) {
        return 6377.830272 * Math.acos((Math.sin(this.latitude) * Math.sin(client.latitude)) + Math.cos(this.latitude) * Math.cos(client.latitude) * Math.cos(client.longitude - this.longitude));
    }
}
