package com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sotsugyou.Data.Data;
import com.example.sotsugyou.Listener.Button.LanguageButtonimp;
import com.example.sotsugyou.Listener.Button.LanguageSettingSaveButtonEventImp;
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
    private String languageType;

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

    public void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }

    public void initLanguage() {

        try {
            binding.title.setText(jsonObject.getString("mainsetting_listitem3_title"));
            binding.saveButton.setText(jsonObject.getString("save_button"));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


    private void initView() {

        binding.languageSettingButton.setOnClickListener(new LanguageButtonimp(this));
        binding.systemLanguageSettingImageButtonReturn.setOnClickListener(new ReturnButtonOnClickImp(this));
        binding.saveButton.setOnClickListener(new LanguageSettingSaveButtonEventImp(this));

        SharedPreferences sharedPreferences = getSharedPreferences(Data.DATAFILE_NAME, MODE_PRIVATE);
        String showLangType = sharedPreferences.getString(LanguageHandler.LANGTYPE_SHOW_KEY, "日本語");
        binding.languageSettingButton.setText(showLangType);

    }

    public ActivitySystemLanguageSettingBinding getBinding() {
        return binding;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public String getLanguageType() {
        return languageType;
    }
}