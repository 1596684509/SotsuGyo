package com.example.sotsugyou.Utils;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;

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

    public static boolean loginUserFromJson(JSONObject jsonObject) {

        boolean isLogined = false;

        try {

            User user = MainActivity.getApp().getUser();
            user.setId(jsonObject.getString("userid"));
            user.setName(jsonObject.getString("name"));
            user.setLoginUser();

            if(jsonObject.getInt("iconid") == -1) {

               user.setIconId(R.drawable.defaultusericon);

            }else {

                user.setIconId(jsonObject.getInt("iconid"));

            }

            isLogined = true;

        } catch (JSONException e) {

        }

        return isLogined;

    }

}
