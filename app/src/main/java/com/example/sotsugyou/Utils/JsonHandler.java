package com.example.sotsugyou.Utils;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Object.Exp;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonHandler {

    public static JSONObject getJsonObj(String json) {

        try {

            if(json == null) {

                return null;

            }

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

    public static Doll jsonToDoll(String json) {

        JSONObject jsonObject = getJsonObj(json);
        Doll doll = null;

        if(jsonObject == null) {

            return null;

        }

        try {

            doll = new Doll();
            Exp exp = new Exp();
            doll.setName(jsonObject.getString("dollname"));
            exp.setExp(jsonObject.getInt("dollexp"));
            exp.setLevel(jsonObject.getInt("dolllevel"));
            doll.setFrameId(jsonObject.getInt("dollframeid"));
            doll.setBackgroundId(jsonObject.getInt("dollbackground"));
            doll.setBitmap(Util.getBitMapForBtye(jsonObject.getString("dollimage")));
            doll.setExp(exp);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return doll;

    }


}
