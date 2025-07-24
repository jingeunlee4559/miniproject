package minip.miniproject.model;

import lombok.Data;

@Data
public class User {

    private String user_id;
    private String user_pw;


    public User(String user_id, String user_pw) throws UserFormatException {
        setUser_id(user_id);  
        setUser_pw(user_pw);  
    }

    public void setUser_id(String user_id) throws UserFormatException {
        if (user_id == null) {
            throw new UserFormatException("아이디는 null이 될 수 없습니다.");
        } else if (user_id.length() < 8 || user_id.length() > 20) {
            throw new UserFormatException("아이디는 8자 이상 20자 이하로 사용하세요.");
        }
        this.user_id = user_id;
    }


    public void setUser_pw(String user_pw) throws UserFormatException {
        if (user_pw == null) {
            throw new UserFormatException("패스워드는 null이 될 수 없습니다.");
        } else if (user_pw.length() < 5) {
            throw new UserFormatException("패스워드는 5자 이상으로 사용하세요.");
        }
        this.user_pw = user_pw;
    }
}
