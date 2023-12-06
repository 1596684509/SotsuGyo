package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.sotsugyou.Activity.LoginActivity.LoginActivity;
import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountSettingActivity;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;

public class UserSettingClickImp implements View.OnClickListener {

    private Context context;

    public UserSettingClickImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        User user = MainActivity.getApp().getUser();
        Intent intent;

        if(user.isDefaultUser()) {

            intent = new Intent(context, LoginActivity.class);

        }else {

            intent = new Intent(context, AccountSettingActivity.class);

        }

        context.startActivity(intent);

    }
}
