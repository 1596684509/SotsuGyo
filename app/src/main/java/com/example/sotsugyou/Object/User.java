package com.example.sotsugyou.Object;

public class User {

    private String name;
    private int iconId;

    public void initUser(String name, int iconId) {

        this.name = name;
        this.iconId = iconId;

    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getIconId() {
        return iconId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
