package minip.miniproject.model;

import java.time.LocalDateTime;

public class Payment {
    private String payment_id;
    private String order_id;
    private String mem_nick;
    private String payment_method;
    private LocalDateTime payment_time;
    private boolean payment_successful;
    private boolean mem_active;

    // 생성자
    public Payment(String payment_id, String order_id, String mem_nick,
                   String payment_method, LocalDateTime payment_time,
                   boolean payment_successful, boolean mem_active) {
        this.payment_id = payment_id;
        this.order_id = order_id;
        this.mem_nick = mem_nick;
        this.payment_method = payment_method;
        this.payment_time = payment_time;
        this.payment_successful = payment_successful;
        this.mem_active = mem_active;
    }

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) {
		this.mem_nick = mem_nick;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public LocalDateTime getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(LocalDateTime payment_time) {
		this.payment_time = payment_time;
	}

	public boolean isPayment_successful() {
		return payment_successful;
	}

	public void setPayment_successful(boolean payment_successful) {
		this.payment_successful = payment_successful;
	}

	public boolean isMem_active() {
		return mem_active;
	}

	public void setMem_active(boolean mem_active) {
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
