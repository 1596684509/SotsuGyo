package com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.Button.LanguageButtonimp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivitySystemLanguageSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class SystemLanguageSettingActivity extends AppCompatActivity {

    private LanguageHandler languageHandler;
    private JSONObject jsonObject;
    private ActivitySystemLanguageSettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySystemLanguageSettingBinding.inflate(getLayoutInflater());

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

    private void initLanguage() {

        try {
            binding.saveButton.setText(jsonObject.getString("save_button"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


    private void initView() {

        binding.languageSettingButton.setOnClickListener(new LanguageButtonimp(this));
        binding.systemLanguageSettingImageButtonReturn.setOnClickListener(new ReturnButtonOnClickImp(this));

    }

}