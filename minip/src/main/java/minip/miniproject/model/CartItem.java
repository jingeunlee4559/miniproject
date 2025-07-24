package minip.miniproject.model;

import lombok.Data;

@Data
public class CartItem {
    private MenuCategory menu_category;
    private String menu_name;
    private int quantity;
    private DrinkTemperature drinkTemp;
    private int item_price;

    public CartItem(MenuCategory menu_category, String menu_name, int quantity, DrinkTemperature drinkTemp, int item_price) {
        this.menu_category = menu_category;
        this.menu_name = menu_name;
        this.quantity = quantity;
        this.drinkTemp = drinkTemp;
        this.item_price = item_price;
    }

    public int getSubtotal() {

        return item_price * quantity;
    }

    
}
