package minip.miniproject.controller;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import minip.miniproject.model.*;
import minip.miniproject.service.CartService;

@Controller
public class CartController {

    private Cart cart;
    private final CartService cartService;
    private final OrderController orderController;
    private final PaymentController paymentController;
    private Scanner sc;

    @Autowired
    public CartController(String nickname, CartService cartService, OrderController orderController, PaymentController paymentController) {
        this.cart = new Cart(nickname);
        this.cartService = cartService;
        this.orderController = orderController;
        this.paymentController = paymentController;
        this.sc = new Scanner(System.in);
    }
    //sdsdsd

    public void start() {
        while (true) {
            System.out.println("\n=============== [" + cart.getMem_nick() + "님의 장바구니] ===============");
            if (cart.getCart_items().isEmpty()) {
                System.out.println("🛒 장바구니가 비었습니다. 메뉴 화면에서 추가해주세요.");
            } else {
                cart.printCart();
            }
            System.out.println("\n[1] 장바구니 전체 삭제\n[2] 개별 삭제\n[3] 수량 수정\n[4] 주문 \n0] 이전으로");
            System.out.print("선택 ▶ ");
            String sel = sc.nextLine().trim();

            switch (sel) {
                case "1":
                    cartService.clearCart(cart);
                    System.out.println("✅ 장바구니가 전체 삭제되었습니다.");
                    break;
                case "2":
                    removeItem();
                    break;
                case "3":
                    updateQuantity();
                    break;
                case "4":
                    placeOrder();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public void addItemToCart(Menu menu, int quantity, DrinkTemperature temp) {
        CartItem item = new CartItem(
                menu.getM_category(),
                menu.getM_name(),
                quantity,
                temp,
                menu.getM_price()
        );
        cartService.addItemToCart(cart, item);
    }

    private void removeItem() {
        if (cart.getCart_items().isEmpty()) {
            System.out.println("🛒 장바구니에 삭제할 항목이 없습니다.");
            return;
        }
        cart.printCart();
        System.out.print("\n삭제할 항목의 번호를 입력하세요 (1~" + cart.getCart_items().size() + ", 취소: 0): ");
        String input = sc.nextLine().trim();
        int idx;
        try {
            idx = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }
        if (idx == 0) {
            System.out.println("삭제를 취소했습니다.");
            return;
        }
        if (idx < 1 || idx > cart.getCart_items().size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }
        cartService.removeItem(cart, idx - 1);
        System.out.println("✅ 항목이 삭제되었습니다.");
    }

    private void updateQuantity() {
        if (cart.getCart_items().isEmpty()) {
            System.out.println("🛒 장바구니에 수정할 항목이 없습니다.");
            return;
        }
        cart.printCart();
        System.out.print("\n수량을 수정할 항목의 번호를 입력하세요 (1~" + cart.getCart_items().size() + ", 취소: 0): ");
        String input = sc.nextLine().trim();
        int idx;
        try {
            idx = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
            return;
        }
        if (idx == 0) {
            System.out.println("수정 취소.");
            return;
        }
        if (idx < 1 || idx > cart.getCart_items().size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }
        CartItem item = cart.getCart_items().get(idx - 1);
        System.out.print(item.getMenu_name() + " (" + item.getDrinkTemp() + ")의 새 수량 입력: ");
        String qtyStr = sc.nextLine().trim();
        int newQty;
        try {
            newQty = Integer.parseInt(qtyStr);
            if (newQty < 1) {
                System.out.println("수량은 1 이상이어야 합니다.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("숫자를 정확히 입력해 주세요.");
            return;
        }
        cartService.updateQuantity(cart, idx - 1, newQty);
        System.out.println("✅ 수량이 수정되었습니다.");
    }

    private void placeOrder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (cart.getCart_items().isEmpty()) {
            System.out.println("🛒 주문할 항목이 없습니다.");
            return;
        }

        // 1. 주문 객체 생성 (OrderController 이용)
        Order order = orderController.createOrder(cart);

        // 2. 결제 (PaymentController에서 처리)
        Payment payment = paymentController.makePayment(order, sc);

        // 3. 결제 결과 확인 및 출력
        if (payment.isPayment_successful()) {
            order.setOrder_status(OrderStatus.결제완료);
            cartService.clearCart(cart);
            System.out.println("✅ 결제가 성공적으로 완료되었습니다!");
        } else {
            order.setOrder_status(OrderStatus.결제실패);
            System.out.println("❌ 결제에 실패하였습니다.");
            return;
        }

        // 4. 주문 정보 출력
        System.out.println("주문번호: " + order.getOrder_id());
        System.out.println("주문시간: " + order.getOrder_time().format(formatter));
        System.out.println("결제수단: " + payment.getPayment_method());
        System.out.println("주문상태: " + order.getOrder_status());
        System.out.println("닉네임: " + order.getMem_nick());
        System.out.println("\n주문 내역");
        System.out.println("────────────────────────────────────────────");
        System.out.printf(" %-4s | %-8s | %-5s | %-4s | %8s\n", "번호", "메뉴명", "온도", "수량", "금액");
        System.out.println("────────────────────────────────────────────");
        int i = 1;
        int total = 0;
        for (CartItem item : order.getOrder_items()) {
            System.out.printf(" %4d | %-8s | %-5s | %4d | %,8d원\n",
                    i++,
                    item.getMenu_name(),
                    item.getDrinkTemp(),
                    item.getQuantity(),
                    item.getSubtotal());
            total += item.getSubtotal();
        }
        System.out.println("────────────────────────────────────────────");
        System.out.println("총합계: " + String.format("%,d원", total));
    }
}
