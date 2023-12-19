package com.example.demo;

import hr.java.production.Enum.City;
import hr.java.production.database.Database;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.model.Address.Builder.findAddressById;
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
        //TODO items lista je null
        factoryItemsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Factory, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Factory, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getItems().toString());
            }
        });
    }

    public void factorySearch() {
        String factoryId = factoryIdTextField.getText().trim();
        String factoryName = factoryNameTextField.getText().trim();

        try {
            // Open database connection
            Database database = new Database();
            database.openConnection();

            // Retrieve all factories from the database
            List<Factory> factoryList = database.getAllFactories();

            // Filter factories based on search criteria
            List<Factory> filteredFactories = factoryList.stream()
                    .filter(f -> String.valueOf(f.getId()).contains(factoryId) &&
                            f.getName().contains(factoryName))
                    .collect(Collectors.toList());

            // Close database connection
            database.closeConnection();

            // Update the UI
            factoryTableView.getItems().clear();
            ObservableList<Factory> observableFactoryList = FXCollections.observableList(filteredFactories);
            factoryTableView.setItems(observableFactoryList);

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real application
        }
    }

    public void addFactories() {
        String factoryId = factoryIdTextField.getText().trim();
        String factoryName = factoryNameTextField.getText().trim();
        String factoryAddress = factoryAddressTextField.getText().trim();
        String factoryItems = factoryItemsTextField.getText();
        //TODO fix adress logic usinng id instead of hardcoding


        if (factoryId.isEmpty() || factoryName.isEmpty() || factoryAddress.isEmpty()) {
            System.out.println("Missing input");
            return;
        }

        try {
            // Open database connection
            Database database = new Database();
            database.openConnection();

            // Retrieve items from the database (assuming they are already stored in the database)
            List<Item> items = database.getAllItems();

            // Save new factory to the database
            Factory newFactory = new Factory((long) (factoryList.size()+1), factoryName, findAddressById(Long.valueOf(factoryAddress)), items.toArray(new Item[0]));
            database.saveNewFactory(newFactory);

            // Close database connection
            database.closeConnection();

            // Optionally, you can update the UI or perform additional actions here

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real application
        }

        // Clear text fields and refresh the search
        factoryIdTextField.clear();
        factoryNameTextField.clear();
        factoryAddressTextField.clear();
        factoryItemsTextField.clear();

        factorySearch();
    }

}
