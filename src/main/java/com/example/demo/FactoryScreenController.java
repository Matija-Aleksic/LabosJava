package com.example.demo;

import hr.java.production.model.Factory;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.model.Address.Builder.loadAddressesFromFile;
import static hr.java.production.model.Factory.loadFactoriesFromFile;
import static hr.java.production.model.Item.loadItemsFromFile;

public class FactoryScreenController {

    @FXML
    private TextField factoryIdTextField;
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private TextField factoryAddressTextField;
    @FXML
    private TextField factoryItemsTextField;

    @FXML
    private TableView<Factory> factoryTableView;

    @FXML
    private TableColumn<Factory, String> factoryIdTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryNameTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryAddressTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryItemsTableColumn;


    public void initialize() {
        loadItemsFromFile("dat/items.txt");
        loadAddressesFromFile("dat/addresses.txt");
        factoryIdTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getId().toString());
            }
        });
        factoryNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getName());
            }
        });
        factoryAddressTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getAddress().toString());
            }
        });
        factoryItemsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory, String> param) {
                return new ReadOnlyStringWrapper(Arrays.toString(param.getValue().getItems()));
            }
        });
    }

    public void factorySearch() {
        List<Factory> factoryList = loadFactoriesFromFile("dat/factories.txt");

        String factoryId = factoryIdTextField.getText();
        String factoryName = factoryNameTextField.getText();
        String factoryAddress = factoryAddressTextField.getText();
        String factoryItems = factoryItemsTextField.getText();

        List<Factory> filteredFactories = factoryList.stream().filter(c -> c.getId().toString().contains(factoryId) && c.getName().contains(factoryName)).collect(Collectors.toList());

        ObservableList<Factory> observableFactoryList = FXCollections.observableList(filteredFactories);

        factoryTableView.setItems(observableFactoryList);
    }

}
