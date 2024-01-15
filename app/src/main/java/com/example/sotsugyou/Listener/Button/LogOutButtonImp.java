package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.view.View;

import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountSettingActivity;
import com.example.sotsugyou.MainActivity;

public class LogOutButtonImp implements View.OnClickListener{

    private Context context;

    public LogOutButtonImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        if(context instanceof AccountSettingActivity) {

            AccountSettingActivity activity = (AccountSettingActivity) context;
            MainActivity.getApp().resetDefaultUser();
            activity.finish();

        }

    }
}
