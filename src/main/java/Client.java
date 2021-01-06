public class Client {
    private final String name;
    private final Integer demand;
    private final Double latitude;
    private final Double longitude;

    public Client(String name, Integer demand, Double latitude, Double longitude) {
        this.name = name;
        this.demand = demand;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public Integer getDemand() {
        return demand;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
