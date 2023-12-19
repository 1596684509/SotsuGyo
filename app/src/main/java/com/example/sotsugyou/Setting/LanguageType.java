package com.example.sotsugyou.Setting;

import android.util.Log;

public enum LanguageType {

    English("en"),
    Chinese("cn"),
    Japanese("jp")

    ;
    public String type;

    LanguageType(String type) {
        this.type = type;
    }

    public static LanguageType getType(String type) {

        for (LanguageType value : values()) {

            if(value.type.equals(type)) {

                return value;

            }

        }

        return null;

    }
}
