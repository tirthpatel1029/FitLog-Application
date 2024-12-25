package DataHandler;

import ApplicationObject.CardioWorkout;
import java.io.*;
import java.util.*;

public class CardioDataHandler {
    private static String dataIdPath;

    private static List<CardioWorkout> allWorkouts = new ArrayList<>();

    public static void setDataDirectoryForUser(String directory) {
        dataIdPath = directory;
    }

    public static List<CardioWorkout> getAllData(){
        return allWorkouts;
    }
    
    public static void addNewWorkoutData(CardioWorkout workout){
        allWorkouts.add(workout);
    }
    
    public static void saveData(){
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(dataIdPath + "/cardioData.csv"))) {
            for (CardioWorkout workout : allWorkouts){
                List<String> data = new ArrayList<>();
                data.add(workout.getDate());
                data.add(workout.getName());
                data.add(Integer.toString(workout.getCaloriesBurned()));
                data.add(Integer.toString(workout.getTime()));

                dataWriter.write(String.join(",", data));
                dataWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadData(){
        try (BufferedReader dataReader = new BufferedReader(new FileReader(dataIdPath + "/cardioData.csv"))) {
            Scanner reader = new Scanner(dataReader);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] values = line.split(","); // Split by comma

                String date = values[0];
                String name = values[1];
                int calories = Integer.parseInt(values[2]);
                int time = Integer.parseInt(values[3]);

                allWorkouts.add(new CardioWorkout(name, date, calories, time));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
