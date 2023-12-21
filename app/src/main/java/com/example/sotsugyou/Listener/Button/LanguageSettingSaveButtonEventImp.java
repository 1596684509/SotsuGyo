package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity.SystemLanguageSettingActivity;
import com.example.sotsugyou.Data.Data;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityDollSettingBinding;
import com.example.sotsugyou.databinding.ActivitySystemLanguageSettingBinding;

public class LanguageSettingSaveButtonEventImp implements View.OnClickListener {

    private Context context;

    public LanguageSettingSaveButtonEventImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        if(context instanceof SystemLanguageSettingActivity) {

            SystemLanguageSettingActivity activity = (SystemLanguageSettingActivity) context;

            SharedPreferences sharedPreferences = activity.getSharedPreferences(Data.DATAFILE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(activity.getLanguageType() != null) {

                editor.putString(LanguageHandler.LANGTYPE_KEY, activity.getLanguageType());
                editor.putString(LanguageHandler.LANGTYPE_SHOW_KEY, (String) activity.getBinding().languageSettingButton.getText());
                editor.commit();
                LanguageHandler languageHandler = MainActivity.getApp().getLanguageHandler();
                languageHandler.setLanguageType(activity.getLanguageType());
                activity.initObj();
                activity.initLanguage();
                Log.i("LanguageSettingSave","Save Button: 言語設定を保存しました");

            }


        }

    }
}
