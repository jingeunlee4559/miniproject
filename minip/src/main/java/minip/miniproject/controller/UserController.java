package minip.miniproject.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import minip.miniproject.model.Member;
import minip.miniproject.model.UserFormatException;
import minip.miniproject.model.mem_gender;



public class UserController {
	
	 // 회원 저장 리스트
    private static List<Member> memberList = new ArrayList<>();
	
    public static void joinMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("회원가입입니다.");

        System.out.print("아이디: ");
        String id = sc.next();

        // 중복 체크
        for (Member m : memberList) {
            if (m.getUser_id().equals(id)) {
                System.out.println("이미 존재하는 아이디입니다.");
                return;
            }
        }

        System.out.print("비밀번호: ");
        String pw = sc.next();

        System.out.print("닉네임: ");
        String nick = sc.next();

        System.out.print("전화번호: ");
        String phone = sc.next();

        System.out.print("성별 (남자/여자): ");
        String genderString = sc.next();

        mem_gender genderEnum;
        try {
            genderEnum = mem_gender.valueOf(genderString);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 성별 입력입니다. '남자' 또는 '여자'로 입력해주세요.");
            return;
        }

        String active = "Y";
        LocalDateTime joinDate = LocalDateTime.now();

        // ✅ 예외 처리 필수
        try {
            Member newMember = new Member(id, pw, nick, phone, genderEnum, active, joinDate);
            memberList.add(newMember);
            System.out.println("회원가입이 완료되었습니다.");
        } catch (UserFormatException e) {
            System.out.println("회원가입 실패: " + e.getMessage());
        }
    }

    // 로그인 메서드
    public static Member loginMember() {
        Scanner sc = new Scanner(System.in);
        System.out.println("로그인입니다.");

        System.out.print("아이디: ");
        String id = sc.next();

        System.out.print("비밀번호: ");
        String pw = sc.next();

        for (Member m : memberList) {
            if (m.getUser_id().equals(id) && m.getUser_pw().equals(pw)) {
                System.out.println("로그인 성공!");
                return m;  // 로그인한 Member 객체 반환
            }
        }

        System.out.println("로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
        return null;
    }
    
    public static List<Member> getMemberList() {
        return memberList;
    }
    
    public static void updateMemberInfo(Member m) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[정보 수정 메뉴]");

        while (true) {
            try {
                System.out.println("[1] 닉네임 수정");
                System.out.println("[2] 전화번호 수정");
                System.out.println("[3] 비밀번호 수정");
                System.out.println("[4] 완료");
                System.out.print("선택: ");

                String input = sc.nextLine();  // 먼저 문자열로 입력받고
                int choice = Integer.parseInt(input); // 정수로 변환 시도

                switch (choice) {
                    case 1:
                        System.out.print("새 닉네임: ");
                        try {
                            m.setMem_nick(sc.nextLine());
                            System.out.println("닉네임 변경 성공 : " + m.getMem_nick());
                        } catch (UserFormatException e) {
                            System.out.println("닉네임 변경 실패: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.print("새 전화번호: ");
                        try {
                            m.setMem_phone(sc.nextLine());
                            System.out.println("전화번호 변경 성공 : " + m.getMem_phone());
                        } catch (UserFormatException e) {
                            System.out.println("전화번호 변경 실패: " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.print("새 비밀번호: ");
                        try {
                            m.setUser_pw(sc.nextLine());
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
                System.out.println("⚠ 숫자만 입력해주세요!");
            }
        }
    }

}
