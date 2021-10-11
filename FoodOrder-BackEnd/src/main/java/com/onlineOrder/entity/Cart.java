package com.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<com.onlineOrder.entity.OrderItem> orderItemList;

    private double totalPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<com.onlineOrder.entity.OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<com.onlineOrder.entity.OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

}
