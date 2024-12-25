package GUI;

import DataHandler.SetMaxDataHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

public class ViewSetMaxDataScene implements Initializable {
    @FXML
    private TableView<SetMaxTableRow> setMaxDataTable;

    @FXML
    private TableColumn<SetMaxTableRow, String> nameCol;

    @FXML
    private TableColumn<SetMaxTableRow, Float> maxCol;

    public static class SetMaxTableRow {
        private final String name;
        private final Float max;

        public SetMaxTableRow(String name, Float max) {
            this.name = name;
            this.max = max;
        }

        public String getName() {
            return name;
        }

        public Float getMax() {
            return max;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, Float> setMaxList = SetMaxDataHandler.getAllData();

        ObservableList<SetMaxTableRow> observableSetMaxList = FXCollections.observableArrayList();
        Set<String> keys = setMaxList.keySet();
        for(String key : keys) {
            observableSetMaxList.add(new SetMaxTableRow(key, setMaxList.get(key)));
        }

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        maxCol.setCellValueFactory(new PropertyValueFactory<>("max"));

        setMaxDataTable.setItems(observableSetMaxList);
        setMaxDataTable.sort();
    }

    @FXML
    public void switchToHomeScene(ActionEvent e) throws IOException {
        // Switch to HomeScene.fxml
        new SceneChanger().switchToScene(e, "HomeScene.fxml");
    }

    @FXML
    public void switchToViewDataScene(ActionEvent e) throws IOException {
        new SceneChanger().switchToScene(e, "ViewDataScene.fxml");
    }
}
