package GUI;

import DataHandler.DataRepositoryHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeScene {

    // sets the current scene to viewdata scene
    @FXML
    public void switchToViewDataScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "ViewDataScene.fxml");
    }

    // sets the current scene to the Workout Selection scene
    @FXML
    public void switchToWorkoutTypeSelectionScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "WorkoutTypeSelectionScene.fxml");
    }

    @FXML
    public void switchToViewPersonalDataScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "ViewPersonalDataScene.fxml");
    }

    @FXML
    public void signOutToSignInScene(ActionEvent e) throws IOException {
        DataRepositoryHandler.saveDataToDirectory();
        new SceneChanger().switchToScene(e, "SignInScene.fxml");
    }
}
