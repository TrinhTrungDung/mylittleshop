package com.example.mylittleshop.entity;

import javax.persistence.*;

@Entity
@Table(name="shop")
public class Shop {

    @Id
    @Column(name="shop_id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "shop_name", length = 30,nullable = false)
    private String name;

    public Shop (){
    }

    public Shop(int id , String name  ){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }


}
