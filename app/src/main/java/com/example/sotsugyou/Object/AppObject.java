package com.example.sotsugyou.Object;

import com.example.sotsugyou.R;

public class AppObject {

    private static User user;
    private static Doll doll;

    public AppObject() {

        doll = new Doll();
        doll.initDoll(R.drawable.dollicon1, "テストネーム");

    }

    public void setDoll(Doll doll) {
        AppObject.doll = doll;
    }

    public Doll getDoll() {
        return doll;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {

        if (user == null) {

            user = new User();
            user.setDefaultUser();
            user.initUser("Default User", R.drawable.defaultusericon);
            user.initDoll(doll);
            return user;

        }

        return user;

    }

}
