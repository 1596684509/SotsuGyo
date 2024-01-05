package com.example.sotsugyou.Object;

import android.graphics.Bitmap;

public class Doll {

    private Bitmap bitmap;
    private String name;
    private Exp exp;

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

    public Exp getExp() {
        return exp;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }
}
