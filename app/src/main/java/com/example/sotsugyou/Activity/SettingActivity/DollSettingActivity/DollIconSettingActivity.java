package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.R;

public class DollIconSettingActivity extends AppCompatActivity {

    private ImageView imageView;
    private Doll doll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_icon_setting);

        initObject();
        findView();
        initView();

    }

    private void initObject() {

        doll = MainActivity.getApp().getDoll();

    }

    private void findView() {

        imageView = findViewById(R.id.ImageViewNowIcon);

    }

    private void initView() {

        imageView.setImageDrawable(Util.getIconRadius(getResources() ,doll.getPhotoID()));

    }

}