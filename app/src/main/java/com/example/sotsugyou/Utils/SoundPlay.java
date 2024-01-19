package com.example.sotsugyou.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.sotsugyou.Define;
import com.example.sotsugyou.Enum.SoundIdEnum;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SoundPlay {

    private Context context;
    private SoundIdEnum soundtype;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    private MediaPlayer mediaPlayer;

    private int a1Sensitivity = 10;
    private double a2Sensitivity = 40.0;
    private int a3Sensitivity = 40;

    public SoundPlay(Context context) {
        this.context = context;
    }

    public void setSound() {

        Doll doll = MainActivity.getApp().getUser().getDoll();

        if(doll != null) {

            this.soundtype = SoundIdEnum.getSound(doll.getSoundType());

        }else {

            this.soundtype = SoundIdEnum.CAT;

        }

    }

    public void playSound(int actionId) {
        if (soundtype.getID(actionId) != -1) {
            stopSound(mediaPlayer);

            mediaPlayer = MediaPlayer.create(context, soundtype.getID(actionId));
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                }
            });

            mediaPlayer.start();
        }
    }

    private void stopSound(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void play(String strJson) {


        try {
            JSONObject jsonObject = new JSONObject(strJson);

            int distance = jsonObject.getInt("distance");
//            int vibration = jsonObject.getInt("vibration");
            double acceleration = jsonObject.getDouble("accelerationfromup");

            if(distance < a3Sensitivity) {

                playSound(Define.ACCTIONTYPE_CODE3);

            }else if(acceleration > a2Sensitivity) {

                playSound(Define.ACCTIONTYPE_CODE2);

            }

        } catch (JSONException e) {

            e.printStackTrace();

        }


    }
}
