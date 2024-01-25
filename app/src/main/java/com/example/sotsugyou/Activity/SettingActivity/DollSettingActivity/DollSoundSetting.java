package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sotsugyou.Enum.SoundIdEnum;
import com.example.sotsugyou.Listener.Button.DollSoundSettingSaveButtonImp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.GroupCheckedChanged.OnDollSoundCheckedChangeImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Utils.JsonHandler;
import com.example.sotsugyou.Utils.SoundPlay;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityDollSoundSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DollSoundSetting extends AppCompatActivity {

    private ActivityDollSoundSettingBinding binding;
    private String soundType;

    private HashMap<Integer, String> soundTypehsm = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDollSoundSettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        setLanguage();
        initView();

    }

    private void initView() {

        binding.flowradioGroup.setOnCheckedChangeListener(new OnDollSoundCheckedChangeImp(this));
        binding.backbutton.setOnClickListener(new ReturnButtonOnClickImp(this));
        binding.savebutton.setOnClickListener(new DollSoundSettingSaveButtonImp(this));

        setIcon();

    }

    private void initData() {

        soundTypehsm.put(binding.rb1.getId(), SoundIdEnum.CAT.getType());
        soundTypehsm.put(binding.rb2.getId(), SoundIdEnum.DOG.getType());

    }

    private void setLanguage() {

        LanguageHandler languageHandler = MainActivity.getApp().getLanguageHandler();

        JSONObject jsonObject = languageHandler.getLanguageJson();

        try {

            binding.title.setText(jsonObject.getString("dollsoundsetting_title"));
            binding.savebutton.setText(jsonObject.getString("save_button"));

        } catch (JSONException e) {

            throw new RuntimeException(e);

        }


    }

    private void setIcon() {

        binding.rb1.setBackground(Util.getIconRadius(getResources(), R.drawable.cat_icon));
        binding.rb2.setBackground(Util.getIconRadius(getResources(), R.drawable.dog_icon));

    }

    public HashMap<Integer, String> getSoundTypehsm() {
        return soundTypehsm;
    }

    public void setSoundType(String soundType) {
        this.soundType = soundType;
    }

    public String getSoundType() {
        return soundType;
    }

    public ActivityDollSoundSettingBinding getBinding() {
        return binding;
    }
}