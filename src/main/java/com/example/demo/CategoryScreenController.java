package com.example.demo;

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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static hr.java.production.model.Category.loadCategoriesFromFile;

public class CategoryScreenController {
    List<Category> categoryList = loadCategoriesFromFile("E:\\Projects\\javaLabos\\dat\\categories.txt");
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

    public void categorySearch() {
        categoryList.clear();
        categoryList = loadCategoriesFromFile("E:\\Projects\\javaLabos\\dat\\categories.txt");
        String categoryId = categoryIdTextField.getText();
        String categoryName = categoryNameTextField.getText();
        String categoryDescription = categoryDescriptionTextField.getText();

        List<Category> filteredCategories = categoryList.stream().filter(c -> c.getId().toString().contains(categoryId) && c.getName().contains(categoryName) && c.getDescription().contains(categoryDescription)).collect(Collectors.toList());
        categoryTableView.getItems().clear();
        ObservableList<Category> observableCategoryList = FXCollections.observableList(filteredCategories);

        categoryTableView.setItems(observableCategoryList);
    }

    public void addCategory() {
        Long categoryId = (long) (categoryList.size() + 1);
        String categoryName = categoryNameTextField.getText().trim();
        String categoryDescription = categoryDescriptionTextField.getText().trim();

        if (categoryName.isEmpty() || categoryDescription.isEmpty()) {
            System.out.println("nes fali");
            return;
        }

        Category newCategory = new Category((categoryId), categoryName, categoryDescription);
        categoryList.add(newCategory);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("E:\\Projects\\javaLabos\\dat\\categories.txt", true))) {

            writer.write(newCategory.getId() + "\n");
            writer.write(newCategory.getName() + "\n");
            writer.write(newCategory.getDescription() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}