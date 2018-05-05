package com.example.mylittleshop.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class EmployeeInfo {
    @JsonInclude(Include.NON_NULL)
    private String emp_username;

    @JsonInclude(Include.NON_NULL)
    private Long shopId;

    public String getUsername() {
        return emp_username;
    }

    public Long getShopId(){
        return this.shopId;
    }

}
