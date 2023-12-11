package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.sotsugyou.Activity.First.FirstDollImageSettingActivity;
import com.example.sotsugyou.Activity.First.FirstDollNameSettingActivity;
import com.example.sotsugyou.MainActivity;

public class FirstNextButtonImp implements View.OnClickListener {

    private Context context;

    public FirstNextButtonImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        if(context instanceof FirstDollNameSettingActivity) {

            FirstDollNameSettingActivity activity = (FirstDollNameSettingActivity) context;

            if(!activity.getEditText().getText().equals("") || activity.getEditText().getText() != null) {

                MainActivity.getApp().getUser().initDoll(activity.getEditText().getText().toString());

                Log.i("dollInit", MainActivity.getApp().getUser().getDoll().getName());
                Intent intent = new Intent(context, FirstDollImageSettingActivity.class);
                context.startActivity(intent);

            }

        }

    }
}
