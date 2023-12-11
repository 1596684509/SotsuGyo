package com.example.sotsugyou.Object;

import android.graphics.Bitmap;

public class Doll {

    //TODO 自分の写真をアップデート
    private Bitmap bitmap;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void initDoll(Bitmap bitmap, String name) {

        this.name = name;
        this.bitmap = bitmap;

    }

}
