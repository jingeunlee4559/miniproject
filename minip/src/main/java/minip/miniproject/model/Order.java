package minip.miniproject.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Order {

    private String order_id;
    private String mem_nick;
    private List<CartItem> order_items;
    private LocalDateTime order_time;
    
	private OrderStatus order_status;

    public Order(String order_id, String mem_nick, List<CartItem> order_items, LocalDateTime order_time, OrderStatus order_status) {
        this.order_id = order_id;
        this.mem_nick = mem_nick;
        this.order_items = order_items;
        this.order_time = order_time;
        this.order_status = order_status;
    }
    public int getTotalPrice() {
        int total = 0;
        if (order_items != null) {
            for (CartItem item : order_items) {
                total += item.getSubtotal();
            }
        }
        return total;
    }
    

}