package com.assignment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class SalesTaxUtil {

    public static final int NEAREST_FACTOR = (int) (1 / 0.05);

    public static final double NONEXEMPT_SALES_TAX_RATE = 0.1;
    public static final double IMPORT_SALES_TAX_RATE = 0.05;

    public static final Function<BigDecimal, BigDecimal> NONEXEMPT_SALES_TAX =
            p -> p.multiply(new BigDecimal(NONEXEMPT_SALES_TAX_RATE));
    public static final Function<BigDecimal, BigDecimal> IMPORT_SALES_TAX =
            p -> p.multiply(new BigDecimal(IMPORT_SALES_TAX_RATE));
    ;

    private static Set<String> exemptItems = null;

    static {
        exemptItems = new HashSet<String>();
        exemptItems.add("book");
        exemptItems.add("headache pills");
        exemptItems.add("packet of headache pills");
        exemptItems.add("box of imported chocolates");
        exemptItems.add("imported box of chocolates");
        exemptItems.add("box of chocolates");
        exemptItems.add("chocolate");
        exemptItems.add("chocolate bar");
        exemptItems.add("pills");
    }

    public static boolean isExempt(String name) {
        return exemptItems.contains(name);
    }

    public static double round(double number, double nearestFactor) {
        return new BigDecimal(Math.ceil(number * nearestFactor) / nearestFactor)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
