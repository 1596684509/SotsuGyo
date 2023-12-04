package com.example.sotsugyou.Object;

public class Doll {

    //TODO 自分の写真をアップデート
    private int photoID;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public int getPhotoID() {
        return photoID;
    }

    public String getName() {
        return name;
    }

    public void initDoll(int photoID, String name) {

        this.name = name;
        this.photoID = photoID;

    }
}
