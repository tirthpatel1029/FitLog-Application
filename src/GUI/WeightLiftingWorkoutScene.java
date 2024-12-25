package GUI;

import ApplicationObject.Validator;
import ApplicationObject.WeightLiftingSet;
import ApplicationObject.WeightLiftingWorkout;
import DataHandler.WeightLiftingDataHandler;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class WeightLiftingWorkoutScene implements Initializable {

    // object that holds an individual set
    @FXML
    private HBox set1;

    // object that holds all the sets formatted vertically
    @FXML
    private VBox setList;

    // text object that displays an error message if the input is not valid
    @FXML
    private Text errorMessage;

    // text object that holds the current date
    @FXML
    private DatePicker dateInput;

    //list of all the exercise options
    private final String[] exercises = {"Dumbbell Bench Press", "Barbell Bench Press", "Machine Chest Press", "Machine Chest Fly", "Dumbbell Chest Fly",
    "Dumbbell Row", "Cable Row", "Lat Pull-down", "Dumbbell Curl", "Dumbbell Triceps Extension", "Barbell Military Press",
    "Dumbbell Lateral Raise", "Barbell Squat", "Dumbbell Lunge", "Leg Extension","Seated Leg Curl","Sit-Ups","Crunches", "Mountain Climbers","Plank"};




    // submits the values, ending the workout
    public void onSubmit(ActionEvent e) throws IOException {
        if (!validateSubmit()){
            errorMessage.setVisible(true);
            return;
        }

        // save the new workout to the file
        saveWorkout();

        // go back to the home scene
        switchToHomeScene(e);
    }

    // create a workout object with the info that was provided
    // then saves that workout to a file using the DataHandler.WeightLiftingDataHandler class
    private void saveWorkout(){

        // create a list of all the sets that were created (The HBox class is a child class of Node)
        ObservableList<Node> allSets = this.setList.getChildren();

        // create a new workout object
        WeightLiftingWorkout newWorkout = new WeightLiftingWorkout(dateInput.getValue().toString());

        // create variables to hold the values for the current set that is being used
        HBox curSet;
        String exercise;
        int reps;
        float weight;

        // populate the workout object with each individual set of the workout
        for (var set : allSets){
            curSet = (HBox) set;
            exercise = ((ComboBox<String>)curSet.getChildren().get(0)).getValue();
            reps = Integer.parseInt(((TextField)curSet.getChildren().get(1)).getText());
            weight = Float.parseFloat(((TextField)curSet.getChildren().get(2)).getText());

            newWorkout.addSet(new WeightLiftingSet(exercise, reps, weight));
        }

        // save the workout that was created
        WeightLiftingDataHandler.addNewWorkoutData(newWorkout);
    }

    // validates the input for the weightlifting workout
    private boolean validateSubmit(){

        // checks that the date and time are input correctly
        if (dateInput.getValue() == null || !Validator.validateDate(dateInput.getValue().toString())){
            dateInput.setValue(LocalDate.now());
        }

        // creates a list of all the nodes inside the vbox
        var listOfSets = setList.getChildren();
        //Check to make sure there are sets in the workout to save
        if(listOfSets.isEmpty()) {
            return false;
        }
        // validates each individual set in the workout at a time
        for (Node node : listOfSets){
            if (!validateSet((HBox) node)){
                return false;
            }
        }

        // returns true if all tests are passed
        return true;
    }

    // validates one set at a time
    private boolean validateSet(HBox hBox){

        // get the list of nodes inside the hbox (Should be three items: a ComboBox<String> and two text fields)
        var setItems = hBox.getChildren();

        // make sure that an exercise was chosen for the set
        if (((ComboBox<String>)setItems.get(0)).getSelectionModel().isEmpty()) return false;

        // check that the user entered a positive integer value for repetitions
        if (!Validator.validatePositiveInt(((TextField)setItems.get(1)).getText())) return false;

        // check that the user entered a positive float value for weight
        if (!Validator.validatePositiveFloat(((TextField) setItems.get(2)).getText())) return false;

        return true;
    }

    // adds a set to the workout
    public void addSet(ActionEvent e) throws IOException {

        // create an FXMLLoader to import the design fxml for the set object
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SetObject.fxml"));

        // create a new node with the object loaded by FXMLLoader (The set object)
        HBox newNode = (HBox)loader.load();

        // populate the set object's ComboBox with values for exercise choices
        addExercises(newNode);

        // add the new object to the VBox containing all the sets in the workout
        setList.getChildren().add(newNode);
    }
    public void removeSet(ActionEvent e) {
        // Get the source of the event (the Remove button)
        Node source = (Node) e.getSource();

        // Get the parent of the button (which should be the HBox containing the set)
        HBox parentSet = (HBox) source.getParent();

        // Get the parent of the HBox, which is the VBox containing all sets
        VBox parentVBox = (VBox) parentSet.getParent();

        // Remove the HBox (set) from the VBox (setList)
        parentVBox.getChildren().remove(parentSet);
    }


    // adds the list of possible exercises to a hbox containing a combo box
    public void addExercises(HBox hBox){
        // make sure that the object was successfully imported
        if (hBox == null){
            return;
        }
        // get the ComboBox<String> from the set object
        ComboBox<String> exerciseBox = (ComboBox<String>) hBox.getChildren().get(0);

        // add the array of exercise names to the combo box for selection
        exerciseBox.getItems().addAll(exercises);
    }

    @FXML
    public void displayImage(ActionEvent e) {
        // Get the HBox containing the ComboBox for the set
        HBox currentHBox = (HBox) ((Node) e.getSource()).getParent();

        // Retrieve the ComboBox from the HBox (it's assumed to be the first child)
        ComboBox<String> exerciseComboBox = (ComboBox<String>) currentHBox.getChildren().get(0);

        // Get the selected exercise
        String selectedExercise = exerciseComboBox.getValue();

        if (selectedExercise == null || selectedExercise.isEmpty()) {
            // Show an alert if no exercise is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No ApplicationClasses.Exercise Selected");
            alert.setHeaderText("Please select an exercise from the dropdown.");
            alert.showAndWait();
            return;
        }

        // Define the mapping of exercises to image paths
        String imagePath;
        switch (selectedExercise) {
            case "Plank":
                imagePath = "/Images/Plank.jpg";
                break;
            case "Mountain Climbers":
                imagePath = "/Images/Mountain_Climbers.jpg";
                break;
            case "Crunches":
                imagePath = "/Images/Crunches.jpg";
                break;
            case "Sit-Ups":
                imagePath = "/Images/Sit_Ups.jpg";
                break;
            case "Seated Leg Curl":
                imagePath = "/Images/Seated_Leg_Curl.jpg";
                break;
            case "Leg Extension":
                imagePath = "/Images/Leg_Extension.jpg";
                break;
            case "Dumbbell Lunge":
                imagePath = "/Images/Dumbbell_Lunge.jpg";
                break;
            case "Barbell Squat":
                imagePath = "/Images/Barbell_Squat.jpg";
                break;
            case "Dumbbell Lateral Raise":
                imagePath = "/Images/Dumbbell_Lateral_Raise.jpg";
                break;
            case "Barbell Military Press":
                imagePath = "/Images/Barbell_Military_Press.jpg";
                break;
            case "Dumbbell Triceps Extension":
                imagePath = "/Images/Dumbbell_Triceps_Extensions.jpg";
                break;
            case "Dumbbell Curl":
                imagePath = "/Images/Dumbbell_Curl.jpg";
                break;
            case "Lat Pull-down":
                imagePath = "/Images/Lat_PullDown.jpg";
                break;
            case "Cable Row":
                imagePath = "/Images/Cable_Row.jpg";
                break;
            case "Dumbbell Row":
                imagePath = "/Images/Dumbbell_Row.jpg";
                break;
            case "Dumbbell Chest Fly":
                imagePath = "/Images/Dumbbell_Chest_Fly.jpg";
                break;
            case "Machine Chest Fly":
                imagePath = "/Images/Machine_Chest_Fly.jpg";
                break;
            case "Machine Chest Press":
                imagePath = "/Images/Machine_CP.jpg";
                break;
            case "Dumbbell Bench Press":
                imagePath = "/Images/DB_Bench.jpeg";
                break;
            case "Barbell Bench Press":
                imagePath = "/Images/Barbell_BP.jpg";
                break;
            default:
                // Handle cases where the selected exercise has no associated image
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Image Available");
                alert.setHeaderText("No image available for the selected exercise.");
                alert.showAndWait();
                return;
        }

        // Check if the image exists in the resources folder
        URL resourceUrl = getClass().getResource(imagePath);
        if (resourceUrl == null) {
            System.out.println("Image not found: " + imagePath);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Image Not Found");
            alert.setHeaderText("The image for the selected exercise could not be found.");
            alert.showAndWait();
            return;
        }
        // Create the popup
        Popup popup = new Popup();

        // Create an ImageView with the selected image
        Image image = new Image(resourceUrl.toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(400); // Adjust size as necessary
        imageView.setFitHeight(400);

        // Create the close button ("X")
        Button closeButton = new Button("X");
        closeButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px;");
        closeButton.setOnAction(event -> {
            // Close the popup when the "X" button is clicked
            popup.hide();
        });

        // Create a VBox to hold the image and close button
        VBox popupContent = new VBox(closeButton, imageView);
        popupContent.setSpacing(10);
        popupContent.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");

        popup.getContent().add(popupContent);

        // Show the popup
        popup.show(((Node) e.getSource()).getScene().getWindow());
    }


    // Switches to the home scene without saving the workout
    public void switchToHomeScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }

    // initializes the starting HBox with the exercise values
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // adds the exercise names to the ComboBox<String> for the first set
        addExercises(set1);
        if (dateInput != null) dateInput.setValue(LocalDate.now());
    }
}
