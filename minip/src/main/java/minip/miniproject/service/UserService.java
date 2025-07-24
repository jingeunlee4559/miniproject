package minip.miniproject.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import minip.miniproject.model.Member;
import minip.miniproject.model.UserFormatException;
import minip.miniproject.model.mem_gender;

@Service
public class UserService {

    // 회원 저장 리스트 (static이 아닌 인스턴스 변수로 관리)
    private final List<Member> memberList = new ArrayList<>();

    public boolean isIdDuplicate(String id) {
        for (Member m : memberList) {
            if (m.getUser_id().equals(id)) {
                return true;
            }
        }
        return false;
    }
    public List<Member> getAllMembers() {
        return memberList;  // memberList는 회원 정보 리스트 필드
    }

    public void joinMember(String id, String pw, String nick, String phone, mem_gender gender)
            throws UserFormatException {
        if (isIdDuplicate(id)) {
            throw new UserFormatException("이미 존재하는 아이디입니다.");
        }
        String active = "Y";
        LocalDateTime joinDate = LocalDateTime.now();
        Member newMember = new Member(id, pw, nick, phone, gender, active, joinDate);
        memberList.add(newMember);
    }

    public Member loginMember(String id, String pw) {
        for (Member m : memberList) {
            if (m.getUser_id().equals(id) && m.getUser_pw().equals(pw)) {
                return m;
            }
        }
        return null;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void updateNickname(Member m, String newNick) throws UserFormatException {
        m.setMem_nick(newNick);
    }

    public void updatePhone(Member m, String newPhone) throws UserFormatException {
        m.setMem_phone(newPhone);
    }

    public void updatePassword(Member m, String newPw) throws UserFormatException {
        m.setUser_pw(newPw);
    }
}
