package com.example.sotsugyou.Handler;

import android.content.Context;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.MyListener.Messageable;
import com.example.sotsugyou.Utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kotlin.jvm.internal.MagicApiIntrinsics;

public class MessageHandler implements Runnable{

    private List<String> messagelist;
    private final int delay = 20000;
    private Messageable messageable;


    public MessageHandler() {

        initMessage();

    }

    private void initMessage() {

        messagelist = new ArrayList<>();
        JSONObject jsonObject = MainActivity.getApp().getLanguageHandler().getLanguageJson();

        try {

            messagelist.add(jsonObject.getString("message1"));
            messagelist.add(jsonObject.getString("message2"));
            messagelist.add(jsonObject.getString("message3"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void changeMessage() {

        Random random = new Random();
        int index = random.nextInt(messagelist.size());
        onMessageChange(messagelist.get(index));

    }


    public void regiserMessageEvent(Messageable messageable) {

        this.messageable = messageable;

    }

    private void onMessageChange(String s) {

        if(messageable != null) {

            messageable.onMessageChenge(s);

        }

    }

    @Override
    public void run() {

        while (true) {

            changeMessage();
            Util.delay(delay);

        }

    }

}
