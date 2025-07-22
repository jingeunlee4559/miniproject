package minip.miniproject.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String mem_nick;
    private List<CartItem> cart_items;
    private int total_price;

    public Cart(String mem_nick) {
        this.mem_nick = mem_nick;
        this.cart_items = new ArrayList<>();
        this.total_price = 0;
    }

    public void addItem(CartItem item) {
        cart_items.add(item);
        total_price += item.getSubtotal();
    }

    public void clearCart() {
        cart_items.clear();
        total_price = 0;
    }

    public int calculateTotal() {
        total_price = cart_items.stream().mapToInt(CartItem::getSubtotal).sum();
        return total_price;
    }

    public List<CartItem> getCart_items() {
        return cart_items;
    }

    public int getTotal_price() {
        return total_price;
    }

    public String getMem_nick() {
        return mem_nick;
    }

    public void printCart() {
        System.out.println("\n🛒 장바구니 목록:");
        System.out.println("────────────────────────────────────────────");
        System.out.printf(" %-4s | %-8s | %-5s | %-4s | %8s\n", "번호", "메뉴명", "온도", "수량", "금액");
        System.out.println("────────────────────────────────────────────");
        int i = 1;
        for (CartItem item : cart_items) {
            System.out.printf(" %4d | %-8s | %-5s | %4d | %,8d원\n",
                i++,
                item.getMenu_name(),
                item.getDrinkTemp(),
                item.getQuantity(),
                item.getSubtotal());
        }
        System.out.println("────────────────────────────────────────────");
        System.out.println("총합계: " + String.format("%,d원", calculateTotal()));
    }

}