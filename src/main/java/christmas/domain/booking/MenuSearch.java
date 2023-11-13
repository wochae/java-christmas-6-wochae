package christmas.domain.booking;

import christmas.domain.booking.dto.MenuItem;
import java.util.List;
import java.util.Optional;

public class MenuSearch {
    public static Optional<MenuItem> findMenuItem(String userInput) {
        List<MenuItem> menuItems = Menu.MENU_ITEMS;

        for (MenuItem menuItem : menuItems) {
            if (isMenuMatch(userInput, menuItem)) {
                return Optional.of(menuItem);
            }
        }

        return Optional.empty();
    }

    private static boolean isMenuMatch(String userInput, MenuItem menuItem) {
        String menuName = menuItem.name().toLowerCase();
        return userInput.toLowerCase().contains(menuName);
    }

}
