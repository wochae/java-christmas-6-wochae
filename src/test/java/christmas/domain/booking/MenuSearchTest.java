package christmas.domain.booking;

import static christmas.exception.ErrorMessage.REQUEST_INVALID_MENU;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import christmas.service.Parser;
import org.junit.jupiter.api.Test;

class MenuSearchTest {

    @Test
    void findMenuItem_isExist() {
        // given
        String userInput = "티본스테이크-1";
        // when
        boolean isExist = MenuSearch.findMenuItem(userInput).isPresent();
        // then
        assertTrue(isExist);
    }
    @Test
    void findMenuItem_isNotExist() {
        // given
        String userInput = "티스테이크-1";
        // when
        boolean isExist = MenuSearch.findMenuItem(userInput).isPresent();
        // then
        assertFalse(isExist);
    }

    @Test
    public void invalid_amount_0() {
        assertThatThrownBy(() ->
                Parser.splitMenuAndAmount("초코케이크-0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(REQUEST_INVALID_MENU.getMessage());
    }
}