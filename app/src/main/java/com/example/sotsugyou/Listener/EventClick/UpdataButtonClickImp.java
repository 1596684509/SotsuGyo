package com.example.sotsugyou.Listener.EventClick;


import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sotsugyou.Activity.LoginActivity.LoginActivity;
import com.example.sotsugyou.Activity.LoginActivity.RegisterActivity;
import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountIconSettingActivity;
import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountNameSettingActivity;
import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountPasswordSettingActivity;
import com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity.AccountSettingActivity;
import com.example.sotsugyou.Enum.SendDataTypeEnum;
import com.example.sotsugyou.Listener.ServerSendSupport;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.JsonHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityAccountNameSettingBinding;
import com.example.sotsugyou.databinding.ActivityAccountPasswordSettingBinding;
import com.example.sotsugyou.databinding.ActivityLoginBinding;
import com.example.sotsugyou.databinding.ActivityRegisterBinding;

import org.json.JSONException;
import org.json.JSONObject;


public class UpdataButtonClickImp implements View.OnClickListener{

    private Context context;
    private String newps = null;
    private String oldps = null;

    private ServerSendSupport serverSendSupport;


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

        }else if(v.getId() == R.id.account_setting_icon_saveButton) {

            updataIconid();

        }else if(v.getId() == R.id.account_setting_name_saveButton) {

            updataUserName();

        }else if(v.getId() == R.id.account_setting_password_saveButton) {


            updataPassword();

        }else if(v.getContext() instanceof AccountSettingActivity) {


            dollDataDown();

        }else {

            User user = MainActivity.getApp().getUser();

            if(!user.isDefaultUser()) {

                sendData(SendDataTypeEnum.DOLLUP);

            }else {

                AppObject.getData().save();
                Toast.makeText(context, "データは携帯に保存しました", Toast.LENGTH_SHORT).show();

            }

        }



    }

    private void dollDataDown() {

        sendData(SendDataTypeEnum.DOLLDOWN);

    }

    private void updataPassword() {

        if(context instanceof AccountPasswordSettingActivity) {

            AccountPasswordSettingActivity activity = (AccountPasswordSettingActivity) context;
            ActivityAccountPasswordSettingBinding binding = activity.getBinding();
            newps = binding.accountSettingNewpasswordEditText.getText().toString().trim();
            oldps = binding.accountSettingOldpasswordEditText.getText().toString().trim();

            if(newps != null && oldps != null) {

                if(!"".equals(newps) && !"".equals(oldps)) {


                    sendData(SendDataTypeEnum.USERPASSWORDUPDATA);

                }

            }

        }

    }

    private void updataUserName() {

        if(context instanceof AccountNameSettingActivity) {

            AccountNameSettingActivity activity = (AccountNameSettingActivity) context;
            ActivityAccountNameSettingBinding binding = activity.getBinding();
            String name = binding.accountSettingNameEditText.getText().toString();

            if("".equals(name.trim())) {

                return;

            }

            User user = MainActivity.getApp().getUser();
            user.setName(name);
            sendData(SendDataTypeEnum.USERNAMEUPDATA);


            activity.finish();

        }

    }

    private void updataIconid() {

        if(context instanceof AccountIconSettingActivity) {

            AccountIconSettingActivity activity = (AccountIconSettingActivity) context;

            User user = MainActivity.getApp().getUser();

            int iconId = activity.getUserIcon();

            if(iconId != -1) {

                user.setIconId(iconId);
                activity.finish();

            }


        }

        sendData(SendDataTypeEnum.USERICONUPDATA);

    }

    private String userIconToJson() {

        String str = null;

        try {

            User user = MainActivity.getApp().getUser();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("datatype", SendDataTypeEnum.USERICONUPDATA.getTypeCode());
            jsonObject.put("userid", user.getId());
            jsonObject.put("usericonid", user.getIconId());
            str = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return str;

    }

    private String userPasswordTuJson() {

        String str = null;

        try {

            User user = MainActivity.getApp().getUser();
            String id = user.getId();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("datatype", SendDataTypeEnum.USERPASSWORDUPDATA.getTypeCode());
            jsonObject.put("userid", user.getId());
            jsonObject.put("newps", newps);
            jsonObject.put("oldps", oldps);

            str = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return str;

    }

    private String userDownloadToJson() {

        String str = null;

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("datatype", SendDataTypeEnum.DATATYPE_DOLLDOWN_CODE);
            jsonObject.put("userid", MainActivity.getApp().getUser().getId());
            str = jsonObject.toString();

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return str;

    }

    private String userNameToJson() {

        String str = null;

        try {

            User user = MainActivity.getApp().getUser();

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("datatype", SendDataTypeEnum.USERNAMEUPDATA.getTypeCode());
            jsonObject.put("userid", user.getId());
            jsonObject.put("username", user.getName());
            str = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return str;

    }

    private void login(String msgFromServer) {

        if(msgFromServer == null) {

            return;

        }

        JSONObject jsonObject = JsonHandler.getJsonObj(msgFromServer);

        if(context instanceof LoginActivity) {

            LoginActivity activity = (LoginActivity) context;
            if(msgFromServer.equals("")) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "IDまたはパスワードは間違った", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        }

        if(jsonObject != null) {

            if(!JsonHandler.loginUserFromJson(jsonObject)) {

                Log.i("updataButtonClickImp", "login: login error");

            }

            ((LoginActivity) context).finish();

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

                        jsonObject.put("datatype", SendDataTypeEnum.USERREGISTER.getTypeCode());
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
                jsonObject.put("dolllevel", doll.getExp().getLevel());
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
                break;

            case USERREGISTER:
                sendData = userRegisterToJsonString();
                break;

            case USERNAMEUPDATA:
                sendData = userNameToJson();
                break;

            case USERICONUPDATA:
                sendData = userIconToJson();
                break;

            case USERPASSWORDUPDATA:
                sendData = userPasswordTuJson();
                break;

            case DOLLDOWN:
                sendData = userDownloadToJson();
                break;


            default:
                sendData = null;

        }

        if(sendData == null) {

            Log.i("UpdataListButtonClickImp", "sendData: data is null");

        }else {


            serverSendSupport = new ServerSendSupport();
            serverSendSupport.setSendMsg(sendData);
            serverSendSupport.start();
            try {
                serverSendSupport.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            isSended = true;

        }

        if(serverSendSupport != null) {

            switch (type) {

                case USERLOGIN:
                    login(serverSendSupport.getMsgFromServer());
                    break;

                case USERREGISTER:
                    if(serverSendSupport.isOver()) {

                        Toast.makeText(context, "ユーザーは登録しました", Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(context, "Error: Register", Toast.LENGTH_SHORT).show();

                    }
                    break;

                case DOLLUP:
                    if(serverSendSupport.isOver()) {

                        Toast.makeText(context, "ぬいぐるみはサーバ保存しました", Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(context, "ぬいぐるみはサーバ保存に失敗", Toast.LENGTH_SHORT).show();

                    }
                    break;

                case USERPASSWORDUPDATA:
                    Toast.makeText(context, serverSendSupport.getMsgFromServer(), Toast.LENGTH_SHORT).show();
                    if(context instanceof AccountPasswordSettingActivity) {

                        ((AccountPasswordSettingActivity)context).finish();

                    }
                    break;

                case DOLLDOWN:

                    if(!isSended) {

                        break;

                    }

                    if(!"error".equals(serverSendSupport.getMsgFromServer())) {

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Doll doll = JsonHandler.jsonToDoll(serverSendSupport.getMsgFromServer());

                                if(doll != null) {

                                    MainActivity.getApp().getUser().setDoll(doll);
                                    AppObject.getData().save();
                                    ((AccountSettingActivity) context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "ぬいぐるみは更新すみ", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }else {

                                    ((AccountSettingActivity) context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "通信エラー", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                            }
                        }).start();

                    }
            }

        }

        return isSended;

    }

}
