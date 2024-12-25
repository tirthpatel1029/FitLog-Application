package ApplicationObject;

import DataHandler.DataRepositoryHandler;

import java.time.LocalDate;

public class Validator {

    // checks if the date and time can be formatted, returns true if not
    public static boolean validateDate(String dateTime){
        try{
            LocalDate.parse(dateTime);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    // checks to make sure that the text field was provided with a positive integer
    public static boolean validatePositiveInt(String value){

        // check that the user entered a
        if (value.isEmpty()){
            return false;
        }

        // check that the user entered a positive integer
        try{
            if (Integer.parseInt(value) < 0){
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    // checks to make sure that the text field was provided with a positive float
    public static boolean validatePositiveFloat(String value){

        // check that the user entered values into the field
        if (value.isEmpty()){
            return false;
        }

        // check that the user entered a positive integer
        try{
            if (Float.parseFloat(value) < 0){
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static boolean validateNewUsername(String value){
        // check that the user entered values into the field
        if (value.isEmpty()) return false;

        // Check if the username is already used
        if(DataRepositoryHandler.isValidUser(value))
            return false;

        return true;
    }

    public static boolean validateCurrentUsername(String value){
        // Check that the user has entered something into the field
        if (value.isEmpty()) return false;

        // Check if it is an existing username
        return DataRepositoryHandler.isValidUser(value);
    }
}
