package com.example.demo;


import hr.java.production.model.Item;
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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.model.Store.loadStoresFromFile;
import static hr.java.production.model.Item.loadItemsFromFile;

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
        ArrayList<Item> items = loadItemsFromFile("dat/items.txt");
        List<Store> storeList = loadStoresFromFile("dat/stores.txt");

        String storeId = storeIdTextField.getText();
        String storeName = storeNameTextField.getText();
        String storeAddress = storeWebAddressTextField.getText();
        String storeItems = storeItemsTextField.getText();


        List<Store> filteredStores = storeList.stream().filter(c -> c.getId().toString().contains(storeId) && c.getName().contains(storeName)).collect(Collectors.toList());
        storeTableView.getItems().clear();
        ObservableList<Store> observableFactoryList = FXCollections.observableList(filteredStores);

        storeTableView.setItems(observableFactoryList);
    }


    public void addStoreToFile() {
        String storeId = storeIdTextField.getText().trim();
        String storeName = storeNameTextField.getText().trim();
        String storeWebAddress = storeWebAddressTextField.getText().trim();
        String storeItems = storeItemsTextField.getText().trim();

        if (storeId.isEmpty() || storeName.isEmpty() || storeWebAddress.isEmpty() || storeItems.isEmpty()) {
            System.out.println("Incomplete input");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dat/stores.txt", true))) {
            writer.write("\n" + storeId + "\n");
            writer.write(storeName + "\n");
            writer.write(storeWebAddress + "\n");
            writer.write(storeItems);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Optionally, you can clear the text fields after adding a new store
        storeIdTextField.clear();
        storeNameTextField.clear();
        storeWebAddressTextField.clear();
        storeItemsTextField.clear();

        // Optionally, refresh the table view with the updated data
        storeSearch();
    }

}
