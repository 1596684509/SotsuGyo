package com.example.sotsugyou.Listener.EventClick;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.example.sotsugyou.Activity.Fragment.SettingFragment;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.Utils.ServerConncetHandler;

import java.util.logging.SocketHandler;

public class UpdataListButtonClickImp implements View.OnClickListener{

    private SettingFragment context;

    public UpdataListButtonClickImp(SettingFragment context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        Log.i("UpdataListButtonClick", "onClick: updata clicked");

        User user = MainActivity.getApp().getUser();

        if(!user.isDefaultUser()) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    ServerConncetHandler serverConncetHandler = MainActivity.getApp().getServerConncetHandler();

                    if(serverConncetHandler.connect()) {

                        serverConncetHandler.sendMsg("this is client");
                        serverConncetHandler.disConnect();

                    }
                }
            }).start();

        }

    }
}
