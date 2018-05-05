package com.example.mylittleshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "item")
public class Item {

    @Column (name = "item_name", length = 30, nullable = false)
    private String name;

    @Column(name="item_category",length = 20,nullable = false)
    private String category;

    @Id
    @Column(name="barcode",length = 20,nullable = false)
    private String barcode;

    @Column (name = "price",nullable = false)
    private int price;

    public Item() {
    }

    public Item (String name, String category, String barcode, int price) {
        this.name = name;
        this.category = category;
        this.barcode = barcode;
        this.price = price;
    }


    public String getCode(){
        return this.barcode;
    }

    public void setCode(String code){
        this.barcode = code;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getCategory(){
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice(){
        return this.price;
    }

    public void setPrice(int price){
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Item)) {
            return false;
        }

        Item item = (Item) obj;

        return this.barcode.equals(item.getCode()) && this.name.equals(item.getName())
                && this.category.equals(item.getCategory()) && this.price == item.getPrice();
    }
}
