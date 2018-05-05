package com.example.mylittleshop.entity;

import com.example.mylittleshop.service.IntAccountService;
import com.example.mylittleshop.service.IntShopService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name="emp_shop")
public class Employee {
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emp_username",nullable = false,foreignKey = @ForeignKey(name = "EMP_SHOP_FK"))
    private String emp_username;

    @ManyToOne
    @JoinColumn(name="shop_id",nullable = false,foreignKey = @ForeignKey(name = "SHOP_EMP_FK"))
    private long shopId;

    @Autowired
    private IntAccountService accountService;

    @Autowired
    private IntShopService shopService;

    public Employee() {}

    public Employee(String username, Long shopId) {
        if (accountService.getAllAccounts().contains(accountService.getAccountByUsername(username))
                && shopService.getAllShops().contains(shopService.getShopById(shopId))) {
            this.emp_username = username;
            this.shopId = shopId;
        } else {
            throw new IllegalArgumentException("Emp_shop must have username and shop id");
        }
    }

    public String getUsername() {
        return emp_username;
    }

    public void setUsername(String username) {
        if (accountService.getAllAccounts().contains(accountService.getAccountByUsername(username))) {
            this.emp_username = username;
        } else {
            throw new IllegalArgumentException("Emp_shop must contain existed username from Emp table");
        }
    }

    public Long getShopId(){
        return this.shopId;
    }

    public void setShopId(Long shopId){
        if (shopService.getAllShops().contains(shopService.getShopById(shopId))) {
            this.shopId = shopId;
        } else {
            throw new IllegalArgumentException("Emp_shop must contain existed shop_id from Shop table");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Employee)) {
            return false;
        }

        Employee employee = (Employee) obj;

        return this.emp_username.equals(employee.getUsername()) && this.shopId == employee.getShopId();
    }

}
