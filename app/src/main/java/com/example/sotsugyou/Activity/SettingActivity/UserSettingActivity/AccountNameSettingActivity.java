package com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.EventClick.UpdataButtonClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityAccountNameSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountNameSettingActivity extends AppCompatActivity {

    private ActivityAccountNameSettingBinding binding;
    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountNameSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        initObj();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        initView();
        initLanguage();

    }

    private void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }

    private void initLanguage() {

        try {

            binding.accountSettingNameTitle.setText(jsonObject.getString("accountsetting_listitem2_title"));
            binding.accountSettingNameEditText.setHint(jsonObject.getString("accountnamesetting_hint"));
            binding.accountSettingNameSaveButton.setText(jsonObject.getString("save_button"));

        } catch (JSONException e) {

            e.printStackTrace();

        }

    }

    private void initView() {

       binding.settingUserNameBackImageButton.setOnClickListener(new ReturnButtonOnClickImp(this));
       binding.accountSettingNameSaveButton.setOnClickListener(new UpdataButtonClickImp(this));

    }

    public ActivityAccountNameSettingBinding getBinding() {
        return binding;
    }
}