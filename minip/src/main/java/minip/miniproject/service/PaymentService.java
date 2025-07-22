package minip.miniproject.service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.stereotype.Service;
import minip.miniproject.model.Order;
import minip.miniproject.model.OrderStatus;
import minip.miniproject.model.Payment;

@Service
public class PaymentService {

    private final List<Payment> paymentList = new ArrayList<>();

    public Payment makePayment(Order order, String paymentMethod) {
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
        paymentList.add(payment);
        return payment;
    }

    public Payment findPaymentByOrderId(String orderId) {
        for (Payment p : paymentList) {
            if (p.getOrder_id().equals(orderId)) return p;
        }
        return null;
    }

    public boolean cancelPayment(Payment payment, Order order) {
        if (payment == null || !payment.isPayment_successful()) {
            System.out.println("❗ 이미 취소된 결제거나, 결제가 완료되지 않았습니다.");
            return false;
        }
        payment.refundPayment();
        order.setOrder_status(OrderStatus.결제취소);
        return true;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentList);
    }
}
