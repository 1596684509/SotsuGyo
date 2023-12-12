package com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.Button.LanguageButtonimp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.R;

public class SystemLanguageSettingActivity extends AppCompatActivity {

    private Button setLanguageButton;

    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_language_setting);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        findView();
        initView();

    }

    private void findView() {

        setLanguageButton = findViewById(R.id.language_setting_button);
        returnButton = findViewById(R.id.systemLanguageSetting_ImageButton_return);

    }

    private void initView() {

        setLanguageButton.setOnClickListener(new LanguageButtonimp(this));
        returnButton.setOnClickListener(new ReturnButtonOnClickImp(this));

    }

}