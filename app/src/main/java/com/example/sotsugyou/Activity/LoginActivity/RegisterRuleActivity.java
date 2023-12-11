package com.example.sotsugyou.Activity.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.R;

public class RegisterRuleActivity extends AppCompatActivity {

    private ImageButton backImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_rule);

        backImageButton = findViewById(R.id.regiterRule_imageButton_back);
        backImageButton.setOnClickListener(new ReturnButtonOnClickImp(this));

    }
}