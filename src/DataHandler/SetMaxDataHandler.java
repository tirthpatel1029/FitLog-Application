package DataHandler;

import java.io.*;
import java.util.*;

public class SetMaxDataHandler{
    private static String dataIdPath;

    private static HashMap<String, Float> allMax = new HashMap<>();

    public static HashMap<String, Float> getAllData(){
        return allMax;
    }

    public static void setDataDirectoryForUser(String directory) {
        dataIdPath = directory;
    }

    public static float getMax(String exercise){
        return (allMax.get(exercise) == null) ? 0f : allMax.get(exercise);
    }

    public static void updateMax(String exercise, float max){
        allMax.put(exercise, max);
    }

    public static void saveData() {
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(dataIdPath + "/setMaxData.csv"))) {
            Set<String> keys = allMax.keySet();

            for(String key : keys){
                List<String> data = new ArrayList<>();
                data.add(key);
                data.add(Float.toString(allMax.get(key)));

                dataWriter.write(String.join(",", data));
                dataWriter.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadData(){
        try(BufferedReader dataReader = new BufferedReader(new FileReader(dataIdPath + "/setMaxData.csv"))) {
            Scanner reader = new Scanner(dataReader);

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String[] values = line.split(","); // Split by comma

                allMax.put(values[0], Float.parseFloat(values[1]));
            }
            // We are here means the file data is over, so save the last workout read
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
