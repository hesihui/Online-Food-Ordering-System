package com.onlineOrder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "menuitem")
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 7551999649936522523L;

    @Id
    private int id;

    private String name;

    private String description;

    private double price;

    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private com.onlineOrder.entity.Restaurant restaurant;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public com.onlineOrder.entity.Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(com.onlineOrder.entity.Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}
