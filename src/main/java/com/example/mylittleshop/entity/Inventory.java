package com.example.mylittleshop.entity;

import com.example.mylittleshop.service.IntItemService;
import com.example.mylittleshop.service.IntShopService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false,foreignKey = @ForeignKey(name = "INVEN_SHOP_FK"))
    private long shopId;

    @ManyToOne
    @JoinColumn(name="barcode",nullable = false,foreignKey = @ForeignKey(name = "INVEN_ITEM_FK"))
    private String barcode;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    @Autowired
    private IntShopService shopService;

    @Autowired
    private IntItemService itemService;

    public Inventory() {}

    public Inventory(Long shopId, String barcode, int quantity) {
        if (shopService.getAllShops().contains(shopService.getShopById(shopId))
                && itemService.getAllItems().contains(itemService.getItemByBarcode(barcode))) {
            this.shopId = shopId;
            this.barcode = barcode;
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Inventory ID must be combination of shop ID and item barcode");
        }
    }

    public Long getShopId() {
        return this.shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getBarcode() {
        return this.barcode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;

        Inventory that = (Inventory) o;

        return this.shopId == that.getShopId() && this.barcode.equals(that.getBarcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShopId(), getBarcode());
    }

}
