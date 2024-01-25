package com.example.sotsugyou.Listener.EventClick;

import android.content.Context;
import android.util.Log;
import android.util.Range;
import android.view.View;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Object.Exp;

import java.util.Random;

public class TestClickImp implements View.OnClickListener{

    private Context context;

    public TestClickImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        if(context != null) {

            expClick();

        }

    }

    private void expClick() {

        AppObject app = MainActivity.getApp();

        Exp exp = app.getUser().getDoll().getExp();
        int exp1 = new Random().nextInt(10000);
        Log.i("TestClick", "增加了" + exp1 + "点经验");
        exp.addExp();


    }


}
