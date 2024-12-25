import DataHandler.DataRepositoryHandler;
import DataHandler.PersonalDataHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Load the DataRepo: User to DataId mapping data
        DataRepositoryHandler.loadData();

        // Start the application and set up the scene
        Parent root = FXMLLoader.load(getClass().getResource("GUI/SignInScene.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        // If the user log-in and accidently closes the app before signing-out
        // We save the user data held by the application to prevent the data loss.
        if(PersonalDataHandler.userExist())
            DataRepositoryHandler.saveDataToDirectory();

        // Saves the data that is currently held by the application
        DataRepositoryHandler.saveData();

        // call the regular stop function to close the application
        super.stop();
    }
}