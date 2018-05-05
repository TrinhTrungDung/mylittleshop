package com.example.mylittleshop.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AccountInfo {
    @JsonInclude(Include.NON_NULL)
    private String username;

    @JsonInclude(Include.NON_NULL)
    private String name;

    @JsonInclude(Include.NON_NULL)
    private String password;

    @JsonInclude(Include.NON_NULL)
    private boolean active;

    @JsonInclude(Include.NON_NULL)
    private String role;

    public String getUsername(){
        return username;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public String getRole() {
        return this.role;
    }

}
