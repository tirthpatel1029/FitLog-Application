package GUI;

import ApplicationObject.Validator;
import DataHandler.PersonalDataHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ViewPersonalDataScene implements Initializable {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField dobField;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private TextField genderField;

    @FXML
    private ComboBox<String> genderBox;
    private final String[] genderArray = {"Male", "Female"};

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

    @FXML
    private Button updateDataButton;

    @FXML
    private Button confirmUpdateButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDisplayModeFieldValues();
        genderBox.getItems().addAll(genderArray);
    }

    private void setDisplayModeFieldValues() {
        usernameField.setText(PersonalDataHandler.getUsername());
        heightField.setText(PersonalDataHandler.getHeight().toString());
        weightField.setText(PersonalDataHandler.getWeight().toString());
        LocalDate dob = LocalDate.parse(PersonalDataHandler.getDob());
        dobField.setText(dob.getMonthValue() + "/" + dob.getDayOfMonth() + "/" + dob.getYear());
        genderField.setText(PersonalDataHandler.getGender());
    }

    @FXML
    private void setFieldsToUpdateMode(ActionEvent event) throws IOException {
        usernameField.setEditable(true);

        heightField.setEditable(true);

        weightField.setEditable(true);

        dobField.setVisible(false);
        dobPicker.setVisible(true);
        dobPicker.setValue(LocalDate.parse(PersonalDataHandler.getDob()));

        genderField.setVisible(false);
        genderBox.setVisible(true);
        genderBox.setValue(PersonalDataHandler.getGender());

        updateDataButton.setVisible(false);
        confirmUpdateButton.setVisible(true);
    }

    private void setFieldsToDisplayMode() {
        usernameField.setEditable(false);

        heightField.setEditable(false);

        weightField.setEditable(false);

        dobField.setVisible(true);
        dobPicker.setVisible(false);

        genderField.setVisible(true);
        genderBox.setVisible(false);

        setDisplayModeFieldValues();

        updateDataButton.setVisible(true);
        confirmUpdateButton.setVisible(false);
    }

    @FXML
    private void updateAccountDetails(ActionEvent event) throws IOException {
        if (!PersonalDataHandler.getUsername().equals(usernameField.getText()) &&
                !Validator.validateNewUsername(usernameField.getText()))
            invalidUsernameMessage.setVisible(true);
        if (!Validator.validatePositiveFloat(heightField.getText()))
            invalidHeightMessage.setVisible(true);
        if (!Validator.validatePositiveFloat(weightField.getText()))
            invalidWeightMessage.setVisible(true);
        if (dobPicker.getValue() == null)
            invalidDobMessage.setVisible(true);
        if (genderBox.getValue() == null)
            invalidGenderMessage.setVisible(true);
        else {
            updateUserData();
            setFieldsToDisplayMode();
        }
    }

    private void updateUserData() {
        PersonalDataHandler.updateUsername(usernameField.getText());
        PersonalDataHandler.updateHeight(Float.parseFloat(heightField.getText()));
        PersonalDataHandler.updateWeight(Float.parseFloat(weightField.getText()));
        PersonalDataHandler.updateDob(dobPicker.getValue().toString());
        PersonalDataHandler.updateGender(genderBox.getValue());
    }

    // Sets the current scene to HomeScene scene
    @FXML
    public void switchToHomeScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }
}
