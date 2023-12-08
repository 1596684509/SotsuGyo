package com.example.sotsugyou.Activity.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.Button.ReturnButtonOnClickImp;
import com.example.sotsugyou.R;

public class LoginActivity extends AppCompatActivity {

    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findView();
        initView();
    }

    private void findView() {

        returnButton = findViewById(R.id.login_imageButton_return);

    }

    private void initView() {

        returnButton.setOnClickListener(new ReturnButtonOnClickImp(this));

    }




}