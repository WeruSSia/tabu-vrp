import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TabuList {
    private List<Swap> swaps;
    private int size;

    public TabuList(int size) {
        this.swaps = new ArrayList<>();
        this.size = size;
    }

    public void checkSwapsCadence() {
        swaps.removeIf(swap -> swap.decrementCadence() == 0);
    }

    public void addSwap(Swap swap) {
        if (swaps.size() < size) {
            swaps.add(swap);
        }
    }

    public boolean doesContainSwap(Swap swap) {
        Swap oppositeSwap = new Swap(swap.getClient2(), swap.getClient1(), swap.getCadence());
        return swaps.contains(swap) || swaps.contains(oppositeSwap);
    }

    public void printTabuList() {
        swaps.forEach(swap -> System.out.print(swap.getClient1().getName() + ", " + swap.getClient2().getName() + "; "));
    }
}
