package hr.java.production.model;

/**
 * The type Named entity.
 */
public abstract class NamedEntity {
    private String name;

    /**
     * Instantiates a new Named entity.
     *
     * @param name the name
     */
    public NamedEntity(String name) {
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}
