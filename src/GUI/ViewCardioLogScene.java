package GUI;

import ApplicationObject.CardioWorkout;
import DataHandler.CardioDataHandler;
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

public class ViewCardioLogScene implements Initializable {

    @FXML
    private TableView<CardioTableRow> cardioLogTable;

    @FXML
    private TableColumn<CardioTableRow, String> dateCol;

    @FXML
    private TableColumn<CardioTableRow, String> nameCol;

    @FXML
    private TableColumn<CardioTableRow, Integer> caloriesCol;

    @FXML
    private TableColumn<CardioTableRow, Integer> timeCol;

    public static class CardioTableRow {
        private final String date;
        private final String name;
        private final int calories;
        private final int time;

        public CardioTableRow(String date, String name, int calories, int time) {
            this.date = date;
            this.name = name;
            this.calories = calories;
            this.time = time;
        }

        public String getDate() {
            return date;
        }

        public String getName() {
            return name;
        }

        public int getCalories() {
            return calories;
        }

        public int getTime() {
            return time;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<CardioWorkout> cardioList = CardioDataHandler.getAllData();

        ObservableList<CardioTableRow> observableCardioList = FXCollections.observableArrayList();
        for (int i = 0; i < cardioList.size(); i++) {
            observableCardioList.add(new CardioTableRow(
                    cardioList.get(i).getDate(),
                    cardioList.get(i).getName(),
                    cardioList.get(i).getCaloriesBurned(),
                    cardioList.get(i).getTime()));
        }

        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCol.setSortType(TableColumn.SortType.DESCENDING);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        caloriesCol.setCellValueFactory(new PropertyValueFactory<>("calories"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        cardioLogTable.setItems(observableCardioList);
        cardioLogTable.getSortOrder().add(dateCol);
        cardioLogTable.sort();
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
