package DataHandler;

import java.io.*;
import java.util.*;

public class DataRepositoryHandler {
    public static final String dataRepositoryPath = "src/DataRepository";

    public static HashMap<String, String> nameToDataIdMap = new HashMap<>();

    // NOTE: This function expects that the DataHandler.PersonalDataHandler holds the same ApplicationClasses.User data
    public static void setUpDataHandlerPathForUser(String username) {
        String dataIdPath = dataRepositoryPath + "/" + nameToDataIdMap.get(username);
        PersonalDataHandler.setDataDirectoryForUser(dataIdPath);
        SetMaxDataHandler.setDataDirectoryForUser(dataIdPath);
        CardioDataHandler.setDataDirectoryForUser(dataIdPath);
        WeightLiftingDataHandler.setDataDirectoryForUser(dataIdPath);
    }

    public static void loadDataFromDirectory() {
        PersonalDataHandler.loadData();
        SetMaxDataHandler.loadData();
        CardioDataHandler.loadData();
        WeightLiftingDataHandler.loadData();
    }

    public static void saveDataToDirectory() {
        PersonalDataHandler.saveData();
        SetMaxDataHandler.saveData();
        CardioDataHandler.saveData();
        WeightLiftingDataHandler.saveData();
    }
    
    public static boolean isValidUser(String username){
        return nameToDataIdMap.containsKey(username);
    }

    public static boolean isUniqueId(String id){
        return !nameToDataIdMap.containsValue(id);
    }

    public static void createNewUserDataDirectory(String username) {
        // Generate a unique dataId for the new user
        String dataId = UUID.randomUUID().toString().substring(0, 10);
        while (!isUniqueId(dataId))
            dataId = UUID.randomUUID().toString().substring(0, 10);

        // Put the new user to dataId mapping in the dataMap
        nameToDataIdMap.put(username, dataId);

        // Create folder directory of the user.
        File newFolder = new File(dataRepositoryPath + "/" + dataId);
        if (newFolder.mkdir()) {
            System.out.println("Created new folder: " + newFolder.getAbsolutePath());
        }

        // Save the new user data to the Data Repository for the Data Consistency
        setUpDataHandlerPathForUser(username);
        saveDataToDirectory();
    }

    public static void updateUsername(String newName) {
        // Update the name to DataRepo mapping
        String curName = PersonalDataHandler.getUsername();
        String id = nameToDataIdMap.get(curName);
        nameToDataIdMap.remove(curName);
        nameToDataIdMap.put(newName, id);
    }

    public static void saveData() {
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(dataRepositoryPath + "/UserToDataIdMapping.csv"))) {
            Set<String> keys = nameToDataIdMap.keySet();

            for(String key : keys){
                List<String> data = new ArrayList<>();
                data.add(key);
                data.add(nameToDataIdMap.get(key));

                dataWriter.write(String.join(",", data));
                dataWriter.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadData(){
        try(BufferedReader dataReader = new BufferedReader(new FileReader(dataRepositoryPath + "/UserToDataIdMapping.csv"))) {
            Scanner reader = new Scanner(dataReader);

            while (reader.hasNextLine()){
                String line = reader.nextLine();
                String[] values = line.split(","); // Split by comma

                nameToDataIdMap.put(values[0], values[1]);
            }
            // We are here means the file data is over, so save the last workout read
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
