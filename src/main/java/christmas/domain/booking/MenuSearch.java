package christmas.domain.booking;

import christmas.domain.booking.dto.MenuItem;
import java.util.List;
import java.util.Optional;

public class MenuSearch {
    public static Optional<MenuItem> findMenuItem(String userInput) {
        List<MenuItem> menuItems = Menu.MENU_ITEMS;
        Optional<MenuItem> foundMenuItem = menuItems.stream()
                .filter(menuItem -> isMenuMatch(userInput, menuItem))
                .findFirst();
        return foundMenuItem;
    }

    private static boolean isMenuMatch(String userInput, MenuItem menuItem) {
        String menuName = menuItem.name();
        return userInput.contains(menuName);
    }

}
