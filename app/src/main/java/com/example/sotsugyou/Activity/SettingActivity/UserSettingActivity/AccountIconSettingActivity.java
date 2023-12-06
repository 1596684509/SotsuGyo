package com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.sotsugyou.Listener.OnCheckedChangeImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.MyLinearLayout.FlowRadioGroup;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.Util;

import java.util.HashMap;

public class AccountIconSettingActivity extends AppCompatActivity {

    private User user;
    private ImageView icon;

    private FlowRadioGroup flowRadioGroup;
    private RadioButton seletorIconR1;
    private RadioButton seletorIconR2;
    private RadioButton seletorIconR3;
    private RadioButton seletorIconR4;
    private RadioButton seletorIconR5;
    private RadioButton seletorIconR6;

    private static HashMap<Integer, Integer> imageResourceIDhsm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_icon_setting);
        user = MainActivity.getApp().getUser();

        initImageId();

        findView();
        initView();

    }

    private void initImageId() {

        imageResourceIDhsm = new HashMap<>();
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR1, R.drawable.usericon1);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR2, R.drawable.usericon2);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR3, R.drawable.usericon3);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR4, R.drawable.usericon1);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR5, R.drawable.usericon1);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR6, R.drawable.usericon1);

    }

    private void findView() {

        icon = findViewById(R.id.account_setting_icon);

        seletorIconR1 = findViewById(R.id.account_setting_icon_iconR1);
        seletorIconR2 = findViewById(R.id.account_setting_icon_iconR2);
        seletorIconR3 = findViewById(R.id.account_setting_icon_iconR3);
        seletorIconR4 = findViewById(R.id.account_setting_icon_iconR4);
        seletorIconR5 = findViewById(R.id.account_setting_icon_iconR5);
        seletorIconR6 = findViewById(R.id.account_setting_icon_iconR6);

        flowRadioGroup = findViewById(R.id.account_setting_icon_flowRadioGroup);

    }

    private void initView() {

        icon.setImageDrawable(Util.getIconRadius(getResources(), user.getIconId()));

        flowRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeImp(this));

        radioImageSet(seletorIconR1);
        radioImageSet(seletorIconR2);
        radioImageSet(seletorIconR3);
        radioImageSet(seletorIconR4);
        radioImageSet(seletorIconR5);
        radioImageSet(seletorIconR6);

//        seletorIconR1.setBackground(Util.getIconRadius(getResources(), imageResourceIDhsm.get(R.id.account_setting_icon_iconR1)));
//        seletorIconR2.setBackground(Util.getIconRadius(getResources(), imageResourceIDhsm.get(R.id.account_setting_icon_iconR2)));
//        seletorIconR3.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
//        seletorIconR4.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
//        seletorIconR5.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
//        seletorIconR6.setBackground(Util.getIconRadius(getResources(), R.drawable.usericon1));
    }

    private void radioImageSet(RadioButton radioButton) {

        radioButton.setBackground(Util.getIconRadius(getResources(), imageResourceIDhsm.get(radioButton.getId())));

    }

    public ImageView getIcon() {
        return icon;
    }

    public static HashMap<Integer, Integer> getImageResourceIDhsm() {
        return imageResourceIDhsm;
    }
}