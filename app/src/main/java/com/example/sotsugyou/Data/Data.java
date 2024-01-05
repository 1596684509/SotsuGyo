package com.example.sotsugyou.Data;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Object.Exp;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.Utils.Util;

public class Data implements DataHandler{

    public static final String DATAFILE_NAME = "data";
    private AppObject app;
    private Doll doll;

    public Data() {

    }

    @Override
    public void save() {

        app = MainActivity.getApp();
        doll = app.getUser().getDoll();

        if(doll == null) {

            return;

        }

        SharedPreferences sp = MainActivity.getApp().getContext().getSharedPreferences(DATAFILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor esed = sp.edit();
        esed.putString("name", doll.getName());
        esed.putString("image",Util.getImageByte(doll.getBitmap()));
        esed.putInt("leave", doll.getExp().getLeave());
        esed.putInt("exp", doll.getExp().getExp());
        esed.apply();

    }

    @Override
    public boolean load() {

        app = MainActivity.getApp();

        String name = null;
        String image = null;
        int exp;
        int leave;

        try {

            SharedPreferences sp = MainActivity.getApp().getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
            name = sp.getString("name", null);
            image = sp.getString("image", null);
            exp = sp.getInt("exp", -1);
            leave = sp.getInt("leave", -1);

            if(name != null && image != null) {

                app.getUser().initDoll(name);

                if(exp != -1 && exp != -1) {

                    Exp expC = new Exp();
                    expC.initExp(leave, exp);
                    app.getUser().getDoll().setExp(expC);

                }else {

                    Log.w("Data", "load: exp error: -1");
                    Log.w("Data", "load: init exp");

                }

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
