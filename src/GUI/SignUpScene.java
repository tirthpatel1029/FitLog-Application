package GUI;

import ApplicationObject.Validator;
import DataHandler.DataRepositoryHandler;
import DataHandler.PersonalDataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpScene implements Initializable {

    // array to hold the gender fields
    private final String[] genderArray = {"Male", "Female"};

    // where the user will enter their username
    @FXML
    private TextField usernameField;

    // where user will enter their weight
    @FXML
    private TextField weightField;

    // where the user will enter their height
    @FXML
    private TextField heightField;

    // where the user will enter their age
    @FXML
    private DatePicker dobField;

    // where the user will select their gender
    @FXML
    private ComboBox<String> genderField;

    // holds the message displayed if the user entered invalid inputs
    @FXML
    private Text invalidUsernameMessage;
    @FXML
    private Text invalidHeightMessage;
    @FXML
    private Text invalidWeightMessage;
    @FXML
    private Text invalidDobMessage;
    @FXML
    private Text invalidGenderMessage;


    // this happens when the user selects the confirm button
    // it will validate the inputs and create a new user with the information provided if valid
    @FXML
    public void onConfirm(ActionEvent e) throws IOException {
        // validate the Inputs
        // Validate the individual input fields
        if (!Validator.validateNewUsername(usernameField.getText()))
            invalidUsernameMessage.setVisible(true);
        if (!Validator.validatePositiveFloat(heightField.getText()))
            invalidHeightMessage.setVisible(true);
        if (!Validator.validatePositiveFloat(weightField.getText()))
            invalidWeightMessage.setVisible(true);
        if (dobField.getValue() == null)
            invalidDobMessage.setVisible(true);
        if (genderField.getValue() == null)
            invalidGenderMessage.setVisible(true);
        else {
            // Valid inputs:
            // Create a new user account
            createNewUser();
            // Change the scene to the default home scene
            switchToHomeScene(e);
        }
    }

    // this will create a new user using the information in the fields
    public void createNewUser(){
        // Create a new user account using the DataHandler.PersonalDataHandler
        PersonalDataHandler.createNewUserAccount(
                usernameField.getText(),
                Float.parseFloat(heightField.getText()),
                Float.parseFloat(weightField.getText()),
                dobField.getValue().toString(),
                genderField.getValue());
        // And also create a Data Directory for the new ApplicationClasses.User
        DataRepositoryHandler.createNewUserDataDirectory(usernameField.getText());
    }

    // navigates to the home scene
    public void switchToHomeScene(ActionEvent e) throws IOException {
        // Before switching to the Home Scene,
        // load the user data from the data directory.
        DataRepositoryHandler.setUpDataHandlerPathForUser(usernameField.getText());
        DataRepositoryHandler.loadDataFromDirectory();

        // Switch to the HomeScene
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }

    public void switchToSignInScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "SignInScene.fxml");
    }

    // populate the combo box with the gender array
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderField.getItems().addAll(genderArray);
    }
}
