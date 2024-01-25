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
import java.util.HashMap;
import java.util.List;


public class AppObject {

    private Context context;
    private static User user;

    private static Data data;
    private static HashMap<Integer, Item> itemHashMap = new HashMap<>();

    private static LanguageHandler languageHandler;
    private static BluetoothHandler bluetoothHandler;
    private SoundPlay soundPlay;

    public AppObject(Context context) {

        this.context = context;
        initDefaultUser();
        data = new Data();
        languageHandler = new LanguageHandler(this.context);
        soundPlay = new SoundPlay(context);
        bluetoothHandler = new BluetoothHandler(context);
        initItemHashMapData();



    }

    public static HashMap<Integer, Item> getItemHashMap() {
        return itemHashMap;
    }
    public void initItemHashMapData() {

        itemHashMap.put(R.id.doll_setting_icon_frameR1, new Item(R.drawable.frame1, 10));
        itemHashMap.put(R.id.doll_setting_icon_frameR2, new Item(R.drawable.frame2, 25));
        itemHashMap.put(R.id.doll_setting_icon_frameR3, new Item(R.drawable.frame3, 40));
        itemHashMap.put(R.id.doll_setting_icon_frameR4, new Item(R.drawable.frame4, 55));
        itemHashMap.put(R.id.doll_setting_icon_frameR5, new Item(R.drawable.frame5, 70));
        itemHashMap.put(R.id.doll_setting_icon_frameR6, new Item(R.drawable.frame6, 85));
        itemHashMap.put(R.id.backgroundRb1, new Item(R.drawable.background1, 15));
        itemHashMap.put(R.id.backgroundRb2, new Item(R.drawable.background2, 30));
        itemHashMap.put(R.id.backgroundRb3, new Item(R.drawable.background3, 45));
        itemHashMap.put(R.id.backgroundRb4, new Item(R.drawable.background4, 60));
        itemHashMap.put(R.id.backgroundRb5, new Item(R.drawable.background5, 75));
        itemHashMap.put(R.id.backgroundRb6, new Item(R.drawable.background6, 90));

    }

    public void setBluetoothHandler(BluetoothHandler bluetoothHandler) {
        this.bluetoothHandler = bluetoothHandler;
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

        if(bluetoothHandler == null) {

            bluetoothHandler = new BluetoothHandler(context);

        }

        return bluetoothHandler;

    }

    public SoundPlay getSoundPlay() {
        return soundPlay;
    }
}
