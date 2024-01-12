package com.example.sotsugyou.Listener.EventClick;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sotsugyou.Activity.LoginActivity.LoginActivity;
import com.example.sotsugyou.Activity.LoginActivity.RegisterActivity;
import com.example.sotsugyou.Enum.SendDataTypeEnum;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.JsonHandler;
import com.example.sotsugyou.Utils.ServerConncetHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityLoginBinding;
import com.example.sotsugyou.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;


public class UpdataButtonClickImp implements View.OnClickListener{

    private Context context;


    public UpdataButtonClickImp(Context context) {

        this.context = context;

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.register_regButton) {

            if (sendData(SendDataTypeEnum.USERREGISTER)) {

                if (context instanceof RegisterActivity) {

                    RegisterActivity activity = (RegisterActivity) context;
                    activity.finish();

                }

            }

        }else if(v.getId() == R.id.login_loginButton) {

            sendData(SendDataTypeEnum.USERLOGIN);

        }else {

            User user = MainActivity.getApp().getUser();

            if(!user.isDefaultUser()) {

                sendData(SendDataTypeEnum.DOLLUP);

            }

        }



    }

    private void login(String msgFromServer) {

        if(msgFromServer == null) {

            Toast.makeText(context, "IDまたはパスワードは間違った", Toast.LENGTH_LONG).show();

        }else {

            JSONObject jsonObject = JsonHandler.getJsonObj(msgFromServer);

            if(jsonObject != null) {

                if(!JsonHandler.loginUserFromJson(jsonObject)) {

                    Log.i("updataButtonClickImp", "login: login error");

                }

                ((LoginActivity) context).finish();

            }

        }

    }

    private String loginDataToJsonString() {

        String str = null;
        JSONObject jsonObject;
        LoginActivity activity = (LoginActivity) context;
        ActivityLoginBinding binding = activity.getBinding();
        String id = binding.idInputbar.getText().toString().trim();
        String password = binding.passwordInputbar.getText().toString().trim();

        if("".equals(id) || "".equals(password)) {

            Toast.makeText(context, "IDとパスワードを入力してください", Toast.LENGTH_LONG).show();

        }else {

            try {

                jsonObject = new JSONObject();
                jsonObject.put("datatype", SendDataTypeEnum.DATATYPE_USERLOGIN_CODE);
                jsonObject.put("userid", id);
                jsonObject.put("password", password);
                str = jsonObject.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return str;

    }

    private String userRegisterToJsonString() {

        JSONObject jsonObject = new JSONObject();

        if(context instanceof RegisterActivity) {

            RegisterActivity activity = (RegisterActivity) context;
            ActivityRegisterBinding binding = activity.getBinding();

            String id = binding.userInputBar.getText().toString().trim();
            String name = binding.nameInputBar.getText().toString().trim();
            String password = binding.passwordInputbar.getText().toString().trim();
            int iconId = -1;


            if("".equals(id) ||
               "".equals(name) ||
               "".equals(password) ||
               "".equals(binding.passwordInputbarAgain.getText().toString().trim()) ||
               !binding.checkbox.isChecked()) {

                Toast.makeText(context, "データを入力していませんまた利用規約を同意していません", Toast.LENGTH_LONG).show();
                return null;

            }else {

                if(password.equals(binding.passwordInputbarAgain.getText().toString().trim())) {

                    try {

                        jsonObject.put("datatype", SendDataTypeEnum.USERREGISTER);
                        jsonObject.put("userid", id);
                        jsonObject.put("password", password);
                        jsonObject.put("iconid", iconId);
                        jsonObject.put("name", name);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {

                    Toast.makeText(context, "二つパスワードは違い", Toast.LENGTH_LONG).show();
                    return null;

                }

            }

        }

        return jsonObject.toString();

    }

    private String dollDataToJsonString() {

        User user = MainActivity.getApp().getUser();
        Doll doll = user.getDoll();
        JSONObject jsonObject = new JSONObject();

        if(!user.isDefaultUser()) {


            try {

                jsonObject.put("datatype", SendDataTypeEnum.DATATYPE_DOLLUP_CODE);
                jsonObject.put("userid", user.getId());
                jsonObject.put("dollname", doll.getName());
                jsonObject.put("dollexp", doll.getExp().getExp());
                jsonObject.put("dolllevel", doll.getExp().getLeave());
                jsonObject.put("dollframeid", doll.getFrameId());
                jsonObject.put("dollbackgroundid", doll.getBackgroundId());
                jsonObject.put("dollimage", Util.getImageByte(doll.getBitmap()));



            } catch (JSONException e) {

                Log.i("UpdataListButtonClickImp", "sendDollData: data to Json error");

            }


        }

        Log.i("UpdataButtonClickImp", "toJson: doll data to json end");

        return jsonObject.toString();

    }

    private boolean sendData(SendDataTypeEnum type) {

        boolean isSended = false;
        String sendData;

        switch (type) {

            case DOLLUP:
                sendData = dollDataToJsonString();
                break;

            case USERLOGIN:
                sendData = loginDataToJsonString();
                Log.i("UpdataButtonClickImp", "send: user login data sended");
                break;

            case USERREGISTER:
                sendData = userRegisterToJsonString();
                Log.i("UpdataButtonClickImp", "send: user register data sended");
                break;

            default:
                sendData = null;

        }

        if(sendData == null) {

            Log.i("UpdataListButtonClickImp", "sendData: data is null");

        }else {


            String finalSendData = sendData;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    ServerConncetHandler serverConncetHandler = new ServerConncetHandler();
                    serverConncetHandler.connect();
                    serverConncetHandler.sendMsg(finalSendData);
                    login(serverConncetHandler.rcvMsg());
                    serverConncetHandler.disConnect();
                }
            }).start();

            isSended = true;

        }

        return isSended;

    }

}
