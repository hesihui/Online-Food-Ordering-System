package com.onlineOrder.dao;

import com.onlineOrder.entity.OrderItem;
import com.onlineOrder.entity.Cart;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void removeCartItem(int cartItemId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            OrderItem cartItem = session.get(OrderItem.class, cartItemId);
            Cart cart = cartItem.getCart();
            cart.getOrderItemList().remove(cartItem);

            session.beginTransaction();
            session.delete(cartItem);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void removeAllCartItems(Cart cart) {
        for (OrderItem item : cart.getOrderItemList()) {
            removeCartItem(item.getId());
        }
    }
}
