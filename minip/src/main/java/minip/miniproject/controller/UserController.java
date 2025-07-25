package minip.miniproject.controller;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import minip.miniproject.model.Member;
import minip.miniproject.model.UserFormatException;
import minip.miniproject.model.mem_gender;
import minip.miniproject.service.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final Scanner sc = new Scanner(System.in);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void joinMember() {
        System.out.println("회원가입입니다.");
        System.out.print("아이디: ");
        String id = sc.nextLine();

        if (userService.isIdDuplicate(id)) {
            System.out.println("이미 존재하는 아이디입니다.");
            return;
        }

        System.out.print("비밀번호: ");
        String pw = sc.nextLine();

        System.out.print("닉네임: ");
        String nick = sc.nextLine();

        System.out.print("전화번호: ");
        String phone = sc.nextLine();

        System.out.print("성별 (남자/여자): ");
        String genderString = sc.nextLine();
        mem_gender genderEnum;
        try {
            genderEnum = mem_gender.valueOf(genderString);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 성별 입력입니다. '남자' 또는 '여자'로 입력해주세요.");
            return;
        }

        try {
            userService.joinMember(id, pw, nick, phone, genderEnum);
            System.out.println("회원가입이 완료되었습니다.");
        } catch (UserFormatException e) {
            System.out.println("회원가입 실패: " + e.getMessage());
        }
    }

    public Member loginMember() {
        System.out.println("로그인입니다.");
        System.out.print("아이디: ");
        String id = sc.nextLine(); 

        System.out.print("비밀번호: ");
        String pw = sc.nextLine();  

        Member m = userService.loginMember(id, pw);
        if (m != null) {
            System.out.println("로그인 성공!");
            return m;
        }
        System.out.println("로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
        return null;
    }


    public void updateMemberInfo(Member m) {
        System.out.println("[정보 수정 메뉴]");
        while (true) {
            try {
                System.out.println("[1] 닉네임 수정");
                System.out.println("[2] 전화번호 수정");
                System.out.println("[3] 비밀번호 수정");
                System.out.println("[4] 완료");
                System.out.print("선택: ");
                String input = sc.nextLine();
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        System.out.print("새 닉네임: ");
                        String newNick = sc.nextLine();
                        try {
                            userService.updateNickname(m, newNick);
                            System.out.println("닉네임 변경 성공 : " + m.getMem_nick());
                        } catch (UserFormatException e) {
                            System.out.println("닉네임 변경 실패: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("새 전화번호: ");
                        String newPhone = sc.nextLine();
                        try {
                            userService.updatePhone(m, newPhone);
                            System.out.println("전화번호 변경 성공 : " + m.getMem_phone());
                        } catch (UserFormatException e) {
                            System.out.println("전화번호 변경 실패: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("새 비밀번호: ");
                        String newPw = sc.nextLine();
                        try {
                            userService.updatePassword(m, newPw);
                            System.out.println("비밀번호 변경 성공" );
                        } catch (UserFormatException e) {
                            System.out.println("비밀번호 변경 실패: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("수정 완료!");
                        return;
                    default:
                        System.out.println("잘못된 선택입니다. 1~4 사이의 숫자를 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠ 숫자만 입력해주세요!!!!");
            }
        }
    }

    public List<Member> getMemberList() {
        return userService.getMemberList();
    }
}
