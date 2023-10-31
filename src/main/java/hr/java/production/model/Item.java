package hr.java.production.model;

import java.math.BigDecimal;

/**
 * The type Item.
 */
public class Item extends NamedEntity {

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
     */
    public Item(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice) {
        super(name);
        this.category = category;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
    }

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
    public Item(String name, Category category, BigDecimal width, BigDecimal height, BigDecimal length, BigDecimal productionCost, BigDecimal sellingPrice, Discount discount) {
        super(name);
        this.category = category;
        this.width = width;
        this.height = height;
        this.length = length;
        this.productionCost = productionCost;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
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
            price *= (1 - discount.discountAmount / 100.0);
            return BigDecimal.valueOf(price);
        }else {
        return sellingPrice;}
    }

    /**
     * Sets selling price.
     *
     * @param sellingPrice the selling price
     */
    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
