package com.example.mylittleshop.model;

public class Password {
    private String old_password;
    private String new_password;
    private String confirm_password;

    public Password(){}

    public Password(String current_password, String new_password, String confirm_password){
        this.old_password = current_password;
        this.new_password = new_password;
        this.confirm_password = confirm_password;
    }

    public boolean check(){
        return new_password.equals(confirm_password);
    }

    public String getOld_password() {
        return old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }
}
