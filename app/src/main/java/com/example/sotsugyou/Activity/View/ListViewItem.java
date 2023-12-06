package com.example.sotsugyou.Activity.View;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewItem {

    private String title;
    private int ImageId;

    private Class targetActivity;

    public ListViewItem(String title, int imageId) {
        this.title = title;
        ImageId = imageId;
    }

    public ListViewItem(String title, int imageId, Class targetActivity) {
        this.title = title;
        ImageId = imageId;
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

    public void setImageId(int imageId) {
        ImageId = imageId;
    }
}
