package com.example.demo;

import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.model.Category.loadCategoriesFromFile;
import static hr.java.production.model.Item.loadItemsFromFile;

public class ItemScreenController {

    @FXML
    private TextField itemIdTextField;
    @FXML
    private TextField itemNameTextField;
    @FXML
    private ComboBox itemCategoryComboBox;
    @FXML
    private TextField itemDimensionsTextField;
    @FXML
    private TextField itemSellingPriceTextField;
    @FXML
    private TextField itemDiscountTextField;
    @FXML
    private TextField itemWeightOrWarrantyTextField;


    @FXML
    private TableView<Item> itemTableView;

    @FXML
    private TableColumn<Item, String> itemIdTableColumn;
    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryTableColumn;
    @FXML
    private TableColumn<Item, String> itemDimensionsTableColumn;
    @FXML
    private TableColumn<Item, String> itemSellingPriceTableColumn;
    @FXML
    private TableColumn<Item, String> itemDiscountTableColumn;

    public void initialize() {
        ArrayList<Category> categoryList = loadCategoriesFromFile("dat/categories.txt");
        ArrayList<Item> items = loadItemsFromFile("dat/items.txt");
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categoryList) {
            categoryNames.add(category.getName());
        }
        ObservableList<String> ObservableCategoryNames = FXCollections.observableList(categoryNames);
        itemCategoryComboBox.setItems(ObservableCategoryNames);


        itemIdTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getId().toString());
            }
        });

        itemNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getName());
            }
        });
        itemCategoryTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getCategory().getName());
            }
        });
        itemDimensionsTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getVolume().toString());
            }
        });
        itemSellingPriceTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getSellingPrice().toString());
            }
        });
        itemDiscountTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getDiscount().getDiscountAmountString());
            }
        });

    }

    public void itemSearch() {
        List<Item> itemList = loadItemsFromFile("dat/items.txt");

        String itemId = itemIdTextField.getText();
        String itemName = itemNameTextField.getText();
        String itemCategory = (String) itemCategoryComboBox.getValue();
        String itemDimension = itemDimensionsTextField.getText();
        String itemSellingPrice = itemSellingPriceTextField.getText();
        String itemDiscount = itemDiscountTextField.getText();
        List<Item> filteredItems = new ArrayList<>();


        filteredItems = itemList.stream()
                .filter(c -> c.getId().toString().contains(itemId) && c.getName()
                        .contains(itemName)).collect(Collectors.toList());
        itemTableView.getItems().clear();

        ObservableList<Item> observableCategoryList = FXCollections.observableList(filteredItems);

        itemTableView.setItems(observableCategoryList);
    }

    public void addItemToFile() {
        String itemId = itemIdTextField.getText().trim();
        String itemName = itemNameTextField.getText().trim();
        String itemCategory = (String) itemCategoryComboBox.getValue();
        String itemDimension = itemDimensionsTextField.getText().trim();
        String itemSellingPrice = itemSellingPriceTextField.getText().trim();
        String itemDiscount = itemDiscountTextField.getText().trim();

        if (itemId.isEmpty() || itemName.isEmpty() || itemCategory == null || itemDimension.isEmpty() ||
                itemSellingPrice.isEmpty() || itemDiscount.isEmpty()) {
            // Handle the case where some fields are empty
            System.out.println("Please fill in all fields.");
            return;
        }

        // Create a new Item object
        Item newItem = new Item(
                Long.parseLong(itemId),
                itemName,
                Category.findCategoryByName(itemCategory),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(Long.parseLong(itemSellingPrice)),
                new Discount(Long.valueOf(itemDiscount))
        );

        // Append the new item to the "dat/items.txt" file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dat/items.txt", true))) {
            writer.write(newItem.getId() + "\n");
            writer.write(newItem.getName() + "\n");
            writer.write(newItem.getCategory().getName() + "\n");
            writer.write(newItem.getVolume() + "\n");
            writer.write(newItem.getSellingPrice() + "\n");
            writer.write(newItem.getDiscount().getDiscountAmountString() + "\n");
            writer.newLine(); // Add an empty line to separate items
        } catch (IOException e) {
            // Handle the exception (e.g., display an error message)
            e.printStackTrace();
        }

        // Optionally, clear the input fields after adding a new item
        itemIdTextField.clear();
        itemNameTextField.clear();
        itemCategoryComboBox.getSelectionModel().clearSelection();
        itemDimensionsTextField.clear();
        itemSellingPriceTextField.clear();
        itemDiscountTextField.clear();

        // Optionally, refresh the table view with the updated data
        itemSearch();
    }
}
