package com.example.mylittleshop.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class InventoryInfo {
    @JsonInclude(Include.NON_NULL)
    private long shopId;

    @JsonInclude(Include.NON_NULL)
    private String barcode;

    @JsonInclude(Include.NON_NULL)
    private int quantity;


    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}