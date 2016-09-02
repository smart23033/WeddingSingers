package com.weddingsingers.wsapp.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class User implements Serializable{
    private String email;
    private String password;
    private String name;
    private String phone;
    private int type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "username : " + getName();
    }
}
