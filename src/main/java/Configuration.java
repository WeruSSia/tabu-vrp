public class Configuration {

    private int maximumIterations;
    private int tabuListSize;
    private int cadence;
    private int randomGeneratorSeed;

    public Configuration(int maximumIterations, int tabuListSize, int cadence, int randomGeneratorSeed) {
        this.maximumIterations = maximumIterations;
        this.tabuListSize = tabuListSize;
        this.cadence = cadence;
        this.randomGeneratorSeed = randomGeneratorSeed;
    }

    public int getMaximumIterations() {
        return maximumIterations;
    }

    public int getTabuListSize() {
        return tabuListSize;
    }

    public int getCadence() {
        return cadence;
    }

    public int getRandomGeneratorSeed() {
        return randomGeneratorSeed;
    }
}
