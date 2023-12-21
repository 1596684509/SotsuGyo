package com.example.sotsugyou.Listener;

import android.content.Context;
import android.view.MenuItem;
import android.widget.PopupMenu;

import com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity.SystemLanguageSettingActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageType;
import com.example.sotsugyou.databinding.ActivitySystemLanguageSettingBinding;


public class LanguageSettingClickImp implements PopupMenu.OnMenuItemClickListener {

    private Context context;

    public LanguageSettingClickImp(Context context) {
        this.context = context;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {



        if(context instanceof SystemLanguageSettingActivity) {

            SystemLanguageSettingActivity activity = (SystemLanguageSettingActivity) context;
            ActivitySystemLanguageSettingBinding binding = activity.getBinding();
            binding.languageSettingButton.setText(menuItem.getTitle());

            if(menuItem.getItemId() == R.id.japanese) {

                activity.setLanguageType(LanguageType.Japanese.type);

            }else if(menuItem.getItemId() == R.id.chinaese) {

                activity.setLanguageType(LanguageType.Chinese.type);

            }else if(menuItem.getItemId() == R.id.english) {

                activity.setLanguageType(LanguageType.English.type);

            }

        }

        return true;
    }
}
