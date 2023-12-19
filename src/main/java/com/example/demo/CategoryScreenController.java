package com.example.demo;

import hr.java.production.database.Database;
import hr.java.production.model.Category;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryScreenController {
    ArrayList<Category> categoryList = new ArrayList<>();
    @FXML
    private TextField categoryIdTextField;
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField categoryDescriptionTextField;
    @FXML
    private TableView<Category> categoryTableView;
    @FXML
    private TableColumn<Category, String> categoryIdTableColumn;
    @FXML
    private TableColumn<Category, String> categoryNameTableColumn;
    @FXML
    private TableColumn<Category, String> categoryDescriptionTableColumn;

    public void initialize() {


        categoryIdTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getId().toString());
            }
        });

        categoryNameTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getName());
            }
        });
        categoryDescriptionTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Category, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Category, String> param) {
                return new ReadOnlyStringWrapper(param.getValue().getDescription());
            }
        });

    }

    public ArrayList<Category> categorySearch() {
        String categoryId = categoryIdTextField.getText().trim();
        String categoryName = categoryNameTextField.getText().trim();
        String categoryDescription = categoryDescriptionTextField.getText().trim();

        try {
            // Open database connection
            Database database = new Database();
            database.openConnection();

            // Retrieve all categories from the database
            List<Category> categoryList = database.getAllCategories();

            // Filter categories based on search criteria
            List<Category> filteredCategories = categoryList.stream()
                    .filter(c -> String.valueOf(c.getId()).contains(categoryId) &&
                            c.getName().contains(categoryName) &&
                            c.getDescription().contains(categoryDescription))
                    .collect(Collectors.toList());

            // Close database connection
            database.closeConnection();

            // Update the UI
            categoryTableView.getItems().clear();
            ObservableList<Category> observableCategoryList = FXCollections.observableList(filteredCategories);
            categoryTableView.setItems(observableCategoryList);

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real application
        }
        return categoryList;
    }


    public void addCategory() {
        String categoryName = categoryNameTextField.getText().trim();
        String categoryDescription = categoryDescriptionTextField.getText().trim();

        if (categoryName.isEmpty() || categoryDescription.isEmpty()) {
            System.out.println("Some fields are missing.");
            return;
        }

        try {
            // Open database connection
            Database database = new Database();
            database.openConnection();

            // Save new category to the database
            Category newCategory = new Category((long) (categoryList.size()+1),categoryName, categoryDescription);
            database.saveNewCategory(newCategory);

            // Close database connection
            database.closeConnection();

            // Optionally, you can update the UI or perform additional actions here

        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real application
        }
    }

}