package minip.miniproject.model;

import java.time.LocalDateTime;
import java.util.List;

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
    
    
    public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public void setOrder_items(List<CartItem> order_items) {
		this.order_items = order_items;
	}

	public void setOrder_time(LocalDateTime order_time) {
		this.order_time = order_time;
	}

	public void setOrder_status(OrderStatus order_status) {
		this.order_status = order_status;
	}



    // Getter
    public String getOrder_id() {
        return order_id;
    }

    public String getMem_nick() {
        return mem_nick;
    }

    public List<CartItem> getOrder_items() {
        return order_items;
    }

    public LocalDateTime getOrder_time() {
        return order_time;
    }

    public OrderStatus getOrder_status() {
        return order_status;
    }
}