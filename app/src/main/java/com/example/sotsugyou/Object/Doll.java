package com.example.sotsugyou.Object;

import android.graphics.Bitmap;

import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.Util;

public class Doll {

    private Bitmap bitmap;
    private int frameId = -1;
    private int backgroundId = -1;
    private String name;
    private Exp exp;
    private String soundType;

    public void setSoundType(String soundType) {
        this.soundType = soundType;
    }

    public String getSoundType() {
        return soundType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {

        Bitmap cropedBitmap = Util.imageCropUtil(bitmap);

        this.bitmap = cropedBitmap;
    }

    public String getName() {
        return name;
    }

    public void initDoll(Bitmap bitmap, String name) {

        this.name = name;
        this.bitmap = bitmap;

    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public void setFrameId(int frameId) {
        this.frameId = frameId;
    }

    public int getFrameId() {
        return frameId;
    }
}
