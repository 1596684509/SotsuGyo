package com.example.sotsugyou.Activity.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.EventClick.IntentEventImp;
import com.example.sotsugyou.Listener.EventClick.UpdataButtonClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
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

        try {

            binding.registerImageButtonBack.setOnClickListener(new ReturnButtonOnClickImp(this));

            SpannableString spannableString = new SpannableString(jsonObject.getString("register_rule_title1"));
            spannableString.setSpan(new UnderlineSpan(), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            binding.registerTextViewRule.setText(spannableString);
            binding.registerTextViewRule.setOnClickListener(new IntentEventImp(this, RegisterRuleActivity.class));
            binding.registerRegButton.setOnClickListener(new UpdataButtonClickImp(this));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initLanguage() {

        try {

            binding.registerTitle.setText(jsonObject.getString("register_saveregister"));
            binding.registerUserId.setText(jsonObject.getString("register_idinputbar_title"));
            binding.registerUserName.setText(jsonObject.getString("register_nameinputbar_title"));
            binding.registerPassword.setText(jsonObject.getString("register_passwordinputbar_title"));
            binding.registerPasswordAgain.setText(jsonObject.getString("register_pwagaininputbar_title"));
            binding.registerRegButton.setText(jsonObject.getString("register_saveregister"));
            binding.registerTextViewRule.setText(jsonObject.getString("register_rule_title1"));
            binding.registerTextViewRule2.setText(jsonObject.getString("register_rule_title2"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    public ActivityRegisterBinding getBinding() {
        return binding;
    }
}