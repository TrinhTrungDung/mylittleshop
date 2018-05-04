package com.example.mylittleshop.entity;

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
    private Shop shop;
    @ManyToOne
    @JoinColumn(name="barcode",nullable = false,foreignKey = @ForeignKey(name = "INVEN_ITEM_FK"))
    private Item item;

    public InventoryID(){}

    public InventoryID(Shop shop,Item item){
        this.shop = shop;
        this.item = item;
    }

    public Shop getShop() {
        return this.shop;
    }

    public Item getItem() {
        return this.item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryID)) return false;
        InventoryID that = (InventoryID) o;
        return Objects.equals(getShop().getId(), that.getShop().getId()) &&
                Objects.equals(getItem().getCode(), that.getItem().getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShop().getId(), getItem().getCode());
    }
}
