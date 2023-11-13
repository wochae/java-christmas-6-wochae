package christmas.service;

import static christmas.exception.ErrorMessage.AMOUNT_OUT_OF_RANGE;
import static christmas.exception.ErrorMessage.REQUEST_INVALID_MENU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
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

    @Test
    public void null_input() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseInt(null);
        });
    }

    @Test
    public void empty_input() {
        assertThrows(IllegalArgumentException.class, () -> {
            Parser.parseInt("");
        });
    }

    @Test
    public void split_menu_and_amount() {
        // given
        Map<String, Integer> expected = new HashMap<>();
        Map<String, Integer> menuAndAmountMap = new HashMap<>();
        expected.put("티본스테이크", 1);
        expected.put("바비큐립", 1);
        expected.put("초코케이크", 2);

        // when
        menuAndAmountMap = Parser.splitMenuAndAmount("티본스테이크-1,바비큐립-1,초코케이크-2");

        // then
        assertThat(menuAndAmountMap).isEqualTo(expected);
    }

    @Test
    public void invalid_amount_0() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }

    @Test
    public void invalid_amount_over() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }

    @Test
    public void over_menu_amount() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }

    @Test
    public void duplicate_menu() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-2,초코케이크-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }
}