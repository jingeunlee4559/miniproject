// src/main/java/minip/miniproject/controller/MenuController.java
package minip.miniproject.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import minip.miniproject.model.Menu;
import minip.miniproject.service.MenuService;
import minip.miniproject.service.MenuServiceImpl;

@Controller
public class MenuController {

    private final MenuService menuService;

    public MenuController() {
        this.menuService = new MenuServiceImpl();
    }

    public List<Menu> getAllMenus() {
    
        return menuService.getAllMenus();
    }

    public Menu getMenuByName(String name) {
      
        return menuService.getMenuByName(name);
    }
}