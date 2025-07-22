package minip.miniproject.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import minip.miniproject.model.DrinkTemperature;
import minip.miniproject.model.Menu;
import minip.miniproject.model.MenuCategory;

@Service // 이 클래스가 비즈니스 로직을 처리하는 '서비스' 컴포넌트임을 명시합니다.
public class MenuServiceImpl implements MenuService {

    // 데이터 저장소 역할. 원래 Controller에 있던 리스트를 Service로 이동.
    // 실제 프로젝트에서는 이 부분을 DAO(Repository) 계층으로 분리하여 DB와 연동합니다.
    private final List<Menu> menuList;

    public MenuServiceImpl() {
        menuList = new ArrayList<>();
        menuList.add(new Menu("M001", MenuCategory.커피, "아메리카노", 4500, DrinkTemperature.ICE));
        menuList.add(new Menu("M002", MenuCategory.커피, "카페라떼", 5000, DrinkTemperature.HOT));
        menuList.add(new Menu("T001", MenuCategory.차, "녹차", 4000, DrinkTemperature.HOT));
        menuList.add(new Menu("J001", MenuCategory.주스, "오렌지주스", 4800, DrinkTemperature.ICE));
    }

    @Override
    public List<Menu> getAllMenus() {
        // 모든 메뉴 리스트를 반환하는 로직
        return this.menuList;
    }

    @Override
    public Menu getMenuByName(String name) {
        // 이름으로 메뉴를 검색하는 로직
        for (Menu menu : menuList) {
            if (menu.getM_name().equals(name)) {
                return menu;
            }
        }
        return null; // 찾지 못하면 null 반환
    }
}