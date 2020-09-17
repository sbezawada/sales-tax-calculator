package com.assignment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShoppingBasketTest {

    private static ShoppingBasket shoppingBasket;

    @BeforeAll
    static void setup() {
        shoppingBasket = new ShoppingBasket();
        Item item1 = new Item("imported bottle of perfume", 27.99);
        item1.setTaxes(SalesTaxUtil.IMPORT_SALES_TAX, SalesTaxUtil.NONEXEMPT_SALES_TAX);
        Item item2 = new Item("bottle of perfume", 18.99);
        item2.setTaxes(SalesTaxUtil.NONEXEMPT_SALES_TAX);
        Item item3 = new Item("packet of headache pills", 9.75);
        item3.setTaxes();
        Item item4 = new Item("box of imported chocolates ", 11.25);
        item4.setTaxes(SalesTaxUtil.IMPORT_SALES_TAX);

        shoppingBasket.addToBasket(item1);
        shoppingBasket.addToBasket(item2);
        shoppingBasket.addToBasket(item3);
        shoppingBasket.addToBasket(item4);
    }

    @Test
    void getTotalSalesTax() {
        assertEquals(6.70, shoppingBasket.getTotalSalesTax());
    }

    @Test
    void getTotalPrice() {
        assertEquals(74.68, shoppingBasket.getTotalPrice());
    }

    @Test
    void testToString() {
        assertEquals("1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 box of imported chocolates : 11.85\n" +
                "Sales Taxes: 6.70\n" +
                "Total: 74.68", shoppingBasket.toString());
    }
}
