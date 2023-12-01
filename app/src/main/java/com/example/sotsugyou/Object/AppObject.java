package com.example.sotsugyou.Object;

import com.example.sotsugyou.R;

public class AppObject {

    private static User user;

    public AppObject() {

        user = new User();
        user.initUser("userTest", R.drawable.usericon1);

    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
