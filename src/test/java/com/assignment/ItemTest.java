package com.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    void missingData() {
        assertThrows(IllegalArgumentException.class, () -> new Item(null, 2.0));
        assertThrows(IllegalArgumentException.class, () -> new Item("", 2.0));
        assertThrows(IllegalArgumentException.class, () -> new Item("chocolate", 0.0));
        assertThrows(IllegalArgumentException.class, () -> new Item(null, -1.0));
    }

    @Test
    void itemInit() {
        var itemInit = new Item("chocolate", 10.0);
        assertEquals("chocolate", itemInit.getName());
        assertEquals(10.0, itemInit.getPrice());
    }

    @Test
    void isExemptAndImported() {
        var itemExempt = new Item("chocolate", 10.0);
        assertEquals(true, itemExempt.isExempt());
        assertEquals(false, itemExempt.isImported());

        itemExempt = new Item("imported box of chocolates", 10.0);
        assertEquals(true, itemExempt.isExempt());
        assertEquals(true, itemExempt.isImported());

        itemExempt = new Item("music CD", 10.0);
        assertEquals(false, itemExempt.isExempt());
        assertEquals(false, itemExempt.isImported());

        itemExempt = new Item("bottle of perfume", 10.0);
        assertEquals(false, itemExempt.isExempt());
        assertEquals(false, itemExempt.isImported());

        itemExempt = new Item("imported bottle of perfume", 10.0);
        assertEquals(false, itemExempt.isExempt());
        assertEquals(true, itemExempt.isImported());
    }

    @Test
    void getSalesTax() {
        var item = new Item("music CD ", 14.99);
        assertEquals(0.0, item.getSalesTax());
        item.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX);
        assertEquals(1.5, item.getSalesTax());

        item = new Item("book", 12.49);
        item.setTaxes();
        assertEquals(0.0, item.getSalesTax());

        item = new Item("imported bottle of perfume", 27.99);
        item.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX, SalesTaxUtil.IMPORT_SALES_TAX);
        assertEquals(4.2, item.getSalesTax());

        item = new Item("imported box of chocolates", 10.50);
        item.setTaxes(SalesTaxUtil.IMPORT_SALES_TAX);
        assertEquals(0.55, item.getSalesTax());
    }

    @Test
    void getTotalPrice() {
        var item = new Item("music CD ", 14.99);
        assertEquals(14.99, item.getTotalPrice()); // Without applying any sales tax
        item.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX);
        assertEquals(16.49, item.getTotalPrice());

        item = new Item("book", 12.49);
        item.setTaxes();
        assertEquals(12.49, item.getTotalPrice());

        item = new Item("imported bottle of perfume", 27.99);
        item.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX, SalesTaxUtil.IMPORT_SALES_TAX);
        assertEquals(32.19, item.getTotalPrice());

        item = new Item("imported box of chocolates", 10.00);
        item.setTaxes(SalesTaxUtil.IMPORT_SALES_TAX);
        assertEquals(10.50, item.getTotalPrice());
    }

    @Test
    void testToString() {
        var item = new Item("music CD", 14.99);
        item.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX);
        assertEquals("1 music CD: 16.49", item.toString());

        item = new Item("book", 12.49);
        item.setTaxes();
        assertEquals("1 book: 12.49", item.toString());

        item = new Item("imported bottle of perfume", 27.99);
        item.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX, SalesTaxUtil.IMPORT_SALES_TAX);
        assertEquals("1 imported bottle of perfume: 32.19", item.toString());

        item = new Item("imported box of chocolates", 10.00);
        item.setTaxes(SalesTaxUtil.IMPORT_SALES_TAX);
        assertEquals("1 imported box of chocolates: 10.50", item.toString());
    }

}
