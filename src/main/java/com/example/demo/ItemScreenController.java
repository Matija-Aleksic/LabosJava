package com.example.demo;

import hr.java.production.model.Category;
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


        filteredItems = itemList.stream().filter(c -> c.getId().toString().contains(itemId) && c.getName().contains(itemName)).collect(Collectors.toList());

        ObservableList<Item> observableCategoryList = FXCollections.observableList(filteredItems);

        itemTableView.setItems(observableCategoryList);
    }
}