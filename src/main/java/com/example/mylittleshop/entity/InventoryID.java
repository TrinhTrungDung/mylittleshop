package com.example.mylittleshop.entity;

import com.example.mylittleshop.service.IntItemService;
import com.example.mylittleshop.service.IntShopService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InventoryID implements Serializable {

    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false,foreignKey = @ForeignKey(name = "INVEN_SHOP_FK"))
    private long shopId;

    @ManyToOne
    @JoinColumn(name="barcode",nullable = false,foreignKey = @ForeignKey(name = "INVEN_ITEM_FK"))
    private String barcode;

    @Autowired
    private IntShopService shopService;

    @Autowired
    private IntItemService itemService;

    public InventoryID() {}

    public InventoryID(Long shopId, String barcode) {
        if (shopService.getAllShops().contains(shopService.getShopById(shopId))
                && itemService.getAllItems().contains(itemService.getItemByBarcode(barcode))) {
            this.shopId = shopId;
            this.barcode = barcode;
        } else {
            throw new IllegalArgumentException("Inventory ID must be combination of shop ID and item barcode");
        }
    }

    public Long getShopId() {
        return this.shopId;
    }

    public String getItemBarcode() {
        return this.barcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryID)) return false;

        InventoryID that = (InventoryID) o;

        return this.shopId == that.getShopId() && this.barcode.equals(that.getItemBarcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShopId(), getItemBarcode());
    }
}
