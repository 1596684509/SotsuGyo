package com.example.sotsugyou.Activity.First;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sotsugyou.Listener.Button.FirstNextButtonImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.R;

public class FirstDollNameSettingActivity extends AppCompatActivity {


    private EditText editText;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_doll_name_setting);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        findView();
        initView();

    }

    private void findView() {

        editText = findViewById(R.id.first_editText);
        next = findViewById(R.id.first_nextButton);

    }

    private void initView() {

        next.setOnClickListener(new FirstNextButtonImp(this));

    }

    public EditText getEditText() {
        return editText;
    }
}