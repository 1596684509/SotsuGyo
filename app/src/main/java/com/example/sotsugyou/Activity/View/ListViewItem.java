package com.example.sotsugyou.Activity.View;

import android.content.Intent;
import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewItem {

    private String title;
    private int ImageId;
    private Bitmap bitmap;

    private Class targetActivity;
    private Intent intent;

    public ListViewItem(String title, int imageId) {
        this.title = title;
        ImageId = imageId;
    }

    public ListViewItem(String title, int imageId, Intent intent) {
        this.title = title;
        ImageId = imageId;
        this.intent = intent;
    }

    public ListViewItem(String title, int imageId, Class targetActivity) {
        this.title = title;
        ImageId = imageId;
        this.targetActivity = targetActivity;
    }

    public ListViewItem(String title, Bitmap bitmap, Class targetActivity) {
        this.title = title;
        this.bitmap = bitmap;
        this.targetActivity = targetActivity;
    }


    public void setTargetActivity(Class targetActivity) {
        this.targetActivity = targetActivity;
    }

    public Class getTargetActivity() {
        return targetActivity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return ImageId;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public Intent getIntent() {
        return intent;
    }
}
