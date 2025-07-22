package minip.miniproject.controller;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import minip.miniproject.model.Order;
import minip.miniproject.model.Payment;
import minip.miniproject.service.PaymentService;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // 결제 처리 (콘솔 입력 포함, 실제 사용 예시)
    public Payment makePayment(Order order, Scanner sc) {
        System.out.println("결제수단을 선택하세요: [1] 카드 [2] 카카오페이");
        String paySel = sc.nextLine().trim();
        String paymentMethod;
        switch (paySel) {
            case "1": paymentMethod = "카드"; break;
            case "2": paymentMethod = "카카오페이"; break;
            default: paymentMethod = "기타"; break;
        }
        return paymentService.makePayment(order, paymentMethod);
    }

    // 주문id로 결제내역 찾기
    public Payment findPaymentByOrderId(String orderId) {
        return paymentService.findPaymentByOrderId(orderId);
    }

    // 결제 환불/취소
    public boolean cancelPayment(Payment payment, Order order) {
        return paymentService.cancelPayment(payment, order);
    }

    public void printAllPayments() {
        for (Payment payment : paymentService.getAllPayments()) {
            System.out.println("결제ID: " + payment.getPayment_id() + ", 주문ID: " + payment.getOrder_id()
                + ", 회원: " + payment.getMem_nick() + ", 수단: " + payment.getPayment_method()
                + ", 성공여부: " + payment.isPayment_successful());
        }
    }
}
