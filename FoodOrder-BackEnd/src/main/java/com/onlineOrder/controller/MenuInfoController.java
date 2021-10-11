package com.onlineOrder.controller;
import com.onlineOrder.entity.MenuItem;
import com.onlineOrder.entity.Restaurant;
import com.onlineOrder.service.MenuInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class MenuInfoController {
    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable("restaurantId") int restaurantId) {
        return menuInfoService.getAllMenuItem(restaurantId);
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants() {
        return menuInfoService.getRestaurants();
    }
}
