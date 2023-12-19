package hr.java.production.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;

import static hr.java.production.model.Category.findCategoryByName;

/**
 * The type Item.
 */
public class Item extends NamedEntity implements Serializable {

    private static final ArrayList<Item> items = new ArrayList<>();
    private Category category;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal length;
    private BigDecimal productionCost;
    private BigDecimal sellingPrice;
    private Discount discount;


    /**
     * Instantiates a new Item.
     *
     * @param name           the name
     * @param category       the category
     * @param width          the width
     * @param height         the height
     * @param length         the length
     * @param productionCost the production cost
     * @param sellingPrice   the selling price
     * @param discount       the discount
     */
    public Item(Long id, String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Discount discount) {
        super(id, name);
        this.category = category;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
    }

    public static ArrayList<Item> loadItemsFromFile(String fileName) {

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Long id = Long.parseLong(line.trim());
                String itemName = reader.readLine().trim();
                String categoryName = reader.readLine().trim();

                Category category = findCategoryByName(categoryName);

                BigDecimal width = new BigDecimal(reader.readLine().trim());
                BigDecimal height = new BigDecimal(reader.readLine().trim());
                BigDecimal length = new BigDecimal(reader.readLine().trim());
                BigDecimal productionCost = new BigDecimal(reader.readLine().trim());
                BigDecimal sellingPrice = new BigDecimal(reader.readLine().trim());

                String discountValue = reader.readLine().trim();
                Discount discount = new Discount(Double.parseDouble(discountValue));

                items.add(new Item(id, itemName, category, width, height, length, productionCost, sellingPrice, discount));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return items;
    }

    public static Item findItemById(Long itemId) {
        return items.get(Math.toIntExact(itemId - 1));
    }

    /**
     * Gets discount.
     *
     * @return the discount
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     * Sets discount.
     *
     * @param discount the discount
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * Gets volume.
     *
     * @return the volume
     */
    public BigDecimal getVolume() {
        return (width.multiply(height.multiply(length)));
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public BigDecimal getWidth() {
        return width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public BigDecimal getLength() {
        return length;
    }

    /**
     * Sets length.
     *
     * @param length the length
     */
    public void setLength(BigDecimal length) {
        this.length = length;
    }

    /**
     * Gets production cost.
     *
     * @return the production cost
     */
    public BigDecimal getProductionCost() {
        return productionCost;
    }

    /**
     * Sets production cost.
     *
     * @param productionCost the production cost
     */
    public void setProductionCost(BigDecimal productionCost) {
        this.productionCost = productionCost;
    }

    /**
     * Gets selling price.
     *
     * @return the selling price
     */
    public BigDecimal getSellingPrice() {
        double price = sellingPrice.doubleValue();
        if (discount != null) {
            // Preračunaj cijenu s obzirom na popust
            price *= (1 - (discount.getDiscountAmount()) / 100.0);
            return BigDecimal.valueOf(price);
        } else {
            return sellingPrice;
        }
    }

    /**
     * Sets selling price.
     *
     * @param sellingPrice the selling price
     */
    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * Gets selling pricein double.
     *
     * @return the selling pricein double
     */
    public double getSellingPriceinDouble() {
        double price = sellingPrice.doubleValue();
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getCategory(), item.getCategory()) && Objects.equals(getWidth(), item.getWidth()) && Objects.equals(getHeight(), item.getHeight()) && Objects.equals(getLength(), item.getLength()) && Objects.equals(getProductionCost(), item.getProductionCost()) && Objects.equals(getSellingPrice(), item.getSellingPrice()) && Objects.equals(getDiscount(), item.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCategory(), getWidth(), getHeight(), getLength(), getProductionCost(), getSellingPrice(), getDiscount());
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + getName() + '\'' +
                ", category=" + getCategory() +
                ", width=" + getWidth() +
                ", height=" + getHeight() +
                ", length=" + getLength() +
                ", productionCost=" + getProductionCost() +
                ", sellingPrice=" + getSellingPrice() +
                ", discount=" + getDiscount() +
                '}';
    }
}
