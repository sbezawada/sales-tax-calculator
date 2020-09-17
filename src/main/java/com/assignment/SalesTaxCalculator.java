package com.assignment;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class SalesTaxCalculator {

    public static void main(String[] args) throws Exception {
        System.out.println(calculateSalesTax());
    }

    public static String calculateSalesTax() throws Exception {
        var inputFile = "/Users/sbezawada/Downloads/input_1.txt";
        var shoppingBasket = populateShoppingBasket(getItems(readInput(inputFile)));
        return shoppingBasket.toString();
    }

    public static List<String> readInput(String inputFile) throws Exception {
        return Files.readAllLines(Path.of(inputFile), StandardCharsets.UTF_8);
    }

    public static List<Item> getItems(List<String> itemsDescriptions) {
        return itemsDescriptions.stream()
                .filter(ItemParser::matches)
                .map(ItemParser::parseItem)
                .collect(Collectors.toList());
    }

    public static ShoppingBasket populateShoppingBasket(List<Item> items) {
        var shoppingBasket = new ShoppingBasket();

        for (var item : items) {
            if (item.isImported() && !item.isExempt()) {
                item.setTaxes(SalesTaxUtil.IMPORT_SALES_TAX, SalesTaxUtil.NONEXEMPT_SALES_TAX);
            } else if (item.isImported()) {
                item.setTaxes(SalesTaxUtil.IMPORT_SALES_TAX);
            } else if (!item.isExempt()) {
                item.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX);
            }
            shoppingBasket.addToBasket(item);
        }

        return shoppingBasket;
    }
}
