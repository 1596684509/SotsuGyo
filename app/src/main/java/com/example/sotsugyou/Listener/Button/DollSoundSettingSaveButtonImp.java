package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.view.View;

import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollSoundSetting;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.Enum.SoundIdEnum;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;

public class DollSoundSettingSaveButtonImp implements View.OnClickListener{

    private Context context;

    public DollSoundSettingSaveButtonImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        if(context instanceof DollSoundSetting) {

            DollSoundSetting activity = (DollSoundSetting) context;

            if(activity.getSoundType() != null) {

                MainActivity.getApp().getUser().getDoll().setSoundType(activity.getSoundType());
                MainActivity.getApp().getSoundPlay().setSound();
                DataHandler dataHandler = AppObject.getData();
                dataHandler.save();
                activity.finish();

            }


        }

    }
}
