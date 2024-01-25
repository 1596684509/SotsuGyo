package com.example.sotsugyou.Listener.EventClick;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ShowHintClickEventImp implements View.OnClickListener {

    private MainFragment context;
    private String hint;

    public ShowHintClickEventImp(MainFragment context, String hint) {
        this.context = context;
        this.hint = hint;
    }

    @Override
    public void onClick(View view) {

        JSONObject jsonObject = MainActivity.getApp().getLanguageHandler().getLanguageJson();

        try {
            context.getBinding().explanation.setText(jsonObject.getString(hint));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}
