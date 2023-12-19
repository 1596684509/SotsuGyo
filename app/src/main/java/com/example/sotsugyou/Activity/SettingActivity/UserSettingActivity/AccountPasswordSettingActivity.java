package com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityAccountPasswordSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountPasswordSettingActivity extends AppCompatActivity {


    private ActivityAccountPasswordSettingBinding binding;
    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountPasswordSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        initObj();
        initView();
        initLanguage();

    }

    private void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }


    private void initView() {

        binding.settingUserPassBackImageButton.setOnClickListener(new ReturnButtonOnClickImp(this));



    }

    private void initLanguage() {

        try {

            binding.accountSettingPasswordTitle.setText(jsonObject.getString("accountsetting_listitem3_title"));
            binding.accountSettingPasswordSaveButton.setText(jsonObject.getString("save_button"));
            binding.accountSettingNewpasswordEditText.setHint(jsonObject.getString("accountpasswordsetting_newpasswordedittext_hint"));
            binding.accountSettingOldpasswordEditText.setHint(jsonObject.getString("accountpasswordsetting_oldpasswordedittext_hint"));


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}