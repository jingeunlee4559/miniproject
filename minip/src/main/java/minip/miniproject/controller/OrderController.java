package minip.miniproject.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import minip.miniproject.model.Cart;
import minip.miniproject.model.CartItem;
import minip.miniproject.model.Order;
import minip.miniproject.model.OrderStatus;
import minip.miniproject.model.Payment;



public class OrderController {
    private List<Order> orderHistory = new ArrayList<>();

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
        return orderHistory;
    }

    // 회원 닉네임으로 주문내역 조회
    public void getOrderDetails(String memNick, PaymentController paymentController, Scanner sc) {
        boolean found = false;
        int idx = 1; // 주문번호(리스트 순번)
        List<Order> myOrders = new ArrayList<>();
        for (Order order : orderHistory) {
            if (order.getMem_nick().equals(memNick)) {
                found = true;
                myOrders.add(order);
            }
        }
        if (!found) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }

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

        // ============== 결제취소/이전 메뉴 ================
        while (true) {
            System.out.println("\n[1] 결제취소  [0] 이전");
            System.out.print("선택 ▶ ");
            String sel = sc.nextLine();
            if (sel.equals("0")) break;
            if (sel.equals("1")) {
                // 주문 내역이 여러 개면 번호로 선택받기
                if (myOrders.size() > 1) {
                    System.out.print("결제취소할 주문의 번호를 입력하세요 (1~" + myOrders.size() + "): ");
                    try {
                        int selIdx = Integer.parseInt(sc.nextLine());
                        if (selIdx < 1 || selIdx > myOrders.size()) {
                            System.out.println("잘못된 번호입니다."); continue;
                        }
                        Order targetOrder = myOrders.get(selIdx - 1);
                        
                        // 결제 정보를 별도 저장/조회 중이면 찾아오기, 예시: PaymentController에 결제내역 있음
                        Payment payment = paymentController.findPaymentByOrderId(targetOrder.getOrder_id());
                        if (paymentController.cancelPayment(payment, targetOrder)) {
                            System.out.println("✅ 결제취소/환불이 완료되었습니다.");
                        } else {
                            System.out.println("❗ 결제취소/환불이 불가능합니다.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("잘못된 입력입니다."); continue;
                    }
                } else if (myOrders.size() == 1) { // 내역이 1개면 바로
                    Order targetOrder = myOrders.get(0);
                    Payment payment = paymentController.findPaymentByOrderId(targetOrder.getOrder_id());
                    if (paymentController.cancelPayment(payment, targetOrder)) {
                        System.out.println("✅ 결제취소/환불이 완료되었습니다.");
                    } else {
                        System.out.println("❗ 결제취소/환불이 불가능합니다.");
                    }
                }
                break; // 취소 후 다시 선택메뉴 나가는 구조
            } else {
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
