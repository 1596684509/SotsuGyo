package com.example.sotsugyou.Enum;

import android.util.Log;

import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.SoundPlay;

public enum SoundIdEnum {

    CAT("cat", R.raw.cat_1, R.raw.cat_2, R.raw.cat_3),
    DOG("dog", R.raw.dog_1, R.raw.dog_2, R.raw.dog_3)

    ;
    private int[] ids;
    private String type;

    SoundIdEnum(String type, int id1, int id2, int id3) {

        this.type = type;

        ids = new int[3];
        ids[0] = id1;
        ids[1] = id2;
        ids[2] = id3;
    }

    public int getID(int id) {

        try {

            return ids[id];

        }catch (IndexOutOfBoundsException e) {

            Log.i("SoundIdEnum", "action id is -1");
            return -1;

        }

    }

    public String getType() {
        return type;
    }

    public static SoundIdEnum getSound(String type) {

        for (SoundIdEnum value : values()) {

            if(value.getType().equals(type)) {

                return value;

            }

        }

        return null;

    }

}
