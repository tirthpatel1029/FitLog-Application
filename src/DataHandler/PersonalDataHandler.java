package DataHandler;

import ApplicationObject.User;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class PersonalDataHandler {
    private static String dataIdPath;

    private static User user;

    private static List<Map.Entry<String, Float>> heightData = new ArrayList<>();

    private static List<Map.Entry<String, Float>> weightData =  new ArrayList<>();

    public static boolean userExist() {
        return user != null;
    }

    public static void setDataDirectoryForUser(String directory) {
        dataIdPath = directory;
    }

    public static String getUsername() {
        return user.getUsername();
    }

    public static Float getHeight() {
        return user.getHeight();
    }

    public static Float getWeight() {
        return user.getWeight();
    }

    public static String getDob() {
        return user.getDob();
    }

    public static String getGender() {
        return user.getGender();
    }

    public static void createNewUserAccount(String name, Float height, Float weight, String dob, String gender) {
        user = new User(name, height, weight, dob, gender);
        // Since it is a new account, we add the initial Height and Weight to our height and weight data.
        heightData.add(new AbstractMap.SimpleEntry<>(LocalDate.now().toString(), height));
        weightData.add(new AbstractMap.SimpleEntry<>(LocalDate.now().toString(), weight));
    }

    public static void updateUsername(String name) {
        // We only update the data if we have new information
        if(!name.equals(user.getUsername())) {
            // Update the name to DataRepo mapping
            DataRepositoryHandler.updateUsername(name);

            // Update the username after the mapping is updated
            user.setUsername(name);
        }
    }

    public static void updateHeight(Float height) {
        // We only update the data if we have new information
        if(!height.equals(user.getHeight())) {
            user.setHeight(height);
            heightData.add(new AbstractMap.SimpleEntry<>(LocalDate.now().toString(), height));
            if (heightData.size() > 5)
                heightData.remove(5);
        }
    }

    public static void updateWeight(Float weight) {
        // We only update the data if we have new information
        if(!weight.equals(user.getWeight())) {
            user.setWeight(weight);
            weightData.add(new AbstractMap.SimpleEntry<>(LocalDate.now().toString(), weight));
            if (weightData.size() > 5)
                weightData.remove(5);
        }
    }

    public static void updateDob(String dob) {
        // We only update the data if we have new information
        if(!dob.equals(user.getDob()))
            user.setDob(dob);
    }

    public static void updateGender(String gender) {
        // We only update the data if we have new information
        if(!gender.equals(user.getGender()))
            user.setGender(gender);
    }

    public static void saveData(){
        try (BufferedWriter dataWriter = new BufferedWriter(new FileWriter(dataIdPath + "/personalData.csv"))) {
            dataWriter.write(user.getUsername());
            dataWriter.newLine();

            List<String> data = new ArrayList<>();
            for (Map.Entry<String, Float> entry : heightData) {
                data.add("{" + entry.getKey() + ":" + entry.getValue() + "}");
            }
            dataWriter.write(String.join(",", data));
            dataWriter.newLine();

            data = new ArrayList<>();
            for (Map.Entry<String, Float> entry : weightData) {
                data.add("{" + entry.getKey() + ":" + entry.getValue() + "}");
            }
            dataWriter.write(String.join(",", data));
            dataWriter.newLine();

            dataWriter.write(user.getDob());
            dataWriter.newLine();

            dataWriter.write(user.getGender());
            dataWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(){
        try (BufferedReader dataReader = new BufferedReader(new FileReader(dataIdPath + "/personalData.csv"))) {
            Scanner reader = new Scanner(dataReader);

            String username = reader.nextLine();

            String heightLine = reader.nextLine();
            String[] heightValues = heightLine.split(",");
            heightData = new ArrayList<>();
            for (String value : heightValues) {
                value = value.replace("{","");
                value = value.replace("}","");
                String[] token = value.split(":");
                heightData.add(new AbstractMap.SimpleEntry<>(token[0], Float.parseFloat(token[1])));
            }

            String weightLine = reader.nextLine();
            String[] wieghtValues = weightLine.split(",");
            weightData = new ArrayList<>();
            for (String value : wieghtValues) {
                value = value.replace("{","");
                value = value.replace("}","");
                String[] token = value.split(":");
                weightData.add(new AbstractMap.SimpleEntry<>(token[0], Float.parseFloat(token[1])));
            }

            String age = reader.nextLine();

            String gender = reader.nextLine();

            user = new User(username,
                    heightData.getLast().getValue(),
                    weightData.getLast().getValue(),
                    age,
                    gender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
