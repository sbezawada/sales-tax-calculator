package com.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalesTaxUtilTest {

    @Test
    void roundTax() {
        assertEquals(4.2, SalesTaxUtil.round(4.1985, SalesTaxUtil.NEAREST_FACTOR));
        assertEquals(0.6, SalesTaxUtil.round(0.5625, SalesTaxUtil.NEAREST_FACTOR));
    }
}
