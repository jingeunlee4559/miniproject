package minip.miniproject.view;

import java.util.List;
import java.util.Scanner;

import minip.miniproject.controller.CartController;
import minip.miniproject.controller.MenuController;
import minip.miniproject.controller.OrderController;
import minip.miniproject.controller.PaymentController;
import minip.miniproject.controller.UserController;
import minip.miniproject.model.DrinkTemperature;
import minip.miniproject.model.Member;
import minip.miniproject.model.Menu;



public class CoffeClass {

	public static final String YELLOW = "\u001B[33m";

	public static String color(int r, int g, int b) {
		return String.format("\u001B[38;2;%d;%d;%dm", r, g, b);
	}

	public static final String RESET = "\u001B[0m";
	// ddd
	public static void main(String[] args) {
		MenuController menuController = new MenuController();
		
		Scanner sc = new Scanner(System.in);

		String[] lines = { "  /$$$$$$             /$$$$$$   /$$$$$$  /$$            /$$$$$$           ",
				" /$$__  $$           /$$__  $$ /$$__  $$|__/           /$$__  $$          ",
				"| $$  \\__/  /$$$$$$ | $$  \\__/| $$  \\__/ /$$ /$$$$$$$ | $$  \\__/  /$$$$$$ ",
				"| $$       /$$__  $$| $$$$    | $$$$    | $$| $$__  $$| $$ /$$$$ /$$__  $$",
				"| $$      | $$  \\ $$| $$_/    | $$_/    | $$| $$  \\ $$| $$|_  $$| $$  \\ $$",
				"| $$    $$| $$  | $$| $$      | $$      | $$| $$  | $$| $$  \\ $$| $$  | $$",
				"|  $$$$$$/|  $$$$$$/| $$      | $$      | $$| $$  | $$|  $$$$$$/|  $$$$$$/",
				" \\______/  \\______/ |__/      |__/      |__/|__/  |__/ \\______/  \\______/ " };

		for (int i = 0; i < lines.length; i++) {
			int r = 255 - (i * 30);
			int g = 100 + (i * 15);
			int b = 255;

			System.out.println(color(r, g, b) + lines[i] + RESET);
		}

		System.out.println(
				"__\r\n" + " $$$b  `---.__\r\n" + "  \"$$b        `--.                          ___.---uuudP\r\n"
						+ "   `$$b           `.__.------.__     __.---'      $$$$\"              .\r\n"
						+ "     \"$b          -'            `-.-'            $$$\"              .'|\r\n"
						+ "       \".                                       d$\"             _.'  |\r\n"
						+ "         `.   /                              ...\"             .'     |\r\n"
						+ "           `./                           ..::-'            _.'       |\r\n"
						+ "            /                         .:::-'            .-'         .'\r\n"
						+ "           :                          ::''\\          _.'            |\r\n"
						+ "          .' .-.             .-.           `.      .'               |\r\n"
						+ "          : /'$$|           .@\"$\\           `.   .'              _.-'\r\n"
						+ "         .'|$u$$|          |$$,$$|           |  &lt;            _.-'\r\n"
						+ "         | `:$$:'          :$$$$$:           `.  `.       .-'\r\n"
						+ "         :                  `\"--'             |    `-.     \\\r\n"
						+ "        :##.       ==             .###.       `.      `.    `\\\r\n"
						+ "        |##:                      :###:        |        &gt;     &gt;\r\n"
						+ "        |#'     `..'`..'          `###'        x:      /     /\r\n"
						+ "         \\                                   xXX|     /    ./\r\n"
						+ "          \\                                xXXX'|    /   ./\r\n"
						+ "          /`-.                                  `.  /   /\r\n"
						+ "         :    `-  ...........,                   | /  .'\r\n"
						+ "         |         ``:::::::'       .            |&lt;    `.\r\n"
						+ "         |             ```          |           x| \\ `.:``.\r\n"
						+ "         |                         .'    /'   xXX|  `:`M`M':.\r\n"
						+ "         |    |                    ;    /:' xXXX'|  -'MMMMM:'\r\n"
						+ "         `.  .'                   :    /:'       |-'MMMM.-'\r\n"
						+ "          |  |                   .'   /'        .'MMM.-'\r\n"
						+ "          `'`'                   :  ,'          |MMM&lt;\r\n"
						+ "            |                     `'            |tbap\\\r\n"
						+ "             \\                                  :MM.-'\r\n"
						+ "              \\                 |              .''\r\n"
						+ "               \\.               `.            /\r\n"
						+ "                /     .:::::::.. :           /\r\n"
						+ "               |     .:::::::::::`.         /\r\n"
						+ "               |   .:::------------\\       /\r\n"
						+ "              /   .''               &gt;::'  /\r\n"
						+ "              `',:                 :    .'\r\n"
						+ "                                   `:.:'\r\n" + "");
		while (true) {
			try {
				System.out.println(
						"\n" + color(255, 215, 0) + "━━━━━━━━━━━━━━━━━━━━━━ 메뉴 ━━━━━━━━━━━━━━━━━━━━━━" + RESET);
				System.out.println(color(255, 255, 255) + "[1] 회원 모드 [2] 관리자 모드 [3] 종료" + RESET);
				System.out.print(color(0, 255, 150) + "선택 ▶ " + RESET);
				String inputStr = sc.nextLine();
				int input = Integer.parseInt(inputStr);

				if (input == 3) {
					System.out.println(color(200, 100, 100) + "커피 주문 시스템을 종료합니다. 감사합니다 ☕" + RESET);
					break;
				}

				switch (input) {
				case 1: // 회원 모드
					System.out.println("\n[1] 로그인 [2] 회원가입");
					System.out.print(color(0, 255, 150) + "선택 ▶ " + RESET);
					String num1Str = sc.nextLine();
					int num1 = Integer.parseInt(num1Str);

					if (num1 == 1) { // 로그인
						Member loginUser = UserController.loginMember();
					
						
						if (loginUser != null) {
							System.out.println(
									"\n" + color(100, 255, 100) + loginUser.getMem_nick() + "님 환영합니다!" + RESET);

							OrderController orderController = new OrderController();
							PaymentController paymentController = new PaymentController();
							CartController cartController = new CartController(loginUser.getMem_nick(), orderController, paymentController);
							while (true) {
								System.out.println("\n[1] 정보수정 [2] 장바구니 [3] 메뉴 조회 [4]주문내역 [0] 로그아웃");
								System.out.print(color(0, 255, 150) + "선택 ▶ " + RESET);
								String num2Str = sc.nextLine();
								int num2 = Integer.parseInt(num2Str);

								if (num2 == 1) {
									UserController.updateMemberInfo(null);
								} else if (num2 == 2) {
									// ★ 장바구니 기능 실행!
									cartController.start();
								} else if (num2 == 3) { 
									while (true) {
										System.out.println("\n--- 전체 메뉴 목록 ---");
										int i = 1;
										// 1. Controller를 통해 전체 메뉴 '데이터'를 가져옴
										List<Menu> allMenus = menuController.getAllMenus(); 
										for (Menu m : allMenus) {
										    System.out.println("[" + (i++) + "] " + m);
										}

										System.out.print("\n장바구니의 추가할 메뉴를 입력해주세요(뒤로가려면 exit): ");
										String menuName = sc.nextLine().trim();

										if (menuName.equalsIgnoreCase("exit"))
										    break;

										// 2. Controller를 통해 메뉴 이름으로 '데이터'를 검색
										Menu menu = menuController.getMenuByName(menuName); 

										if (menu == null) {
										    System.out.println("존재하지 않는 메뉴입니다.");
										    continue;
										}

										// 여기가 "장바구니에 추가"루틴
										System.out.print("ICE 또는 HOT 중 선택하세요: ");
										String tempInput = sc.nextLine().trim().toUpperCase();
										DrinkTemperature selectedTemp;
										if (tempInput.equals("HOT"))
											selectedTemp = DrinkTemperature.HOT;
										else if (tempInput.equals("ICE"))
											selectedTemp = DrinkTemperature.ICE;
										else {
											System.out.println("⚠ 입력 오류. 기본값 ICE로 설정됩니다.");
											selectedTemp = DrinkTemperature.ICE;
										}
										System.out.print("수량: ");
										int qty = 1;
										try {
											qty = Integer.parseInt(sc.nextLine().trim());
										} catch (NumberFormatException e) {
											System.out.println("기본값 1개로 처리됩니다.");
										}

										cartController.addItemToCart(menu, qty, selectedTemp);
										System.out.println("✅ 장바구니에 추가되었습니다.");

										System.out.print("계속 담으시겠습니까? (Y/N): ");
										if (sc.nextLine().trim().equalsIgnoreCase("N"))
											break;
									}
								}else if (num2 == 4) {
									orderController.getOrderDetails(loginUser.getMem_nick(), paymentController, sc);;
								}
								else if (num2 == 0) {
									System.out.println("로그아웃 되었습니다.");
									break;
								} else {
									System.out.println("잘못된 입력입니다.");
								}
							}
						}
					} else if (num1 == 2) {
						UserController.joinMember();
					} else {
						System.out.println("잘못된 입력입니다.");
					}
					break;

				case 2:
					System.out.println("관리자 모드는 추후 구현 예정입니다.");
					break;

				default:
					System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
				}
			} catch (NumberFormatException e) {
				System.out.println("⚠ 숫자만 입력해주세요!");
			}
		}

		sc.close();
	}
}
