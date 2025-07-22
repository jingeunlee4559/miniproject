package minip.miniproject.service;

import java.util.List;

import org.springframework.stereotype.Service;
import minip.miniproject.model.Cart;
import minip.miniproject.model.CartItem;

@Service
public class CartService {

    public void addItemToCart(Cart cart, CartItem item) {
        cart.addItem(item);
    }

    public void clearCart(Cart cart) {
        cart.clearCart();
    }

    public void removeItem(Cart cart, int index) {
        if (index >= 0 && index < cart.getCart_items().size()) {
            cart.getCart_items().remove(index);
            cart.calculateTotal();
        }
    }

    public void updateQuantity(Cart cart, int index, int newQuantity) {
        if (index >= 0 && index < cart.getCart_items().size() && newQuantity > 0) {
            CartItem item = cart.getCart_items().get(index);
            cart.getCart_items().set(index,
                new CartItem(item.getMenu_category(), item.getMenu_name(), newQuantity, item.getDrinkTemp(), item.getItem_price())
            );
            cart.calculateTotal();
        }
    }

    public int getTotal(Cart cart) {
        return cart.calculateTotal();
    }

    public List<CartItem> getCartItems(Cart cart) {
        return cart.getCart_items();
    }
}
