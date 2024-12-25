package ApplicationObject;

public class CardioWorkout extends Exercise {
    // the date of the workout
    private String date;

    // the calories burned during the workout
    private int caloriesBurned;

    // the duration of the workout in seconds
    private int time;

    // constructor for ApplicationClasses.CardioWorkout passing all values
    // calls the workout super class constructor
    public CardioWorkout(String n, String date, int caloriesBurned, int minutes) {
        super(n);
        this.date = date;
        this.caloriesBurned = caloriesBurned;
        this.time = minutes;
    }

    // returns the value for caloriesBurned
    public int getCaloriesBurned(){
        return this.caloriesBurned;
    }

    // returns the value for workout duration time
    public int getTime(){
        return this.time;
    }

    // returns the value for the date
    public String getDate() {
        return this.date;
    }
}
