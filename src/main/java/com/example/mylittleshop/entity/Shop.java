package com.example.mylittleshop.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="shop")
public class Shop implements Serializable {

    @Id
    @Column(name="shop_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "shop_name", length = 30,nullable = false)
    private String name;

    public Shop () {}

    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Shop)) {
            return false;
        }

        Shop shop = (Shop) obj;

        return this.id == shop.getId() && this.name.equals(shop.getName());
    }
}