package GUI;

import ApplicationObject.WeightLiftingSet;
import ApplicationObject.WeightLiftingWorkout;
import DataHandler.WeightLiftingDataHandler;
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
import java.util.List;
import java.util.ResourceBundle;

public class ViewWeightLiftingLogScene implements Initializable {
    @FXML
    private TableView<WeightLiftingTableRow> weightLiftingLogTable;

    @FXML
    private TableColumn<WeightLiftingTableRow, String> dateCol;

    @FXML
    private TableColumn<WeightLiftingTableRow, String> nameCol;

    @FXML
    private TableColumn<WeightLiftingTableRow, Integer> repCol;

    @FXML
    private TableColumn<WeightLiftingTableRow, Float> weightCol;

    public static class WeightLiftingTableRow {
        private final String date;
        private final String name;
        private final int repetitions;
        private final float weight;

        public WeightLiftingTableRow(String date, String name, int repetitions, float weight) {
            this.date = date;
            this.name = name;
            this.repetitions = repetitions;
            this.weight = weight;
        }

        public String getDate() {
            return date;
        }

        public String getName() {
            return name;
        }

        public int getRepetitions() {
            return repetitions;
        }

        public float getWeight() {
            return weight;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<WeightLiftingWorkout> weightLiftingList = WeightLiftingDataHandler.getAllData();

        ObservableList<WeightLiftingTableRow> observableWeightLiftingList = FXCollections.observableArrayList();
        for (WeightLiftingWorkout workout : weightLiftingList) {
            for (WeightLiftingSet set : workout.getSets()) {
                observableWeightLiftingList.add(new WeightLiftingTableRow(
                        workout.getDate(),
                        set.getName(),
                        set.getNumberOfReps(),
                        set.getWeightInPounds()));
            }
        }

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setSortType(TableColumn.SortType.DESCENDING);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        repCol.setCellValueFactory(new PropertyValueFactory<>("repetitions"));
        weightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

        weightLiftingLogTable.setItems(observableWeightLiftingList);
        weightLiftingLogTable.getSortOrder().add(dateCol);
        weightLiftingLogTable.sort();
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
