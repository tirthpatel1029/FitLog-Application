package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;

public class ViewDataScene{

    @FXML
    public void switchToCardioLogScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "ViewCardioLogScene.fxml");
    }

    @FXML
    public void switchToWeightLiftingLogScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "ViewWeightLiftingLogScene.fxml");
    }

    @FXML
    public void switchToSetMaxDataScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "ViewSetMaxDataScene.fxml");
    }

    @FXML
    public void switchToHomeScene(ActionEvent e) throws IOException {
        // Switch to HomeScene.fxml
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }
}
