package christmas.domain.booking;

import christmas.domain.booking.dto.MenuItem;
import christmas.domain.booking.dto.MenuType;
import java.util.Arrays;
import java.util.List;


public class Menu {
    // APPETIZER
    public static final MenuItem APPETIZER_1 = new MenuItem(MenuType.APPETIZER, "양송이수프", 6_000);
    public static final MenuItem APPETIZER_2 = new MenuItem(MenuType.APPETIZER, "타파스", 5_500);
    public static final MenuItem APPETIZER_3 = new MenuItem(MenuType.APPETIZER, "시저샐러드", 8_000);

    // MAIN
    public static final MenuItem MAIN_1 = new MenuItem(MenuType.MAIN, "티본스테이크", 55_000);
    public static final MenuItem MAIN_2 = new MenuItem(MenuType.MAIN, "바비큐립", 54_000);
    public static final MenuItem MAIN_3 = new MenuItem(MenuType.MAIN, "해산물파스타", 35_000);
    public static final MenuItem MAIN_4 = new MenuItem(MenuType.MAIN, "크리스마스파스타", 25_000);

    // DESSERT
    public static final MenuItem DESSERT_1 = new MenuItem(MenuType.DESSERT, "초코케이크", 15_000);
    public static final MenuItem DESSERT_2 = new MenuItem(MenuType.DESSERT, "아이스크림", 5_000);

    // BEVERAGE
    public static final MenuItem BEVERAGE_1 = new MenuItem(MenuType.BEVERAGE, "제로콜라", 3_000);
    public static final MenuItem BEVERAGE_2 = new MenuItem(MenuType.BEVERAGE, "레드와인", 60_000);
    public static final MenuItem BEVERAGE_3 = new MenuItem(MenuType.BEVERAGE, "샴페인", 25_000);

    public static final List<MenuItem> MENU_ITEMS = Arrays.asList(
            APPETIZER_1, APPETIZER_2, APPETIZER_3,
            MAIN_1, MAIN_2, MAIN_3, MAIN_4,
            DESSERT_1, DESSERT_2,
            BEVERAGE_1, BEVERAGE_2, BEVERAGE_3
    );
    private MenuType menuType;
    private int quantity;

    public Menu(MenuType menuType, int quantity) {
        this.menuType = menuType;
        this.quantity = quantity;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void display() {
        System.out.println(menuType + " " + quantity + "개");
    }
}
