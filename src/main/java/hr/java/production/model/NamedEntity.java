package hr.java.production.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedEntity that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
