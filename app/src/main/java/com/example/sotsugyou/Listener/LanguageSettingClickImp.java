package com.example.sotsugyou.Listener;

import android.content.Context;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.example.sotsugyou.R;


public class LanguageSettingClickImp implements PopupMenu.OnMenuItemClickListener {

    private Context context;

    public LanguageSettingClickImp(Context context) {
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        if(menuItem.getItemId() == R.id.japanese) {

            //TODO 日本語

        }else if(menuItem.getItemId() == R.id.chinaese) {

            //TODO 中国語

        }else if(menuItem.getItemId() == R.id.english) {

            //TODO 英語

        }

        return true;
    }
}
