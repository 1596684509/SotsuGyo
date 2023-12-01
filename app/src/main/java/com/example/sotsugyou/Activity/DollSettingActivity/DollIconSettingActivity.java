package com.example.sotsugyou.Activity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.R;

public class DollIconSettingActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_icon_setting);

        findView();
        initView();

    }

    private void findView() {

        imageView = findViewById(R.id.ImageViewNowIcon);

    }

    private void initView() {

        imageView.setImageDrawable(Util.getIconRadius(getResources() ,R.drawable.dollicon1));

    }

}