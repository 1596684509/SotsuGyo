package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.R;

public class DollNameSettingActivity extends AppCompatActivity {

    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_name_setting);

        findView();
        initView();

    }

    private void findView() {

        returnButton = findViewById(R.id.dollSettingName_ImageButton_return);

    }

    private void initView() {

        returnButton.setOnClickListener(new ReturnButtonOnClickImp(this));

    }

}