package minip.miniproject.service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

import org.springframework.stereotype.Service;
import minip.miniproject.model.*;

@Service
public class OrderService {
    private final List<Order> orderHistory = new ArrayList<>();

    public Order createOrder(Cart cart) {
        String orderId = UUID.randomUUID().toString();
        List<CartItem> orderItems = new ArrayList<>(cart.getCart_items());
        Order order = new Order(
            orderId,
            cart.getMem_nick(),
            orderItems,
            LocalDateTime.now(),
            OrderStatus.주문완료
        );
        orderHistory.add(order);
        return order;
    }

    public List<Order> getOrderHistory() {
        return new ArrayList<>(orderHistory);
    }

    public List<Order> getMyOrders(String memNick) {
        List<Order> myOrders = new ArrayList<>();
        for (Order order : orderHistory) {
            if (order.getMem_nick().equals(memNick)) {
                myOrders.add(order);
            }
        }
        return myOrders;
    }

    public Order findOrderById(String orderId) {
        for (Order order : orderHistory) {
            if (order.getOrder_id().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        Order order = findOrderById(orderId);
        if (order != null) {
            order.setOrder_status(status);
            return true;
        }
        return false;
    }
}
