package com.example.sotsugyou.Object;

import android.content.Context;

import com.example.sotsugyou.Data.Data;
import com.example.sotsugyou.Enum.SoundIdEnum;
import com.example.sotsugyou.Item.Item;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Setting.LanguageType;
import com.example.sotsugyou.Utils.BluetoothHandler;
import com.example.sotsugyou.Utils.ServerConncetHandler;
import com.example.sotsugyou.Utils.SoundPlay;

import java.util.ArrayList;
import java.util.List;


public class AppObject {

    private Context context;
    private static User user;

    private static Data data;

    private static LanguageHandler languageHandler;
    private BluetoothHandler bluetoothHandler;
    private SoundPlay soundPlay;

    public AppObject(Context context) {

        this.context = context;
        data = new Data();
        languageHandler = new LanguageHandler(this.context);
        bluetoothHandler = new BluetoothHandler(context);;
        soundPlay = new SoundPlay(context);
        soundPlay.setSound(SoundIdEnum.CAT);

        initDefaultUser();

    }

    public LanguageHandler getLanguageHandler() {
        return languageHandler;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initDefaultUser() {

        user = new User();
        user.initUser("default", "default", R.drawable.defaultusericon);
        user.setDefaultUser();

    }

    public void resetDefaultUser() {

        user.initUser("default", "default", R.drawable.defaultusericon);
        user.setDefaultUser();

    }

    public void initUser(String json) {



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

    public SoundPlay getSoundPlay() {
        return soundPlay;
    }
}
