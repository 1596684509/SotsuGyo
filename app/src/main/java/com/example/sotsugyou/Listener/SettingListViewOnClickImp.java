package com.example.sotsugyou.Listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity.SystemLanguageSettingActivity;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.R;

public class SettingListViewOnClickImp implements AdapterView.OnItemClickListener {

    private Context context;

    public SettingListViewOnClickImp(Context context) {
        this.context = context;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        if(adapterView.getItemAtPosition(i) instanceof ListViewItem) {

            ListViewItem item = (ListViewItem) adapterView.getItemAtPosition(i);

            if (item.getTargetActivity() != null) {

                Intent intent = new Intent(context, item.getTargetActivity());
                context.startActivity(intent);

            }else if(item.getIntent() != null) {

                Intent intent = item.getIntent();
                context.startActivity(intent);

            }else if(item.getOnClickListener() != null) {

                item.getOnClickListener().onClick(view);

            }
        }


//        if(i == 2) {
//
//            Intent intent = new Intent(context, SystemLanguageSettingActivity.class);
//            context.startActivity(intent);
//
//        }

    }
}
