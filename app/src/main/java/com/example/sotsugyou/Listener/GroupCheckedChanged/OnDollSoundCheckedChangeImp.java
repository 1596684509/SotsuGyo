package com.example.sotsugyou.Listener.GroupCheckedChanged;

import android.content.Context;
import android.widget.ImageView;

import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollSoundSetting;
import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountIconSettingActivity;
import com.example.sotsugyou.MyLinearLayout.FlowRadioGroup;
import com.example.sotsugyou.Utils.Util;

import java.util.HashMap;

public class OnDollSoundCheckedChangeImp implements FlowRadioGroup.OnCheckedChangeListener {

    private Context context;

    public OnDollSoundCheckedChangeImp(Context context) {
        this.context = context;
    }

    @Override
    public void onCheckedChanged(FlowRadioGroup radioGroup, int i) {

        if(context instanceof DollSoundSetting) {

            DollSoundSetting activity = (DollSoundSetting) context;
            activity.setSoundType(activity.getSoundTypehsm().get(i));

        }

    }


}
