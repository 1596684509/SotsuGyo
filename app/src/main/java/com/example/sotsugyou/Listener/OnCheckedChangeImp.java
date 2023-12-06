package com.example.sotsugyou.Listener;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.annotation.DrawableRes;

import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountIconSettingActivity;
import com.example.sotsugyou.MyLinearLayout.FlowRadioGroup;
import com.example.sotsugyou.Utils.Util;

import java.util.HashMap;

public class OnCheckedChangeImp implements FlowRadioGroup.OnCheckedChangeListener {

    private Context context;

    public OnCheckedChangeImp(Context context) {
        this.context = context;
    }

//    @Override
//    public void onCheckedChanged(FlowRadioGroup radioGroup, int i) {
//
//        if(context instanceof AccountIconSettingActivity) {
//
//            AccountIconSettingActivity activity = (AccountIconSettingActivity) context;
//
//            ImageView imageView = activity.getIcon();
//
//            HashMap<Integer, Integer> imageRecourceIds = AccountIconSettingActivity.getImageResourceIDhsm();
//
//            imageView.setImageDrawable(Util.getIconRadius(context.getResources(), imageRecourceIds.get(i)));
//
//        }
//
//    }

    @Override
    public void onCheckedChanged(FlowRadioGroup radioGroup, int i) {

        if(context instanceof AccountIconSettingActivity) {

            AccountIconSettingActivity activity = (AccountIconSettingActivity) context;

            ImageView imageView = activity.getIcon();
            imageView.setImageDrawable(radioGroup.findViewById(i).getBackground());

        }

    }

}
