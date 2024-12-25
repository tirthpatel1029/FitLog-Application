package GUI;

import ApplicationObject.CardioWorkout;
import ApplicationObject.Validator;
import DataHandler.CardioDataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CardioWorkoutScene implements Initializable {

    // View for the exercise demonstration
    @FXML
    private ImageView exerciseDemo;

    // Combo box to select and exercise
    @FXML
    private ComboBox<String> exerciseSelection;

    // array that holds the file locations for the cardio exercises
    // the array for the file paths match the exercise names array
    private final Image[] cardioImages = {new Image("images/Elliptical.jpg"),
            new Image("images/StairMaster.jpg"),
            new Image("images/StationaryBike.png"),
            new Image("images/Treadmill_Running.jpg"),
            new Image("images/Treadmill_Walking.jpg")};

    // array that holds the names of the exercises for cardio that can be chosen
    private final String[] cardioExerciseNames = {"Elliptical", "Stairmaster",
            "StationaryBike", "Treadmill Run", "Treadmill Walk"};

    //Text field for duration input
    @FXML
    private TextField durationInput;

    // Text field for caloric burn input
    @FXML
    private TextField caloriesBurnedInput;

    // Text field to enter the date of the workout
    @FXML
    private DatePicker dateInput;

    //Error message that displays when the inputs are invalid
    @FXML
    private Text errorMessage;

    // Function to submit the form
    public void onSubmit(ActionEvent e) throws IOException {

        // check to ensure that the inputs from the user are valid
        if (!validateInputs()){
            errorMessage.setVisible(true);
            return;
        }

        // create a new cardio workout object
        // save the data from the newly created cardio workout
        CardioDataHandler.addNewWorkoutData(createCardioWorkout());

        // switch to the home scene of the app
        switchToHomeScene(e);
    }

    // creates a ApplicationClasses.CardioWorkout with the data that is in the fields of the cardioWorkoutScene
    private CardioWorkout createCardioWorkout(){
        // integer to hold the number of calories burned
        int caloriesBurned;

        // set the number of calories burned
        try {
            caloriesBurned = Integer.parseInt(caloriesBurnedInput.getText());
        } catch (NumberFormatException ex) {

            // sets the calories burned during the cardio workout to -1 if no data was entered
            caloriesBurned = -1;
        }

        // convert the text field for duration of workout
        int duration = Integer.parseInt(durationInput.getText());

        // get the value for the date that was input by the user
        String date = dateInput.getValue().toString();

        // create a new cardioWorkout
		return new CardioWorkout(exerciseSelection.getValue(), date, caloriesBurned, duration);
    }

    // checks whether the fields in the cardioWorkoutScene are valid to create a new cardioWorkout
    private boolean validateInputs(){


        // check if the calories burned is a valid integer
        if (!Validator.validatePositiveInt(caloriesBurnedInput.getText())){
            return false;
        }


        // return false if the user did not enter a positive integer for duration
        if (!Validator.validatePositiveInt(durationInput.getText())){
            return false;
        }

        // return false if the user did not select an exercise
        if (exerciseSelection.getValue() == null){
            return false;
        }

        // check if the date value is formatted correctly
        if (!Validator.validateDate(dateInput.getValue().toString())) return false;

        // return true if all the tests are passed
        return true;
    }

    // adds the options to the exercise combo box
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseSelection.getItems().addAll(cardioExerciseNames);
        dateInput.setValue(LocalDate.now());
    }

    // changes the image displayed when the user selects an exercise
    public void setImageView(ActionEvent event){
        // check each exercise in the array of exercise names
        for (int i = 0; i < cardioExerciseNames.length; i++){

            // if the exercise matches the array at the current index,
            // use the corresponding index to populate the ImageView for the exerciseDemo
            if (cardioExerciseNames[i].equals(exerciseSelection.getValue())){
                exerciseDemo.setImage(cardioImages[i]);
            }
        }
    }

    // sets the current scene to homeScene
    public void switchToHomeScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }

}
