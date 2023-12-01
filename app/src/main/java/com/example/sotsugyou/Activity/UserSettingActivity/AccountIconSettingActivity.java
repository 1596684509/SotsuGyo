package com.example.sotsugyou.Activity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.Util;

public class AccountIconSettingActivity extends AppCompatActivity {

    private User user;
    private ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_icon_setting);
        user = MainActivity.getApp().getUser();

        findView();
        initView();

    }

    private void findView() {

        icon = findViewById(R.id.account_setting_icon);

    }

    private void initView() {

        icon.setImageDrawable(Util.getIconRadius(getResources(), user.getIconId()));

    }

}