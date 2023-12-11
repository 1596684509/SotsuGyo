package com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.R;

public class AccountNameSettingActivity extends AppCompatActivity {

    private ImageButton backImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_name_setting);

        findView();
        initView();

    }

    private void findView() {

        backImageButton = findViewById(R.id.setting_userName_backImageButton);

    }

    private void initView() {

        backImageButton.setOnClickListener(new ReturnButtonOnClickImp(this));

    }

}