package com.example.mylittleshop.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

public class SaleInfo {
    @JsonInclude(Include.NON_NULL)
    private long id;

    @JsonInclude(Include.NON_NULL)
    private Long shopId;

    @JsonInclude(Include.NON_NULL)
    private String barcode;

    @JsonInclude(Include.NON_NULL)
    private String username;

    @JsonInclude(Include.NON_NULL)
    private Date expDate;

    @JsonInclude(Include.NON_NULL)
    private int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}