package GUI;

import javafx.event.ActionEvent;

import java.io.IOException;

public class WorkoutTypeSelectionScene {

    // sets the current scene to home scene
    public void switchToHomeScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }

    // sets the current scene to weightlifting workout scene
    public void switchToWeightLiftingWorkoutScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "WeightLiftingWorkoutScene.fxml");
    }

    // sets the current scene to CardioWorkoutScene
    public void switchToCardioWorkoutScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "CardioWorkoutScene.fxml");
    }

}
