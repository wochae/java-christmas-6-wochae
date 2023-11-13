package christmas.service;

import static christmas.exception.ErrorMessage.REQUEST_INVALID_MENU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
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
    public void split_menu_and_amount_1() {
        // given
        Map<MenuItem, Integer> expected = new HashMap<>();
        Map<MenuItem, Integer> menuAndAmountMap = new HashMap<>();
        MenuItem MenuItem1 = new MenuItem(MenuType.MAIN, "티본스테이크", 55_000);
        MenuItem MenuItem2 = new MenuItem(MenuType.MAIN, "바비큐립", 54_000);
        MenuItem MenuItem3 = new MenuItem(MenuType.DESSERT, "초코케이크", 15_000);
        expected.put(MenuItem1, 1);
        expected.put(MenuItem2, 1);
        expected.put(MenuItem3, 2);
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
    public void one_menu_over_menu_amount() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-21"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }

    @Test
    public void several_menu_over_menu_amount() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-19,티본스테이크-2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }

    @Test
    public void nonCertainFood() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초카이크-1,티본스테이크-1,샴페인-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }

    @Test
    public void whitespace() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-1, 티본스테이크-1"))
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

    @Test
    public void only_beverage() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("샴페인-2,제로콜라-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }
}