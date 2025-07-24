package minip.miniproject.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Payment {
	private String payment_id;
	private String order_id;
	private String mem_nick;
	private String payment_method;
	private LocalDateTime payment_time;
	private boolean payment_successful;
	private boolean mem_active;

	public Payment(String payment_id, String order_id, String mem_nick, String payment_method,
			LocalDateTime payment_time, boolean payment_successful, boolean mem_active) {
		this.payment_id = payment_id;
		this.order_id = order_id;
		this.mem_nick = mem_nick;
		this.payment_method = payment_method;
		this.payment_time = payment_time;
		this.payment_successful = payment_successful;
		this.mem_active = mem_active;
	}

	public boolean refundPayment() {
		if (!this.payment_successful) {
			System.out.println("❗ 이미 취소된 결제거나, 결제가 아직 완료되지 않았습니다.");
			return false;
		}
		this.payment_successful = false;
		this.mem_active = false;
		System.out.println("✅ 결제가 환불(취소) 처리되었습니다.");
		return true;
	}
}
