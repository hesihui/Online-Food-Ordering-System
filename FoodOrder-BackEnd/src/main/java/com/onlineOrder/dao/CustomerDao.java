package com.onlineOrder.dao;

import com.onlineOrder.entity.Customer;
import com.onlineOrder.entity.Authorities;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
    //用于访问数据库
    //和数据库交互的所有代码都定义在Dao中

    //将在applicationconfig中扫描得到的sessionfactory injert到DAO层
    @Autowired
    private SessionFactory sessionFactory;

    //所有dao层面都用@Repository

    public void signUp(Customer customer) {
        //需要在dao中给用户添加权限
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER");

        Session session = null;
        try {
            //创建session对象
            session = sessionFactory.openSession();
            session.beginTransaction();
            //保存transaction中的用户信息
            session.save(authorities);
            session.save(customer);
            //commit transaction的内容，确保传输失败之后进行回滚操作
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            if(session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) session.close();
        }

    }

    public Customer getCustomer(String email) {
        Customer customer = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (session != null) session.close();;
        }

        return customer;
    }
}

