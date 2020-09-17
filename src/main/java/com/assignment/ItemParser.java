package com.assignment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class parses the input string containing item details, extracts relevant fields
 * such as the name, price from raw string
 */
public class ItemParser {

    // Format of the input tem string: {quantity} {item_name} at {price}
    private static final String ITEM_INPUT_REGEX = "(\\d+)\\s((\\w+\\s)+)at\\s(\\d+.\\d+)";

    public static Item parseItem(String itemDescription) {
        Matcher matcher = parse(itemDescription);
        String name = matcher.group(2).trim();
        double itemPrice = Double.valueOf(matcher.group(4));
        return new Item(name, itemPrice);
    }

    public static int count(String order) {
        return Integer.valueOf(parse(order).group(1));
    }

    public static Matcher parse(String description) {
        Pattern pattern = Pattern.compile(ITEM_INPUT_REGEX);
        Matcher matcher = pattern.matcher(description);
        matcher.find();
        return matcher;
    }

    public static boolean matches(String itemDescription) {
        return Pattern.matches(ITEM_INPUT_REGEX, itemDescription);
    }
}
