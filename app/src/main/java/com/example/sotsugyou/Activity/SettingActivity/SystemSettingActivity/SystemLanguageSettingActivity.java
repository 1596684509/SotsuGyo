package com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.sotsugyou.Listener.Button.LanguageButtonimp;
import com.example.sotsugyou.R;

public class SystemLanguageSettingActivity extends AppCompatActivity {

    private Button setLanguageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_language_setting);

        findView();
        initView();

    }

    private void findView() {

        setLanguageButton = findViewById(R.id.language_setting_button);

    }

    private void initView() {

        setLanguageButton.setOnClickListener(new LanguageButtonimp(this));

    }

}