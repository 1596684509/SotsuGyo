package com.example.sotsugyou.Activity.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityRegisterRuleBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterRuleActivity extends AppCompatActivity {

    private ActivityRegisterRuleBinding binding;
    private LanguageHandler languageHandler;
    private JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterRuleBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        initObj();
        initView();
        initLanguage();

    }

    private void initView() {

        binding.regiterRuleImageButtonBack.setOnClickListener(new ReturnButtonOnClickImp(this));

    }

    private void initLanguage() {

        try {

            binding.registerruleTitle.setText(jsonObject.getString("register_rule_title1"));
            binding.registerruleText.setText(jsonObject.getString("rule_title"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }


}