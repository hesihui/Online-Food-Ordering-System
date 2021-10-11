package com.laioffer.onlineOrder.service;

import com.onlineOrder.dao.CustomerDao;
import com.onlineOrder.entity.Cart;
import com.onlineOrder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    //Spring会自动找到customerdao的对象，将它注入到CustomerService

    public void signUp(Customer customer) {
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnabled(true);
        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }

}

