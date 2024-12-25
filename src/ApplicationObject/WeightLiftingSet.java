package ApplicationObject;

import DataHandler.SetMaxDataHandler;

public class WeightLiftingSet extends Exercise implements CalculatesMax{
    private int numberOfReps;
    private float weightInPounds;

    public WeightLiftingSet(String exerciseName, int numberOfReps, float weightInPounds) {
        super(exerciseName);
        this.numberOfReps = numberOfReps;
        this.weightInPounds = weightInPounds;
        checkMax(numberOfReps, weightInPounds);
    }

    private void checkMax(int repetitions, float weightLbs){
        // using Epley's equation:
        float curSetMax = calculateMax(repetitions, weightLbs);

        // get the old max from that exercise
        float oldMax = SetMaxDataHandler.getMax(this.getName());
        if (oldMax < curSetMax || oldMax == 0){
            SetMaxDataHandler.updateMax(this.getName(), curSetMax);
        }
    }

    public float calculateMax(int repetitions, float weight){
        return weight * ( 1 + (repetitions / 30f));
    }

    // returns the number of repetitions completed during the set
    public int getNumberOfReps() {
        return numberOfReps;
    }

    public float getWeightInPounds() {
        return weightInPounds;
   }
}
