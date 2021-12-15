import java.io.Serializable;
import java.util.*;

public class Log implements Serializable {
    private int seed;
    private Settings settings;
    private HashMap<Integer, String> choices;

    Log(int seed, Settings settings) {
        this.seed = seed;
        this.settings = settings;
    }

    public int getSeed() { return seed; }

    public Settings getSettings() {
        return settings;
    }

    public String getChoice(int step) {
        if (!choices.isEmpty() && choices.containsKey(step)) {
            return choices.get(step);
        }
        return null;
    }

    public void add(int step, City city) {
        choices.put(step, city.getName());
    }
}
