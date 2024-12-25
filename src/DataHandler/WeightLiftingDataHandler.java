package DataHandler;

import ApplicationObject.WeightLiftingSet;
import ApplicationObject.WeightLiftingWorkout;
import java.io.*;
import java.util.*;

public class WeightLiftingDataHandler {
    private static String dataIdPath;

    private static List<WeightLiftingWorkout> allWorkouts = new ArrayList<>();

    public static void setDataDirectoryForUser(String directory) {
        dataIdPath = directory;
    }

    public static List<WeightLiftingWorkout> getAllData(){
        return allWorkouts;
    }

    public static void addNewWorkoutData(WeightLiftingWorkout workout){
        allWorkouts.add(workout);
    }

    public static void saveData(){
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(dataIdPath + "/weightLiftingData.csv"))) {
            for (WeightLiftingWorkout workout : allWorkouts){
                List<WeightLiftingSet> sets = workout.getSets();
                for (WeightLiftingSet set : sets) {
                    List<String> data = new ArrayList<>();
                    data.add(workout.getDate());
                    data.add(set.getName());
                    data.add(Integer.toString(set.getNumberOfReps()));
                    data.add(Float.toString(set.getWeightInPounds()));

                    dataWriter.write(String.join(",", data));
                    dataWriter.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(){
        try(BufferedReader dataReader = new BufferedReader(new FileReader(dataIdPath + "/weightLiftingData.csv"))) {
            Scanner reader = new Scanner(dataReader);

            WeightLiftingWorkout curWorkout = new WeightLiftingWorkout("");     // Temporary initiation
            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String[] values = line.split(","); // Split by comma

                String date = values[0];
                if(!date.equals(curWorkout.getDate())){
                    // When the curDate changes, the logged workout changes
                    // So we add the last workout, and update the parameters
                    if(curWorkout.getDate().isEmpty()){
                        // curDate is empty means, we have just started reading the file
                        // So create a new workout to store data.
                        curWorkout = new WeightLiftingWorkout(date);
                    }
                    else{
                        // save and update parameters
                        allWorkouts.add(curWorkout);
                        curWorkout = new WeightLiftingWorkout(date);
                    }
                }
                String name = values[1];
                int repetition = Integer.parseInt(values[2]);
                float weight = Float.parseFloat(values[3]);

                curWorkout.addSet(new WeightLiftingSet(name, repetition, weight));
            }
            // We are here means the file data is over, so save the last workout read
            allWorkouts.add(curWorkout);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
