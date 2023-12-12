package com.example.sotsugyou.Activity.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.IntentEventImp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.R;

public class LoginActivity extends AppCompatActivity {

    private ImageButton returnButton;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        findView();
        initView();
    }

    private void findView() {

        returnButton = findViewById(R.id.login_imageButton_return);
        registerButton = findViewById(R.id.login_button_register);

    }

    private void initView() {

        returnButton.setOnClickListener(new ReturnButtonOnClickImp(this));
        registerButton.setOnClickListener(new IntentEventImp(this, RegisterActivity.class));

    }




}