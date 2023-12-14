package com.example.sotsugyou.Object;

import com.example.sotsugyou.Data.Data;
import com.example.sotsugyou.R;


public class AppObject {

    private static User user;

    private static Data data;

    public AppObject() {

        data = new Data();

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initDefaultUser() {

        user = new User();
        user.setName("default");
        user.setIconId(R.drawable.defaultusericon);

    }

    public User getUser() {

        return user;

    }

    public static Data getData() {
        return data;
    }
}
