package com.onlineOrder.service;
import com.onlineOrder.dao.ItemOrderDao;
import com.onlineOrder.entity.Customer;
import com.onlineOrder.entity.MenuItem;
import com.onlineOrder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderService {

    @Autowired
    private com.onlineOrder.service.MenuInfoService menuInfoService;

    @Autowired
    private com.laioffer.onlineOrder.service.CustomerService customerService;

    @Autowired
    private ItemOrderDao itemOrderDao;

    public void saveItem(int menuId) {
        final OrderItem orderItem = new OrderItem();
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);

        orderItem.setMenuItem(menuItem);
        orderItem.setCart(customer.getCart());
        orderItem.setQuantity(1);
        orderItem.setPrice(menuItem.getPrice());
        itemOrderDao.save(orderItem);
    }
}
