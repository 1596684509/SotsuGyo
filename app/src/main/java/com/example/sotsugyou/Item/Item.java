package com.example.sotsugyou.Item;

public class Item {

    private boolean isLocked = false;
    private int id;

    private int lockLevel;

    public Item(int id, int lockLevel) {
        this.id = id;
        this.lockLevel = lockLevel;
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
