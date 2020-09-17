package com.assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemParserTest {

    @Test
    void parseItem() {
        assertEquals(true,
                ItemParser.parseItem("1 music CD at 14.99")
                        .equals(new Item("music CD", 14.99)));
        assertEquals(true,
                ItemParser.parseItem("1 imported bottle of perfume at 47.50")
                        .equals(new Item("imported bottle of perfume", 47.50)));
    }

    @Test
    void matches() {
        assertEquals(true, ItemParser.matches("1 music CD at 14.99"));
        assertEquals(false, ItemParser.matches("1 music CD"));
    }

    @Test
    void count() {
        assertEquals(1, ItemParser.count("1 music CD at 14.99"));
    }
}
