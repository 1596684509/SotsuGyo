package com.example.sotsugyou.Object;

import android.content.Context;

import com.example.sotsugyou.Data.Data;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Setting.LanguageType;
import com.example.sotsugyou.Utils.BluetoothHandler;
import com.example.sotsugyou.Utils.ServerConncetHandler;


public class AppObject {

    private Context context;
    private static User user;

    private static Data data;

    private static LanguageHandler languageHandler;
    private BluetoothHandler bluetoothHandler;
    private ServerConncetHandler serverConncetHandler;

    public AppObject(Context context) {

        this.context = context;
        data = new Data();
        languageHandler = new LanguageHandler(this.context);
        bluetoothHandler = new BluetoothHandler(context);
        serverConncetHandler = new ServerConncetHandler();
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
        user.setIconId(R.drawable.defaultusericon);

    }

    public User getUser() {

        return user;

    }

    public static Data getData() {
        return data;
    }

    public Context getContext() {
        return context;
    }

    public BluetoothHandler getBluetoothHandler() {
        return bluetoothHandler;
    }

    public ServerConncetHandler getServerConncetHandler() {
        return serverConncetHandler;
    }
}
