package christmas.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    public void out_of_range() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseInt("0");
        });
    }

    @Test
    public void not_integer() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseInt("a");
        });
    }

}