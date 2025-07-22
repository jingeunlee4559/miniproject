package minip.miniproject.model;

import java.time.LocalDateTime;


public class Member extends User {

	private String mem_nick;
	private String mem_phone;
	private mem_gender gender; // Enum 필드
	private String mem_active;
	private LocalDateTime mem_joinDate;

	public Member(String user_id, String user_pw, String mem_nick, String mem_phone, mem_gender gender,
            String mem_active, LocalDateTime mem_joinDate) throws UserFormatException {
  super(user_id, user_pw);
  setMem_nick(mem_nick);
  setMem_phone(mem_phone);
  setGender(gender);
  this.mem_active = mem_active;
  this.mem_joinDate = mem_joinDate;
}

	public mem_gender getGender() {
		return gender;
	}

	public void setGender(mem_gender gender) throws UserFormatException {

		if (gender == null) {
			throw new UserFormatException("성별은 null이 될 수 없습니다.");
		} else if (!(gender == mem_gender.남자 || gender == mem_gender.여자)) {
			throw new UserFormatException("성별은 '남자' 또는 '여자'만 입력할 수 있습니다.");
		}
		this.gender = gender;
	}

	public String getMem_nick() {
		return mem_nick;
	}

	public void setMem_nick(String mem_nick) throws UserFormatException {

		if (mem_nick == null) {
			throw new UserFormatException("닉네임은 null이 될 수 없습니다.");
		} else if (mem_nick.length() < 8 || mem_nick.length() > 20) {
			throw new UserFormatException("닉네임은 8자 이상 20자 이하로 사용하세요.");
		}

		this.mem_nick = mem_nick;
	}

	public String getMem_phone() {
		return mem_phone;
	}

	public void setMem_phone(String mem_phone) throws UserFormatException {
	    if (mem_phone == null) {
	        throw new UserFormatException("전화번호는 null이 될 수 없습니다.");
	    } else if (!mem_phone.matches("^01[016789]\\d{8}$")) {
	        throw new UserFormatException("전화번호는 '01x'로 시작하는 숫자 11자리여야 합니다.");
	    }

	    this.mem_phone = mem_phone;
	}
	public String getMem_active() {
		return mem_active;
	}

	public void setMem_active(String mem_active) {
		this.mem_active = mem_active;
	}

	public LocalDateTime getMem_joinDate() {
		return mem_joinDate;
	}

	public void setMem_joinDate(LocalDateTime mem_joinDate) {
		this.mem_joinDate = mem_joinDate;
	}

}
