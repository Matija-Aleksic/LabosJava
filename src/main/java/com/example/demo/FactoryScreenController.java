package com.example.demo;

import hr.java.production.Enum.City;
import hr.java.production.model.Address;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
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

    private ArrayList<Factory> factoryList;

    public void initialize() {
        ArrayList<Address> addresses = loadAddressesFromFile("dat/addresses.txt");
        loadItemsFromFile("dat/items.txt");
        factoryList = loadFactoriesFromFile("dat/factories.txt");
        factorySearch();


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
        factoryList.clear();
        factoryList = loadFactoriesFromFile("dat/factories.txt");
        String factoryId = factoryIdTextField.getText();
        String factoryName = factoryNameTextField.getText();
        String factoryAddress = factoryAddressTextField.getText();
        String factoryItems = factoryItemsTextField.getText();

        List<Factory> filteredFactories = factoryList.stream().filter(c -> c.getId().toString().contains(factoryId) && c.getName().contains(factoryName)).collect(Collectors.toList());
        factoryTableView.getItems().clear();
        ObservableList<Factory> observableFactoryList = FXCollections.observableList(filteredFactories);

        factoryTableView.setItems(observableFactoryList);
    }

    public void addFactories() {
        String factoryId = factoryIdTextField.getText().trim();
        String factoryName = factoryNameTextField.getText().trim();
        String tempfactoryAddress = factoryAddressTextField.getText().trim();
        String factoryItems = factoryItemsTextField.getText();
        Address factoryAddress = new Address(tempfactoryAddress,
                "1000",
                City.ZAGREB,
                10L);

        if (factoryId.isEmpty() || factoryName.isEmpty() || tempfactoryAddress.isEmpty()) {
            System.out.println("fali unos");
            return;
        }
        Item[] items = loadItemsFromFile("dat/items.txt").toArray(new Item[0]);

        Factory newFactory = new Factory(Long.parseLong(factoryId), factoryName, factoryAddress, items);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dat/factories.txt", true))) {
            writer.write("\n" + newFactory.getId() + "\n");
            writer.write(newFactory.getName() + "\n");
            writer.write(4 + "\n");
            writer.write(factoryItems);
        } catch (IOException e) {
            e.printStackTrace();
        }


        factoryIdTextField.clear();
        factoryNameTextField.clear();
        factoryAddressTextField.clear();
        factoryItemsTextField.clear();

        factorySearch();
    }
}
