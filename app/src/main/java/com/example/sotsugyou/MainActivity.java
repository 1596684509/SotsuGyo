package com.example.sotsugyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sotsugyou.Activity.DollSettingActivity.DollIconSettingActivity;
import com.example.sotsugyou.Activity.UserSettingActivity.AccountIconSettingActivity;
import com.example.sotsugyou.Activity.UserSettingActivity.AccountSettingActivity;
import com.example.sotsugyou.Object.AppObject;

public class MainActivity extends AppCompatActivity {

    private static AppObject app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app = new AppObject();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, AccountSettingActivity.class);
        startActivity(intent);
    }

    public static AppObject getApp() {
        return app;
    }
}