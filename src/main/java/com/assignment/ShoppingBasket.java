package com.assignment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingBasket {

    private List<Item> items = new ArrayList<>();

    public void addToBasket(Item item) {
        items.add(item);
    }

    public double getTotalSalesTax() {
        double totalSalesTax = items.stream()
                .reduce(BigDecimal.ZERO, (partialResult, item) -> partialResult.add(new BigDecimal(item.getSalesTax())),
                        BigDecimal::add).doubleValue();
        return SalesTaxUtil.round(totalSalesTax, SalesTaxUtil.NEAREST_FACTOR);
    }

    public double getTotalPrice() {
        return items.stream()
                .reduce(BigDecimal.ZERO, (partialResult, item) -> partialResult.add(new BigDecimal(item.getTotalPrice())),
                        BigDecimal::add).doubleValue();
    }

    @Override
    public String toString() {
        return items.stream()
                .map(item -> item.toString() + "\n")
                .collect(Collectors.joining())
                .concat(String.format("Sales Taxes: %.2f", getTotalSalesTax()) + "\n")
                .concat(String.format("Total: %.2f", getTotalPrice()));
    }
}
