package com.example.mylittleshop.entity;

import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {
    @EmbeddedId
    private InventoryID id;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    public InventoryID getId() {
        return this.id;
    }
    public void setId(InventoryID id) {
        this.id = id;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
