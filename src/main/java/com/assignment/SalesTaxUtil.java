package com.assignment;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import static java.lang.StrictMath.*;

public class SalesTaxUtil {

    public static final int NEAREST_FACTOR = (int) (1 / 0.05);

    public static final double NONEXEMPT_SALES_TAX_RATE = 0.1;
    public static final double IMPORT_SALES_TAX_RATE = 0.05;

    // Sales tax for non-exempt items
    public static final Function<BigDecimal, BigDecimal> NONEXEMPT_SALES_TAX =
            p -> p.multiply(new BigDecimal(NONEXEMPT_SALES_TAX_RATE));

    // Sales tax for imported items
    public static final Function<BigDecimal, BigDecimal> IMPORT_SALES_TAX =
            p -> p.multiply(new BigDecimal(IMPORT_SALES_TAX_RATE));
    ;

    /**
     * Items that are exempt from applying sales tax, but if they are imported
     * then appropriate import tax will assessed
     */
    private static Set<String> exemptItems = null;

    // Initialize/identify items that are exempt from sales tax
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

    /**
     * Indicates whether this item is exempt from assessing a sales tax
     *
     * @see #exemptItems
     * @param name of the item
     * @return whether it is exempt from sales tax
     */
    public static boolean isExempt(String name) {
        return exemptItems.contains(name);
    }

    /**
     * Rounds up a decimal value to the nearest factor
     * <code>
     *     4.1985->4.20
     *     0.5625->0.60
     * </code>
     * @param number price decimal
     * @param nearestFactor factor for rounding up the price decimal
     * @return rounded price decimal with scale of 2
     */
    public static double round(double number, double nearestFactor) {
        return new BigDecimal(ceil(number * nearestFactor) / nearestFactor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
