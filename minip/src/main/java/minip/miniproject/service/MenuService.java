package minip.miniproject.service;

import java.util.List;
import minip.miniproject.model.Menu;


public interface MenuService {

    List<Menu> getAllMenus();
    Menu getMenuByName(String name);
}