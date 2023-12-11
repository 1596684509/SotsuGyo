package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.sotsugyou.Activity.First.FirstDollImageSettingActivity;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;

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

            DataHandler dataHandler = AppObject.getData();
            dataHandler.save(context);

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        }

    }

}
