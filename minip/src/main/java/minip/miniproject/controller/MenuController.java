package minip.miniproject.controller;

import java.util.ArrayList;
import java.util.List;

import minip.miniproject.model.DrinkTemperature;
import minip.miniproject.model.Menu;
import minip.miniproject.model.MenuCategory;



public class MenuController {
    private List<Menu> menuList;

    public MenuController() {
        menuList = new ArrayList<>();

        menuList.add(new Menu("M001", MenuCategory.커피, "아메리카노", 4500, DrinkTemperature.ICE));
        menuList.add(new Menu("M002", MenuCategory.커피, "카페라떼", 5000, DrinkTemperature.HOT));
        menuList.add(new Menu("T001", MenuCategory.차, "녹차", 4000, DrinkTemperature.HOT));
        menuList.add(new Menu("J001", MenuCategory.주스, "오렌지주스", 4800, DrinkTemperature.ICE));
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public Menu getMenuByName(String m_name) {
        for (Menu menu : menuList) {
            if (menu.getM_name().equals(m_name)) {
                return menu;
            }
        }
        return null;
    }
}
