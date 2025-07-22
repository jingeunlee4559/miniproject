package minip.miniproject.model;

public enum OrderStatus {
	주문완료, 결제완료, 주문처리완료, 결제실패,결제취소;
	
	public boolean isCompleted() {
		return this == 주문처리완료 || this == 결제취소 || this == 결제실패;
	}
	
	

}
