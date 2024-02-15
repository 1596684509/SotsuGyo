package com.example.sotsugyou.Item;

import android.graphics.Bitmap;

public class Item {

    private boolean isLocked = false;
    private Bitmap bitmap;
    private int id;

    private int lockLevel;

    public Item(int id, Bitmap bitmap, int lockLevel) {
        this.id = id;
        this.bitmap = bitmap;
        this.lockLevel = lockLevel;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getId() {
        return id;
    }

    public int getLockLevel() {
        return lockLevel;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void unlock() {

        isLocked = true;

    }

}
