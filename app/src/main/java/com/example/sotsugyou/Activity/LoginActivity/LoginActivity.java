package com.example.sotsugyou.Activity.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.sotsugyou.Listener.EventClick.IntentEventImp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityLoginBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        initObj();
        initView();
        initLanguage();
    }

    private void initView() {

        binding.loginImageButtonReturn.setOnClickListener(new ReturnButtonOnClickImp(this));
        binding.loginButtonRegister.setOnClickListener(new IntentEventImp(this, RegisterActivity.class));

    }

    private void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }

    private void initLanguage() {

        try {

            binding.loginTitle.setText(jsonObject.getString("login_title"));
            binding.loginUserIdTitle.setText(jsonObject.getString("login_idinputbar_title"));
            binding.loginPasswordTitle.setText(jsonObject.getString("login_passwordinputbar_title"));
            binding.loginTitle2.setText(jsonObject.getString("login_title2"));
            binding.loginButtonRegister.setText(jsonObject.getString("login_toregisterbutton_text"));
            binding.loginLoginButton.setText(jsonObject.getString("login_loginbutton_text"));


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


}