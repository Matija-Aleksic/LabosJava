package hr.java.production.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The type Category.
 */
public class Category extends NamedEntity implements Serializable {

    private static final ArrayList<Category> categories = new ArrayList<>();
    private String description;

    /**
     * Instantiates a new Category.
     *
     * @param name the name
     */
    /**
     * Instantiates a new Category.
     *
     * @param name        the name
     * @param description the description
     */

    public Category(Long id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public static ArrayList<Category> loadCategoriesFromFile(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Long id = Long.parseLong(line.trim());
                String name = reader.readLine().trim();
                String description = reader.readLine().trim();

                categories.add(new Category(id, name, description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public static Category findCategoryByName(String categoryName) {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return category;
            }
        }
        return null;
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getDescription(), category.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDescription());
    }
}
