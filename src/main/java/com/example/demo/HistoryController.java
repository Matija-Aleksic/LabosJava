package com.example.demo;

import hr.java.production.database.Database;
import hr.java.production.model.Category;
import hr.java.production.model.Item;
import hr.java.production.model.ItemHistory;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static hr.java.production.model.Category.loadCategoriesFromFile;
import static hr.java.production.model.Item.loadItemsFromFile;

public class HistoryController {

    @FXML
    private TableView<ItemHistory> historyTableView;

    @FXML
    private TableColumn<ItemHistory, String> idColumn;

    @FXML
    private TableColumn<ItemHistory, String> itemIdColumn;

    @FXML
    private TableColumn<ItemHistory, String> changeOnColumn;

    @FXML
    private TableColumn<ItemHistory, String> oldValueColumn;

    @FXML
    private TableColumn<ItemHistory, String> newValueColumn;

    public void initialize() throws SQLException, IOException {
        Database database = new Database();
        database.openConnection();
        database.getChanges();
        ArrayList<ItemHistory> histories = database.getChanges();


        ObservableList<ItemHistory> ObservableCategoryNames = FXCollections.observableList(histories);
        historyTableView.setItems(ObservableCategoryNames);


        idColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ItemHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ItemHistory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getIdString());
            }
        });
        itemIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ItemHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ItemHistory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getItemIdString());
            }
        });

        changeOnColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ItemHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ItemHistory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getChangeOn().toString());
            }
        });

        oldValueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ItemHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ItemHistory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getOldValue().toString());
            }
        });

        newValueColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ItemHistory, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ItemHistory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getNewValue().toString());
            }
        });

    }
}

