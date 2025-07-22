// src/main/java/minip/miniproject/controller/MenuController.java
package minip.miniproject.controller;

import java.util.List;
import minip.miniproject.model.Menu;
import minip.miniproject.service.MenuService;
import minip.miniproject.service.MenuServiceImpl;

// @Controller 어노테이션은 그대로 둡니다.
public class MenuController {

    private final MenuService menuService;

    public MenuController() {
        this.menuService = new MenuServiceImpl();
    }

    /**
     * 모든 메뉴 '목록을 반환'하는 메소드
     * (이전의 displayAllMenus에서 변경)
     * @return Menu 객체 리스트
     */
    public List<Menu> getAllMenus() {
        // Service를 호출해서 데이터를 그대로 반환합니다.
        return menuService.getAllMenus();
    }

    /**
     * 이름으로 메뉴 '객체를 반환'하는 메소드
     * (이전의 findAndDisplayMenu에서 변경)
     * @param name 검색할 메뉴 이름
     * @return 찾아낸 Menu 객체 (없으면 null)
     */
    public Menu getMenuByName(String name) {
        // Service를 호출해서 데이터를 그대로 반환합니다.
        return menuService.getMenuByName(name);
    }
}