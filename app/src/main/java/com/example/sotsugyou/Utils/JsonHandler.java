package com.example.sotsugyou.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandler {

    public static JSONObject getJsonObj(String json) {

        try {

            JSONObject jsonObject = new JSONObject(json);
            return jsonObject;

        } catch (JSONException e) {


        }

        return null;

    }

}
