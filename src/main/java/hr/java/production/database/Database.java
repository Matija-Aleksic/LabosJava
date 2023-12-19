package hr.java.production.database;

import hr.java.production.model.*;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static hr.java.production.model.Address.Builder.findAddressById;

public class Database {
    private static final String DATABASE_FILE = "dat/database.properties";
    private Connection connection;

    private static Properties loadDatabaseProperties() throws SQLException, IOException {
        Properties svojstva = new Properties();
        svojstva.load(new FileReader(DATABASE_FILE));
        String urlBazePodataka = svojstva.getProperty("db.url");
        String korisnickoIme = svojstva.getProperty("db.user");
        String lozinka = svojstva.getProperty("db.password");
        return svojstva;
    }

    public void openConnection() throws SQLException, IOException {
        Properties properties = loadDatabaseProperties();

        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        connection = DriverManager.getConnection(url, user, password);
    }

    private Factory getFactoryFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        long addressId = resultSet.getLong("ADDRESS_ID");

        // You may need to fetch the address details and construct the Address object
        Address address = findAddressById(addressId);

        return new Factory(id, name, address);
    }

    public List<Store> getAllStores() throws SQLException {
        List<Store> storeList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE");

            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                String webAddress = resultSet.getString("WEB_ADDRESS");

                Store store = new Store(id, name, webAddress);
                storeList.add(store);
            }
        }

        return storeList;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORY");
            while (resultSet.next()) {
                Category category = getCategoryFromResultSet(resultSet);
                categoryList.add(category);
            }
        }
        return categoryList;
    }

    public ArrayList<Item> getAllItems() throws SQLException {
        ArrayList<Item> itemList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ITEM");
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                itemList.add(item);
            }
        }
        return itemList;
    }

    public ArrayList<Item> getAllStoreItems() throws SQLException {
        ArrayList<Item> itemList = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM STORE_ITEM ");
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                itemList.add(item);
            }
        }
        return itemList;
    }

    public void saveNewCategory(Category category) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO CATEGORY (NAME, DESCRIPTION) VALUES (?, ?)")) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();
        }
    }


    public void addItemToFactory(long factoryId, long itemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO FACTORY_ITEM (FACTORY_ID, ITEM_ID) VALUES (?, ?)")) {
            statement.setLong(1, factoryId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
        }
    }

    public void addItemToStore(long storeId, long itemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO STORE_ITEM (STORE_ID, ITEM_ID) VALUES (?, ?)")) {
            statement.setLong(1, storeId);
            statement.setLong(2, itemId);
            statement.executeUpdate();
        }
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("ID");
        String name = resultSet.getString("NAME");
        String description = resultSet.getString("DESCRIPTION");
        return new Category(id, name, description);
    }

    private Item getItemFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("ID");
        long categoryId = resultSet.getLong("CATEGORY_ID");
        Category category = Category.findCategoryById(categoryId);
        String name = resultSet.getString("NAME");
        BigDecimal width = resultSet.getBigDecimal("WIDTH");
        BigDecimal height = resultSet.getBigDecimal("HEIGHT");
        BigDecimal length = resultSet.getBigDecimal("LENGTH");
        BigDecimal productionCost = resultSet.getBigDecimal("PRODUCTION_COST");
        BigDecimal sellingPrice = resultSet.getBigDecimal("SELLING_PRICE");
        return new Item(id, name, category, width, height, length, productionCost, sellingPrice);
    }

    public List<Item> getItemsForFactory(long factoryId) throws SQLException {
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT I.* FROM FACTORY_ITEM FI, ITEM I " + "WHERE FI.FACTORY_ID = ? AND FI.ITEM_ID = I.ID")) {
            statement.setLong(1, factoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                itemList.add(item);
            }
        }
        return itemList;
    }


    public List<Item> getItemsForStore(long storeId) throws SQLException {
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT I.* FROM STORE_ITEM SI, ITEM I " + "WHERE SI.STORE_ID = ? AND SI.ITEM_ID = I.ID")) {
            statement.setLong(1, storeId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = getItemFromResultSet(resultSet);
                itemList.add(item);
            }
        }
        return itemList;
    }

    public Item getItemById(long itemId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM ITEM WHERE ID = ?")) {
            statement.setLong(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getItemFromResultSet(resultSet);
            }
            return null;
        }
    }

    public void saveNewItem(Item item) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO ITEM (CATEGORY_ID, NAME, WIDTH, HEIGHT, LENGTH, " + "PRODUCTION_COST, SELLING_PRICE) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            statement.setLong(1, item.getCategoryId());
            statement.setString(2, item.getName());
            statement.setBigDecimal(3, item.getWidth());
            statement.setBigDecimal(4, item.getHeight());
            statement.setBigDecimal(5, item.getLength());
            statement.setBigDecimal(6, item.getProductionCost());
            statement.setBigDecimal(7, item.getSellingPrice());
            statement.executeUpdate();
        }
    }

    public void saveNewAddress(Address address) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO ADDRESS (STREET, HOUSE_NUMBER, CITY, POSTAL_CODE) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, address.getStreet());
            statement.setString(2, address.getHouseNumber());
            statement.setString(3, address.getCity().toString());
            statement.setInt(4, address.getPostalCode());
            statement.executeUpdate();
        }
    }

    public void saveNewFactory(Factory factory) throws SQLException {
        // Check if the address already exists in the ADDRESS table
        if (addressExists(factory.getAddress())) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO FACTORY (NAME, ADDRESS_ID) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

                // Set the NAME as the first parameter
                statement.setString(1, factory.getName());

                // Set the ADDRESS_ID as the second parameter
                statement.setLong(2, factory.getAddress().getId());

                statement.executeUpdate();

                // Retrieve the generated ID
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long generatedId = generatedKeys.getLong(1);
                    factory.setId(generatedId); // Set the generated ID in the Factory object
                }
            }

            // Insert items into FACTORY_ITEM table
            for (Item item : factory.getItems()) {
                try (PreparedStatement itemStatement = connection.prepareStatement(
                        "INSERT INTO FACTORY_ITEM (FACTORY_ID, ITEM_ID) VALUES (?, ?)")) {

                    // Set the FACTORY_ID as the first parameter
                    itemStatement.setLong(1, factory.getId());

                    // Set the ITEM_ID as the second parameter
                    itemStatement.setLong(2, item.getId());

                    itemStatement.executeUpdate();
                }
            }
        } else {
            System.out.println("Error: Address not found.");
        }
    }

    private boolean addressExists(Address address) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT ID FROM ADDRESS WHERE ID = ?")) {

            statement.setLong(1, address.getId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }


    public void saveNewStore(Store store) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO STORE (NAME, WEB_ADDRESS) VALUES (?, ?)")) {
            statement.setString(1, store.getName());
            statement.setString(2, store.getWebAddress());
            statement.executeUpdate();
        }
    }

    public List<Factory> getAllFactories() throws SQLException {
        List<Factory> factoryList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM FACTORY");
            while (resultSet.next()) {
                Factory factory = getFactoryFromResultSet(resultSet);
                factoryList.add(factory);
            }
        }

        return factoryList;
    }

}
