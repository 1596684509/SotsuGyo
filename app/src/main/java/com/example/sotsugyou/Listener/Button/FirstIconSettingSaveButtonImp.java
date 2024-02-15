package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.sotsugyou.Activity.First.FirstDollImageSettingActivity;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Object.Exp;

public class FirstIconSettingSaveButtonImp implements View.OnClickListener {

    private Context context;

    public FirstIconSettingSaveButtonImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        if(context instanceof FirstDollImageSettingActivity) {

            FirstDollImageSettingActivity activity = (FirstDollImageSettingActivity) context;

            if(activity.getRotatedBitmap() == null) {

                return;

            }

            MainActivity.getApp().getUser().getDoll().setBitmap(activity.getRotatedBitmap());
            Exp exp = new Exp();
            exp.initExp(100, 0);
            MainActivity.getApp().getUser().getDoll().setExp(exp);

            DataHandler dataHandler = AppObject.getData();
            dataHandler.save();

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        }

    }

}
