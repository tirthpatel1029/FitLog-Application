package ApplicationObject;

import java.util.ArrayList;
import java.util.List;

public class WeightLiftingWorkout {
    private String date;
    private final List<WeightLiftingSet> sets;

    public WeightLiftingWorkout(String date) {
        this.date = date;
        this.sets = new ArrayList<>();
    }

    public void addSet(WeightLiftingSet set) {
        sets.add(set);
    }

    public List<WeightLiftingSet> getSets() {
        return sets;
    }

    public String getDate() {
        return date;
    }
}
