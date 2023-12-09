package com.example.demo;


import hr.java.production.model.Store;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.model.Store.loadStoresFromFile;

public class StoreScreenController {

    @FXML
    private TextField storeIdTextField;
    @FXML
    private TextField storeNameTextField;
    @FXML
    private TextField storeWebAddressTextField;
    @FXML
    private TextField storeItemsTextField;

    @FXML
    private TableView<Store> storeTableView;

    @FXML
    private TableColumn<Store, String> storeIdTableColumn;
    @FXML
    private TableColumn<Store, String> storeNameTableColumn;
    @FXML
    private TableColumn<Store, String> storeWebAddressTableColumn;
    @FXML
    private TableColumn<Store, String> storeItemsTableColumn;

    public void initialize() {
        storeIdTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Store, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Store, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getId().toString());
            }
        });
        storeNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Store, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Store, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getName());
            }
        });
        storeWebAddressTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Store, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Store, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getWebAddress());
            }
        });
        storeItemsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Store, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Store, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getItems().toString());
            }
        });
    }

    public void storeSearch() {
        List<Store> storeList = loadStoresFromFile("dat/stores.txt");

        String storeId = storeIdTextField.getText();
        String storeName = storeNameTextField.getText();
        String storeAddress = storeWebAddressTextField.getText();
        String storeItems = storeItemsTextField.getText();

        List<Store> filteredStores = storeList.stream().filter(c -> c.getId().toString().contains(storeId) && c.getName().contains(storeName)).collect(Collectors.toList());

        ObservableList<Store> observableFactoryList = FXCollections.observableList(filteredStores);

        storeTableView.setItems(observableFactoryList);
    }

}
