package minip.miniproject.service;

import java.util.List;
import minip.miniproject.model.Menu;

/**
 * 메뉴와 관련된 비즈니스 로직의 명세(규칙)를 정의합니다.
 */
public interface MenuService {

    List<Menu> getAllMenus();
    Menu getMenuByName(String name);
}