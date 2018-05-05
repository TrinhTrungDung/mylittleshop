package com.example.mylittleshop.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

public class ImportInfo {
    @JsonInclude(Include.NON_NULL)
    private long id;

    @JsonInclude(Include.NON_NULL)
    private Long shopId;

    @JsonInclude(Include.NON_NULL)
    private String barcode;

    @JsonInclude(Include.NON_NULL)
    private String username;

    @JsonInclude(Include.NON_NULL)
    private Date impDate;

    @JsonInclude(Include.NON_NULL)
    private int quantity;

    public long getId() {
        return id;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getUsername() {
        return username;
    }

    public Date getImpDate() {
        return impDate;
    }

    public int getQuantity() {
        return quantity;
    }
}
