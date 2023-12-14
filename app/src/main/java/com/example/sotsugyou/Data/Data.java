package com.example.sotsugyou.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.Utils.Util;

public class Data implements DataHandler{

    private AppObject app;
    private Doll doll;

    public Data() {

    }

    @Override
    public void save(Context context) {

        app = MainActivity.getApp();
        doll = app.getUser().getDoll();

        if(doll == null) {

            return;

        }

        SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor esed = sp.edit();
        esed.putString("name", doll.getName());
        esed.putString("image",Util.getImageByte(doll.getBitmap()));
        esed.apply();

    }

    @Override
    public boolean load(Context context) {

        app = MainActivity.getApp();

        String name = null;
        String image = null;

        try {

            SharedPreferences sp = context.getSharedPreferences("data", Context.MODE_PRIVATE);
            name = sp.getString("name", null);
            image = sp.getString("image", null);

            if(name != null && image != null) {

                app.getUser().initDoll(name);
                app.getUser().getDoll().setBitmap(Util.getBitMapForBtye(image));


                return false;

            }

            return true;
        }catch (Exception e) {

            e.printStackTrace();

            Log.i("Data", "load: data load error");
            return true;

        }

    }
}
