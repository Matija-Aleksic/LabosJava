package com.example.demo;

import hr.java.production.database.Database;
import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.model.Category.findCategoryByName;
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

    Database database = new Database();

    public void initialize() throws SQLException, IOException {
        database.openConnection();
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


    }
    public void showFactoryScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("history.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            HelloApplication.getMainStage().setTitle("history");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public void itemSearch() throws SQLException {
        String itemId = itemIdTextField.getText();
        String itemName = itemNameTextField.getText();
        ArrayList<Item> items = new ArrayList<>();
        if (itemId.isEmpty() && itemName.isEmpty()){
             items = database.getAllItems();
        }else {
            items.add(database.getItemById(Long.parseLong(itemId)));
        }


        itemTableView.getItems().clear();
        ObservableList<Item> observableItemList = FXCollections.observableList(items);
        itemTableView.setItems(observableItemList);
    }

    public void addItemToDatabase() throws SQLException {
        String itemId = itemIdTextField.getText().trim();
        String itemName = itemNameTextField.getText().trim();
        String itemCategoryName = (String) itemCategoryComboBox.getValue();
        String itemDimension = itemDimensionsTextField.getText().trim();
        String itemSellingPrice = itemSellingPriceTextField.getText().trim();
        String itemDiscount = itemDiscountTextField.getText().trim();

        if (itemId.isEmpty() || itemName.isEmpty() || itemCategoryName == null || itemDimension.isEmpty() || itemSellingPrice.isEmpty() || itemDiscount.isEmpty()) {
            System.out.println("Please fill in all fields.");
            return;
        }

        // Retrieve the category from the database by name
        Category category = findCategoryByName(itemCategoryName);

        if (category == null) {
            System.out.println("Category not found.");
            return;
        }

        // Create a new Item object
        Item newItem = new Item(
                Long.parseLong(itemId),
                itemName,
                category,
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(3),
                BigDecimal.valueOf(Long.parseLong(itemSellingPrice)),
                new Discount(Long.valueOf(itemDiscount))
        );

        // Save the new item to the database
        database.saveNewItem(newItem);

        itemIdTextField.clear();
        itemNameTextField.clear();
        itemCategoryComboBox.getSelectionModel().clearSelection();
        itemDimensionsTextField.clear();
        itemSellingPriceTextField.clear();
        itemDiscountTextField.clear();

        itemSearch(); // Optional: Update the UI or perform additional actions after adding the item
    }

}
