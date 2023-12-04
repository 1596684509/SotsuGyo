package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

import com.example.sotsugyou.Listener.LanguageSettingClickImp;
import com.example.sotsugyou.R;

public class LanguageButtonimp implements View.OnClickListener {

    private Context context;

    public LanguageButtonimp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.language_popup_menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new LanguageSettingClickImp(context));

    }
}
