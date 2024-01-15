package com.example.sotsugyou.Enum;

import com.example.sotsugyou.R;

public enum SoundIdEnum {

    CAT(R.raw.cat_1, R.raw.cat_2, R.raw.cat_3)

    ;

    //揉む
    public static final int ACCTIONTYPE_CODE1 = 0;
    //上に抛る
    public static final int ACCTIONTYPE_CODE2 = 1;
    //抱きしめる
    public static final int ACCTIONTYPE_CODE3 = 2;
    private int[] ids;

    SoundIdEnum(int id1, int id2, int id3) {
        ids = new int[3];
        ids[0] = id1;
        ids[1] = id2;
        ids[2] = id3;
    }

    public int getID(int id) {

        return ids[id];

    }
}
