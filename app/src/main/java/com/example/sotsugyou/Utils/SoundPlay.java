package com.example.sotsugyou.Utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.sotsugyou.Enum.SoundIdEnum;

public class SoundPlay {

    private Context context;
    private SoundIdEnum soundtype;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;

    public SoundPlay(Context context) {
        this.context = context;
    }

    public void setSound(SoundIdEnum soundIdEnum) {

        this.soundtype = soundIdEnum;
        mediaPlayer1 = MediaPlayer.create(context, soundtype.getID(SoundIdEnum.ACCTIONTYPE_CODE1));
        mediaPlayer2 = MediaPlayer.create(context, soundtype.getID(SoundIdEnum.ACCTIONTYPE_CODE2));
        mediaPlayer3 = MediaPlayer.create(context, soundtype.getID(SoundIdEnum.ACCTIONTYPE_CODE3));


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
