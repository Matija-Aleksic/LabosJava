package hr.java.production.model;

public class CategoryBuilder {
    private String name;
    private String description;

    public CategoryBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Category createCategory() {
        return new Builder().setName(name).createCategory();
    }
}