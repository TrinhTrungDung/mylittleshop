package com.example.mylittleshop.entity;

import com.example.mylittleshop.service.IntAccountService;
import com.example.mylittleshop.service.IntItemService;
import com.example.mylittleshop.service.IntShopService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="import_history")
public class Import {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false,foreignKey = @ForeignKey(name = "IMP_SHOP_FK"))
    private Long shopId;

    @ManyToOne
    @JoinColumn(name="barcode",nullable = false,foreignKey = @ForeignKey(name = "IMP_ITEM_FK"))
    private String barcode;

    @ManyToOne
    @JoinColumn(name = "emp_username",nullable = false,foreignKey = @ForeignKey(name="IMP_EMP_FK"))
    private String username;

    @Temporal(TemporalType.DATE)
    @Column(name = "imp_date",columnDefinition = "DATE",nullable = false)
    private Date impDate;

    @Column(name="quantity",nullable = false)
    private int quantity;

    @Autowired
    private IntAccountService accountService;

    @Autowired
    private IntShopService shopService;

    @Autowired
    private IntItemService itemService;

    public Import() {}

    public Import(Long shopId, String barcode, String username, Date impDate, int quantity) {
        if (shopService.getAllShops().contains(shopService.getShopById(shopId))
                && itemService.getAllItems().contains(itemService.getItemByBarcode(barcode))
                && accountService.getAllAccounts().contains(accountService.getAccountByUsername(username))) {
            this.shopId = shopId;
            this.barcode = barcode;
            this.username = username;
            this.impDate = impDate;
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Import must contain all three essential attributes");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        if (shopService.getAllShops().contains(shopService.getShopById(shopId))) {
            this.shopId = shopId;
        } else {
            throw new IllegalArgumentException("Import must contain shop id");
        }
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        if (itemService.getAllItems().contains(itemService.getItemByBarcode(barcode))) {
            this.barcode = barcode;
        } else {
            throw new IllegalArgumentException("Import must contain barcode");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (accountService.getAllAccounts().contains(accountService.getAccountByUsername(username))) {
            this.username = username;
        } else {
            throw new IllegalArgumentException("Import must contain username");
        }
    }

    public Date getImpDate() {
        return impDate;
    }
    public void setImpDate(Date impDate) {
        this.impDate = impDate;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}