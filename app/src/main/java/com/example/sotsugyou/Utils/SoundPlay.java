package com.example.sotsugyou.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.sotsugyou.Define;
import com.example.sotsugyou.Enum.SoundIdEnum;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SoundPlay {

    private Context context;
    private SoundIdEnum soundtype;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;

    private int a1Sensitivity = 10;
    private double a2Sensitivity = 40.0;
    private int a3Sensitivity = 40;

    public SoundPlay(Context context) {
        this.context = context;
    }

    public void setSound(SoundIdEnum soundIdEnum) {

        this.soundtype = soundIdEnum;
        mediaPlayer1 = MediaPlayer.create(context, soundtype.getID(Define.ACCTIONTYPE_CODE1));
        mediaPlayer2 = MediaPlayer.create(context, soundtype.getID(Define.ACCTIONTYPE_CODE2));
        mediaPlayer3 = MediaPlayer.create(context, soundtype.getID(Define.ACCTIONTYPE_CODE3));


    }

    public void play(String strJson) {


        try {
            JSONObject jsonObject = new JSONObject(strJson);

            int distance = jsonObject.getInt("distance");
//            int vibration = jsonObject.getInt("vibration");
            double acceleration = jsonObject.getDouble("accelerationfromup");

            if(distance < a3Sensitivity) {

                play3();

            }else if(acceleration > a2Sensitivity) {

                play2();

            }

        } catch (JSONException e) {
        }


    }

    public void play1() {

        stopSound(mediaPlayer2, mediaPlayer3);

        if(!mediaPlayer1.isPlaying()) {

            mediaPlayer1.start();

        }

    }

    public void play2() {

       stopSound(mediaPlayer1, mediaPlayer3);

        if(!mediaPlayer2.isPlaying()) {

            mediaPlayer2.start();

        }

    }

    public void play3() {

        stopSound(mediaPlayer2, mediaPlayer1);

        if(!mediaPlayer3.isPlaying()) {

            mediaPlayer3.start();

        }

    }

    public void stopSound(MediaPlayer... mediaPlayers) {

        for (MediaPlayer mediaPlayer : mediaPlayers) {

            if(mediaPlayer.isPlaying()) {

                mediaPlayer.stop();

            }

        }

    }
}
