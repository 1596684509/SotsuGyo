package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollNameSettingActivity;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.databinding.ActivityDollNameSettingBinding;

public class DollNameSetButtonImp implements View.OnClickListener {

    private Context context;

    public DollNameSetButtonImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        if(context instanceof DollNameSettingActivity) {

            ActivityDollNameSettingBinding binding = ((DollNameSettingActivity) context).getBinding();

            if(!binding.nameInputBar.getText().toString().trim().equals("")) {

                MainActivity.getApp().getUser().getDoll().setName(binding.nameInputBar.getText().toString());
                ((DollNameSettingActivity) context).finish();
                DataHandler dataHandler = AppObject.getData();
                dataHandler.save();

            }

        }

    }
}
