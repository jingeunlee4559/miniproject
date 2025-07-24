package minip.miniproject.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class Admin extends User {
	
	private String admin_name;
	private int admin_level;
	private LocalDateTime last_login_date_time;

	// ✅ 반드시 예외 명시
	public Admin(String user_id, String user_pw, String admin_name, int admin_level,
			LocalDateTime last_login_date_time) throws UserFormatException {
		super(user_id, user_pw);  
		this.admin_name = admin_name;
		this.admin_level = admin_level;
		this.last_login_date_time = last_login_date_time;
	}
}
