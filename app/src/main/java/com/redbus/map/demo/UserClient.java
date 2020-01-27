package com.redbus.map.demo;

import android.app.Application;

import com.redbus.map.demo.models.User;


public class UserClient extends Application {

    private User user = null;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
