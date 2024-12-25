package GUI;

import ApplicationObject.Validator;
import DataHandler.DataRepositoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

public class SignInScene {

    // where the user will enter their username
    @FXML
    public TextField usernameField;

    @FXML
    public Text invalidUsernameMessage;

    // this happens when the user selects the confirm button
    // it will validate the inputs and create a new user with the information provided if valid
    @FXML
    public void onConfirm(ActionEvent e) throws IOException {
        // validate the Username
        if (!Validator.validateCurrentUsername(usernameField.getText())) {
            // Invalid username: Display the error message
            invalidUsernameMessage.setVisible(true);
        }
        else {
            // Valid Username: Change the scene to the default home scene
            switchtoHomeScene(e);
        }
    }

    @FXML
    public void switchToSignUpScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "SignUpScene.fxml");
    }

    // navigates to the home scene
    public void switchtoHomeScene(ActionEvent e) throws IOException {
        // Before switching to the Home Scene,
        // load the user data from the data directory.
        DataRepositoryHandler.setUpDataHandlerPathForUser(usernameField.getText());
        DataRepositoryHandler.loadDataFromDirectory();

        // Switch to the HomeScene
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }
}
