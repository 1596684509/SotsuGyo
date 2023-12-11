package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.R;

public class DollIconSettingActivity extends AppCompatActivity {

    private ImageView imageView;
    private Doll doll;

    private ImageButton imageButton;

    private RadioButton frameR1;
    private RadioButton frameR2;
    private RadioButton frameR3;
    private RadioButton frameR4;
    private RadioButton frameR5;
    private RadioButton frameR6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_icon_setting);

        initObject();
        findView();
        initView();

    }

    private void initObject() {

        doll = MainActivity.getApp().getUser().getDoll();

    }

    private void findView() {

        imageView = findViewById(R.id.ImageViewNowIcon);

        imageButton = findViewById(R.id.dollSettingIcon_ImageButton_return);

        frameR1 = findViewById(R.id.doll_setting_icon_frameR1);
        frameR2 = findViewById(R.id.doll_setting_icon_frameR2);
        frameR3 = findViewById(R.id.doll_setting_icon_frameR3);
        frameR4 = findViewById(R.id.doll_setting_icon_frameR4);
        frameR5 = findViewById(R.id.doll_setting_icon_frameR5);
        frameR6 = findViewById(R.id.doll_setting_icon_frameR6);

    }

    private void initView() {

        imageView.setImageDrawable(Util.getIconRadius(getResources() ,doll.getBitmap()));

        imageButton.setOnClickListener(new ReturnButtonOnClickImp(this));

        frameR1.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
        frameR2.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
        frameR3.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
        frameR4.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
        frameR5.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
        frameR6.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));

    }

}