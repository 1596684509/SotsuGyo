package com.example.sotsugyou.Listener.GroupCheckedChanged;

import android.content.Context;

import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollIconSettingActivity;
import com.example.sotsugyou.Item.Item;
import com.example.sotsugyou.MyLinearLayout.FlowRadioGroup;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityDollIconSettingBinding;

import java.util.HashMap;

public class OnDollFrameCheckedChangeImp implements FlowRadioGroup.OnCheckedChangeListener{

    private Context context;

    public OnDollFrameCheckedChangeImp(Context context) {
        this.context = context;
    }

    @Override
    public void onCheckedChanged(FlowRadioGroup group, int checkedId) {

        if(context instanceof DollIconSettingActivity) {

            DollIconSettingActivity activity = (DollIconSettingActivity) context;
            HashMap<Integer, Item> itemHashMap = activity.getItemHashMap();

            if(itemHashMap.get(checkedId).isLocked()) {

                ActivityDollIconSettingBinding binding = activity.getBinding();
                binding.frame.setImageDrawable(Util.getIconRadius(context.getResources(), itemHashMap.get(checkedId).getId()));
                activity.setSelectedframeItemId(itemHashMap.get(checkedId).getId());

            }


        }


    }
}
