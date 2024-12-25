package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneChanger {

    // sets the current scene to the string path that is passed
    public void switchToScene(ActionEvent e, String fileName) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fileName));
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
