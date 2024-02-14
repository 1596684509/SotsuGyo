package com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.EventClick.UpdataButtonClickImp;
import com.example.sotsugyou.Listener.GroupCheckedChanged.OnUserIconCheckedChangeImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.MyLinearLayout.FlowRadioGroup;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityAccountIconSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class AccountIconSettingActivity extends AppCompatActivity {

    private User user;
    private ImageView icon;

    private ActivityAccountIconSettingBinding binding;

    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    private FlowRadioGroup flowRadioGroup;
    private RadioButton seletorIconR1;
    private RadioButton seletorIconR2;
    private RadioButton seletorIconR3;
    private RadioButton seletorIconR4;
    private RadioButton seletorIconR5;
    private RadioButton seletorIconR6;

    private ImageButton backImageButton;

    private static HashMap<Integer, Integer> imageResourceIDhsm;

    private int userIcon = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountIconSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        user = MainActivity.getApp().getUser();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        initImageId();

        initObj();
        findView();
        initView();
        initLanguage();

    }

    private void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }

    private void initImageId() {

        imageResourceIDhsm = new HashMap<>();
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR1, R.drawable.usericon1);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR2, R.drawable.usericon2);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR3, R.drawable.usericon3);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR4, R.drawable.usericon4);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR5, R.drawable.usericon5);
        imageResourceIDhsm.put(R.id.account_setting_icon_iconR6, R.drawable.usericon6);

    }

    private void findView() {

        icon = findViewById(R.id.account_setting_icon);

        backImageButton = findViewById(R.id.setting_userIcon_backImageButton);

        seletorIconR1 = findViewById(R.id.account_setting_icon_iconR1);
        seletorIconR2 = findViewById(R.id.account_setting_icon_iconR2);
        seletorIconR3 = findViewById(R.id.account_setting_icon_iconR3);
        seletorIconR4 = findViewById(R.id.account_setting_icon_iconR4);
        seletorIconR5 = findViewById(R.id.account_setting_icon_iconR5);
        seletorIconR6 = findViewById(R.id.account_setting_icon_iconR6);

        flowRadioGroup = findViewById(R.id.account_setting_icon_flowRadioGroup);

    }

    private void initLanguage() {

        try {

            binding.accountSettingIconTitle.setText(jsonObject.getString("accountsetting_listitem1_title"));
            binding.accountSettingIconSaveButton.setText(jsonObject.getString("save_button"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void initView() {

        binding.accountSettingIconSaveButton.setOnClickListener(new UpdataButtonClickImp(this));
        icon.setImageDrawable(Util.getIconRadius(getResources(), user.getIconId()));

        flowRadioGroup.setOnCheckedChangeListener(new OnUserIconCheckedChangeImp(this));
        backImageButton.setOnClickListener(new ReturnButtonOnClickImp(this));

        radioImageSet(seletorIconR1);
        radioImageSet(seletorIconR2);
        radioImageSet(seletorIconR3);
        radioImageSet(seletorIconR4);
        radioImageSet(seletorIconR5);
        radioImageSet(seletorIconR6);

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

    public void setUserIcon(int userIcon) {
        this.userIcon = userIcon;
    }

    public int getUserIcon() {
        return userIcon;
    }
}