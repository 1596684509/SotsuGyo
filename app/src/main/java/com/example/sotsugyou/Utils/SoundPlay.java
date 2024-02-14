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
    private MediaPlayer mediaPlayer;

    private int a1Sensitivity = 10;
    private double ayMax = 80.0;
    private double ayMin = 30.0;
    private double axMax = 1;
    private double axMin = 0.5;
    private int a3Sensitivity = 55;
    private double azMax = 2.0;
    private double azMin = -2.0;

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

        if(soundtype == null) {

            soundtype = SoundIdEnum.CAT;

        }
        if (soundtype.getID(actionId) != -1) {
//            stopSound(mediaPlayer);

        if(mediaPlayer == null || !mediaPlayer.isPlaying()) {

            stopSound(mediaPlayer);
            mediaPlayer = MediaPlayer.create(context, soundtype.getID(actionId));

        }


            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.reset();
                }
            });

            mediaPlayer.start();

            Doll doll = MainActivity.getApp().getUser().getDoll();
            doll.getExp().addExp();

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
            double ay = jsonObject.getDouble("ay");
            double az = jsonObject.getDouble("az");
            double ax = jsonObject.getDouble("ax");

            if((az >= azMax || az <= azMin) && (ay >= ayMin && ay <= ayMax)) {

                playSound(Define.ACCTIONTYPE_CODE2);

            }else if(distance < a3Sensitivity) {

                playSound(Define.ACCTIONTYPE_CODE3);

            }

            Thread.sleep(1000l);

        } catch (JSONException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
