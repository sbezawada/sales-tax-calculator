package com.assignment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class acts as a container for all the items in an item and
 * computes total sales tax for all items and final total for all
 * the items including the sales item applied on all these items
 */
public class ShoppingBasket {

    private List<Item> items = new ArrayList<>();

    public void addToBasket(Item item) {
        items.add(item);
    }

    /**
     * Calculate the total sales tax for all the items in the basket and round it up the nearest
     * 0.05%
     * @return total sales tax for all the items based on the imported and their exempt status
     */
    public double getTotalSalesTax() {
        double totalSalesTax = items.stream()
                .reduce(BigDecimal.ZERO, (partialResult, item) -> partialResult.add(new BigDecimal(item.getSalesTax())),
                        BigDecimal::add).doubleValue();
        return SalesTaxUtil.round(totalSalesTax, SalesTaxUtil.NEAREST_FACTOR);
    }

    /**
     * Get the total price for all the items in the input including the item's
     * applicable sales tax
     * @return total price of all the items in the input basket along with sales tax
     */
    public double getTotalPrice() {
        return items.stream()
                .reduce(BigDecimal.ZERO, (partialResult, item) -> partialResult.add(new BigDecimal(item.getTotalPrice())),
                        BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    /**
     * Prints out all the items details such as the name, final price,
     * total sales tax and the final amount
     * @return string of item details and the sum of all sales tax and
     * total amounts.
     * {@code} 1 imported bottle of perfume: 32.19
     * 1 bottle of perfume: 20.89
     * 1 packet of headache pills: 9.75
     * 1 box of imported chocolates: 11.85
     * Sales Taxes: 6.70
     * Total: 74.68
     */
    @Override
    public String toString() {
        return items.stream()
                .map(item -> item.toString() + "\n")
                .collect(Collectors.joining())
                .concat(String.format("Sales Taxes: %.2f", getTotalSalesTax()) + "\n")
                .concat(String.format("Total: %.2f", getTotalPrice()));
    }
}
