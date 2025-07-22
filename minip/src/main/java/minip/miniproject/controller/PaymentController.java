package minip.miniproject.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import minip.miniproject.model.Order;
import minip.miniproject.model.OrderStatus;
import minip.miniproject.model.Payment;



public class PaymentController {

    // 모든 결제내역 저장
    private List<Payment> paymentList = new ArrayList<>();

    // 결제 처리
    public Payment makePayment(Order order, Scanner sc) {
        System.out.println("결제수단을 선택하세요: [1] 카드 [2] 카카오페이");
        String paySel = sc.nextLine().trim();
        String paymentMethod;
        switch (paySel) {
            case "1": paymentMethod = "카드"; break;
            case "2": paymentMethod = "카카오페이"; break;
            default: paymentMethod = "기타"; break;
        }

        String paymentId = UUID.randomUUID().toString();
        LocalDateTime paymentTime = LocalDateTime.now();
        boolean paymentSuccessful = true;

        Payment payment = new Payment(
                paymentId,
                order.getOrder_id(),
                order.getMem_nick(),
                paymentMethod,
                paymentTime,
                paymentSuccessful,
                true
        );
        paymentList.add(payment); // 결제내역 저장
        return payment;
    }

    // 주문id로 결제내역 찾기
    public Payment findPaymentByOrderId(String orderId) {
        for (Payment p : paymentList) {
            if (p.getOrder_id().equals(orderId)) return p;
        }
        return null;
    }

    // 결제 환불/취소
    public boolean cancelPayment(Payment payment, Order order) {
        if (payment == null || !payment.isPayment_successful()) {
            System.out.println("❗ 이미 취소된 결제거나, 결제가 완료되지 않았습니다.");
            return false;
        }
        payment.refundPayment();
        order.setOrder_status(OrderStatus.결제취소);
        return true;
    }
}