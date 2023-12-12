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
import com.example.sotsugyou.R;

public class RegisterActivity extends AppCompatActivity {

    private ImageButton backImageButton;
    private TextView ruleTextViewFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        findView();
        initView();

    }

    private void findView() {

        backImageButton = findViewById(R.id.register_imageButton_back);
        ruleTextViewFirst = findViewById(R.id.register_textView_rule);

    }

    private void initView() {

        backImageButton.setOnClickListener(new ReturnButtonOnClickImp(this));

        SpannableString spannableString = new SpannableString("利用規約");
        spannableString.setSpan(new UnderlineSpan(), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ruleTextViewFirst.setText(spannableString);
        ruleTextViewFirst.setOnClickListener(new IntentEventImp(this, RegisterRuleActivity.class));

    }

}