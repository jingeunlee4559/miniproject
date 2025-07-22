package minip.miniproject.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import minip.miniproject.model.*;
import minip.miniproject.service.OrderService;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public Order createOrder(Cart cart) {
        return orderService.createOrder(cart);
    }

    // 회원 닉네임으로 주문내역 조회
    public void getOrderDetails(String memNick, PaymentController paymentController, Scanner sc) {
        List<Order> myOrders = orderService.getMyOrders(memNick);
        if (myOrders.isEmpty()) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }

        int idx = 1;
        System.out.println("==== " + memNick + "님의 주문 내역 ====");
        for (Order order : myOrders) {
            System.out.println("----------------------------------------");
            System.out.printf("[%d] 주문번호: %s\n", idx, order.getOrder_id());
            System.out.printf("주문시간: %s\n", order.getOrder_time()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            System.out.printf("주문상태: %s\n", order.getOrder_status());
            System.out.println("[메뉴내역]");
            int i = 1, total = 0;
            for (CartItem item : order.getOrder_items()) {
                System.out.printf(" %4d | %-8s | %-5s | %4d | %,8d원\n",
                        i++, item.getMenu_name(), item.getDrinkTemp(), item.getQuantity(), item.getSubtotal());
                total += item.getSubtotal();
            }
            System.out.println("총합계: " + String.format("%,d원", total));
            idx++;
        }

        // 결제취소/이전 메뉴
        while (true) {
            System.out.println("\n[1] 결제취소  [0] 이전");
            System.out.print("선택 ▶ ");
            String sel = sc.nextLine();
            if (sel.equals("0")) break;
            if (sel.equals("1")) {
                if (myOrders.size() > 1) {
                    System.out.print("결제취소할 주문의 번호를 입력하세요 (1~" + myOrders.size() + "): ");
                    try {
                        int selIdx = Integer.parseInt(sc.nextLine());
                        if (selIdx < 1 || selIdx > myOrders.size()) {
                            System.out.println("잘못된 번호입니다."); continue;
                        }
                        Order targetOrder = myOrders.get(selIdx - 1);
                        Payment payment = paymentController.findPaymentByOrderId(targetOrder.getOrder_id());
                        if (paymentController.cancelPayment(payment, targetOrder)) {
                            System.out.println("✅ 결제취소/환불이 완료되었습니다.");
                        } else {
                            System.out.println("❗ 결제취소/환불이 불가능합니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 입력입니다."); continue;
                    }
                } else if (myOrders.size() == 1) {
                    Order targetOrder = myOrders.get(0);
                    Payment payment = paymentController.findPaymentByOrderId(targetOrder.getOrder_id());
                    if (paymentController.cancelPayment(payment, targetOrder)) {
                        System.out.println("✅ 결제취소/환불이 완료되었습니다.");
                    } else {
                        System.out.println("❗ 결제취소/환불이 불가능합니다.");
                    }
                }
                break;
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
