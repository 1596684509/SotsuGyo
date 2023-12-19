package com.example.sotsugyou.Setting;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;

import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.JsonHandler;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class LanguageHandler {

    private String jsonData;
    private Context context;

    private HashMap<String, Integer> languagehsm;

    private LanguageType languageType;
    public LanguageHandler(Context context) {

        this.context = context;
        initLanguageData();

    }

    public void setLanguageType(String str) {

        if(str == null) {

            Log.e("LanguageHandler", "setLanguage: str is null");
            return;

        }

        languageType = LanguageType.getType(str);

    }

    public void setLanguageType(LanguageType languageType) {

        this.languageType = languageType;

    }

    private void initLanguageData() {

        languagehsm = new HashMap<>();
        languagehsm.put("jp", R.raw.language_jp);
        languagehsm.put("en", R.raw.language_en);
        languagehsm.put("cn", R.raw.language_cn);

    }

    public void loadJsonFile() {

        StringBuilder stringBuilder = new StringBuilder();

        try {

            InputStream inputStream = context.getResources().openRawResource(languagehsm.get(languageType.type));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {

                stringBuilder.append(line);

            }

            jsonData = stringBuilder.toString();

            inputStream.close();
            reader.close();

            System.out.println(jsonData);


        } catch (IOException e) {

            Log.i("LanguageHandler", "loadJsonFile: File load error");

        }

    }

    public JSONObject getLanguageJson() {

        return JsonHandler.getJsonObj(jsonData);

    }

    public String getJsonData() {
        return jsonData;
    }
}
