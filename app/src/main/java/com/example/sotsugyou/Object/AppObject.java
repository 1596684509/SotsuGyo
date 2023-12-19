package com.example.sotsugyou.Object;

import android.content.Context;

import com.example.sotsugyou.Data.Data;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Setting.LanguageType;


public class AppObject {

    private Context context;
    private static User user;

    private static Data data;

    private static LanguageHandler languageHandler;

    public AppObject(Context context) {

        this.context = context;
        data = new Data();
        languageHandler = new LanguageHandler(this.context);
    }

    public LanguageHandler getLanguageHandler() {
        return languageHandler;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initDefaultUser() {

        user = new User();
        user.setName("default");
        user.setDefaultUser();
        user.setIconId(R.drawable.defaultusericon);

    }

    public User getUser() {

        return user;

    }

    public static Data getData() {
        return data;
    }
}
