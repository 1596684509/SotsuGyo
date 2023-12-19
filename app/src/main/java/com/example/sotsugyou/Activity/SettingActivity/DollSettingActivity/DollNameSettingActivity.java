package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityDollNameSettingBinding;
import com.example.sotsugyou.databinding.ActivityDollSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class DollNameSettingActivity extends AppCompatActivity {

    private ActivityDollNameSettingBinding binding;
    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDollNameSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        initObj();
        initView();
        initLanguage();

    }

    private void initLanguage() {

        try {

            binding.title.setText(jsonObject.getString("dollnamesetting_title"));
            binding.nameInputBar.setHint(jsonObject.getString("dollnamesetting_inputhint"));
            binding.saveButton.setText(jsonObject.getString("save_button"));

        } catch (JSONException e) {

            e.printStackTrace();

        }

    }

    private void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }

    private void initView() {

        binding.dollSettingNameImageButtonReturn.setOnClickListener(new ReturnButtonOnClickImp(this));

    }

}