package com.example.sotsugyou.Listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity.SystemLanguageSettingActivity;
import com.example.sotsugyou.R;

public class SettingListViewOnClickImp implements AdapterView.OnItemClickListener {

    private Context context;

    public SettingListViewOnClickImp(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(i == 2) {

            Intent intent = new Intent(context, SystemLanguageSettingActivity.class);
            context.startActivity(intent);

        }

    }
}
