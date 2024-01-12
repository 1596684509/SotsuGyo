package com.example.sotsugyou.Listener;

import android.content.Context;

import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollIconSettingActivity;
import com.example.sotsugyou.Item.Item;
import com.example.sotsugyou.MyLinearLayout.FlowRadioGroup;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityDollIconSettingBinding;

import java.util.HashMap;

public class OnDollBackgroundCheckedChangeImp implements FlowRadioGroup.OnCheckedChangeListener{

    private Context context;

    public OnDollBackgroundCheckedChangeImp(Context context) {
        this.context = context;
    }

    @Override
    public void onCheckedChanged(FlowRadioGroup group, int checkedId) {

        if(context instanceof DollIconSettingActivity) {

            DollIconSettingActivity activity = (DollIconSettingActivity) context;
            HashMap<Integer, Item> itemHashMap = activity.getItemHashMap();

            if(itemHashMap.get(checkedId).isLocked()) {

                activity.setSelectedbackgroundItemId(itemHashMap.get(checkedId).getId());

            }


        }


    }
}
