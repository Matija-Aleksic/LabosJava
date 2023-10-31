package hr.java.production.model;

/**
 * The type Category.
 */
public class Category extends NamedEntity {

    private String description;

    /**
     * Instantiates a new Category.
     *
     * @param name the name
     */
    public Category(String name) {
        super(name);
    }

    /**
     * Instantiates a new Category.
     *
     * @param name        the name
     * @param description the description
     */
    public Category(String name, String description) {
        super(name);
        this.description = description;
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
}
