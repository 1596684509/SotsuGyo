package com.example.sotsugyou.Listener.Button;

import android.content.Context;
import android.view.View;

import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollIconSettingActivity;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;

public class DollIconSettingSaveButtonImp implements View.OnClickListener{

    private Context context;

    public DollIconSettingSaveButtonImp(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        if(context instanceof DollIconSettingActivity) {

            DollIconSettingActivity activity = (DollIconSettingActivity) context;
            int frameId = activity.getSelectedframeItemId();
            int backgroundId = activity.getSelectedbackgroundItemId();

            if(frameId != -1) {

                MainActivity.getApp().getUser().getDoll().setFrameId(frameId);

            }

            if(backgroundId != -1) {

                MainActivity.getApp().getUser().getDoll().setBackgroundId(backgroundId);

            }

            DataHandler dataHandler = AppObject.getData();
            dataHandler.save();
            activity.finish();

        }

    }
}
