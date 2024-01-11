package com.example.sotsugyou.Listener.EventClick;

import android.util.Log;
import android.view.View;

import com.example.sotsugyou.Activity.Fragment.SettingFragment;
import com.example.sotsugyou.Enum.SendDataTypeEnum;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.Utils.ServerConncetHandler;
import com.example.sotsugyou.Utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdataButtonClickImp implements View.OnClickListener{

    private SettingFragment context;
    private SendDataTypeEnum dataType;


    public UpdataButtonClickImp(SettingFragment context, SendDataTypeEnum dataType) {

        this.context = context;
        this.dataType = dataType;

    }

    @Override
    public void onClick(View v) {

        Log.i("UpdataListButtonClick", "onClick: updata clicked");

        User user = MainActivity.getApp().getUser();

        if(!user.isDefaultUser()) {

            sendData(SendDataTypeEnum.DOLL);

        }

    }

    private String userRegisterToJsonString(SendDataTypeEnum type) {

        User user = MainActivity.getApp().getUser();
        return null;

    }

    private String dollDataToJsonString(SendDataTypeEnum type) {

        User user = MainActivity.getApp().getUser();
        Doll doll = user.getDoll();
        JSONObject jsonObject = new JSONObject();

        if(!user.isDefaultUser()) {


            try {

                jsonObject.put("datatype", type.getTypeCode());
                jsonObject.put("userid", user.getId());
                jsonObject.put("dollname", doll.getName());
                jsonObject.put("dolllevel", doll.getExp().getLeave());
                jsonObject.put("dollexp", doll.getExp().getExp());
                jsonObject.put("dollimage", Util.getImageByte(doll.getBitmap()));



            } catch (JSONException e) {

                Log.i("UpdataListButtonClickImp", "sendDollData: data to Json error");

            }


        }

        Log.i("UpdataButtonClickImp", "toJson: doll data to json end");

        return jsonObject.toString();

    }

    private void sendData(SendDataTypeEnum type) {

        String sendData;

        switch (type) {

            case DOLL:
                sendData = dollDataToJsonString(SendDataTypeEnum.DOLL);
                break;

            case USERLOGIN:
                sendData = dollDataToJsonString(SendDataTypeEnum.USERLOGIN);
                Log.i("UpdataButtonClickImp", "send: user login data sended");
                break;

            case USERREGISTER:
                sendData = dollDataToJsonString(SendDataTypeEnum.USERREGISTER);
                Log.i("UpdataButtonClickImp", "send: user register data sended");
                break;

            default:
                sendData = null;

        }

        if(sendData == null) {

            Log.i("UpdataListButtonClickImp", "sendData: data is null");

        }else {


            new Thread(new Runnable() {
                @Override
                public void run() {

                    ServerConncetHandler serverConncetHandler = new ServerConncetHandler();
                    serverConncetHandler.connect();
                    serverConncetHandler.sendMsg(sendData);
                    serverConncetHandler.disConnect();
                    Log.i("UpdataButtonClickImp", "send: doll data sended");
                }
            }).start();

        }

    }

}
