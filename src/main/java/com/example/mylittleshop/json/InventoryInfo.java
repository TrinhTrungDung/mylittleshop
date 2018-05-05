package com.example.mylittleshop.json;

import com.example.mylittleshop.entity.InventoryID;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class InventoryInfo {
    @JsonInclude(Include.NON_NULL)
    private InventoryID id;

    @JsonInclude(Include.NON_NULL)
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