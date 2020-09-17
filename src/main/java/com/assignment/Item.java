package com.assignment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This class holds the item details such as the name, price and
 * the tax exemptions that this item is eligible for.
 * Is created by parsing an input string that contains the item name and the price.
 */

public class Item {

    private String name;
    private double price;
    private boolean exempt;
    private boolean imported;

    private Function<BigDecimal, BigDecimal>[] taxes;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Indicates whether this item is exempt from applying a sales tax;
     * books, food and medical products are exempt for the sales tax of 10%
     *
     * @return whether this item is exempt from the sales tax
     */
    public boolean isExempt() {
        return SalesTaxUtil.isExempt(name);
    }

    /**
     * Identifies if the item is an imported item, if it is then an additional tax of
     * 5% is applied on the item price.
     *
     * @return if this item is imported
     */
    public boolean isImported() {
        return name.toLowerCase().contains("imported");
    }

    /**
     * Initialize an item with it's name and price
     *
     * @param name  of the item
     * @param price of the item
     */
    public Item(String name, double price) {
        if(name == null || name.length() == 0 || price <= 0.0) {
            throw new IllegalArgumentException("Name and/or price is missing");
        }
        this.name = name;
        this.price = price;
    }

    /**
     * List of taxes that would be applied on this item.
     */
    public void setTaxes(Function<BigDecimal, BigDecimal>... taxes) {
        this.taxes = taxes;
    }

    /**
     * Calculate the sales tax for this item based on it's exemption and imported status
     *
     * @return calculated sales tax, rounded up to the nearest 0.05
     */
    public double getSalesTax() {
        double itemSalesTax = (taxes != null) ? Stream.of(taxes)
                .reduce(BigDecimal.ZERO, (partialResult, func) -> partialResult.add(func.apply(BigDecimal.valueOf(price))),
                        BigDecimal::add).doubleValue() : 0.0;
        return SalesTaxUtil.round(itemSalesTax, SalesTaxUtil.NEAREST_FACTOR);
    }

    /**
     * This is the total price of the item - sum of the original price and the sales tax assessed.
     * This will never be lower than the item price
     *
     * @return the item price + sales tax
     */
    public double getTotalPrice() {
        return (new BigDecimal(price).add(new BigDecimal(getSalesTax())))
                .setScale(2, RoundingMode.HALF_EVEN)
                .doubleValue();
    }

    /**
     * Print the item details in the following format:
     * {quantity} {item_name} : {item_price}
     *
     * @return a string in the above format
     */
    @Override
    public String toString() {
        return String.format("1 %s: %.2f", name, getTotalPrice());
    }

    /**
     * Object comparison for equality based on the item name and the price
     *
     * @param o object to compare for equality
     * @return if the objects being compared to are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.getPrice(), getPrice()) == 0 &&
                com.google.common.base.Objects.equal(getName(), item.getName());
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(getName(), getPrice());
    }
}
