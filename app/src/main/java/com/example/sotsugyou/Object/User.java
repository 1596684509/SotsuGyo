package com.example.sotsugyou.Object;

public class User {

    private String name;
    private boolean defaultUser = false;
    private String id;
    private Doll doll;
    //TODO データベースに移動
    private int iconId = -1;

    public void initUser(String id, String name, int iconId) {

        this.id = id;
        this.name = name;
        this.iconId = iconId;

    }

    public void initDoll(String name) {

        if(name == null) {

            return;

        }

        doll = new Doll();
        doll.initDoll(null, name);

    }

    public void setId(String id) {
        this.id = id;
    }

    public void initDoll(Doll doll) {

        if(doll != null) {

            this.doll = doll;

        }

    }

    public Doll getDoll() {
        return doll;
    }

    public void setDefaultUser() {

        defaultUser = true;

    }

    public void setLoginUser() {

        defaultUser = false;

    }

    public boolean isDefaultUser() {
        return defaultUser;
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

    public String getId() {
        return id;
    }
}
