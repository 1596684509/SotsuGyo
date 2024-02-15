package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sotsugyou.Item.Item;
import com.example.sotsugyou.Listener.Button.DollIconSettingSaveButtonImp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.GroupCheckedChanged.OnDollBackgroundCheckedChangeImp;
import com.example.sotsugyou.Listener.GroupCheckedChanged.OnDollFrameCheckedChangeImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.R;
import com.example.sotsugyou.databinding.ActivityDollIconSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;

public class DollIconSettingActivity extends AppCompatActivity {

    private ActivityDollIconSettingBinding binding;
    private static HashMap<Integer, Item> itemHashMap;
    private ImageView imageView;
    private Doll doll;

    private ImageButton imageButton;

    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    private int selectedframeItemId = -1;
    private int selectedbackgroundItemId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        watiImageLoad();
        binding = ActivityDollIconSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        initObj();
        initItemData();
        findView();
        initView();
        initLanguage();

    }
    private void initObj() {

        doll = MainActivity.getApp().getUser().getDoll();
        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();
        itemHashMap = AppObject.getItemHashMap();

    }

    private void watiImageLoad() {

        try {
            MainActivity.getApp().getImageThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void initItemData() {

        for (Integer integer : itemHashMap.keySet()) {

            if(doll.getExp().getLevel() >= itemHashMap.get(integer).getLockLevel()) {

                itemHashMap.get(integer).unlock();

            }

        }

    }

    private void initLanguage() {

        try {

            binding.title.setText(jsonObject.getString("dollpersonalitysetting_title"));
            binding.frameTitle.setText(jsonObject.getString("dollpersonalitysetting_title2"));
            binding.backgroundTitle.setText(jsonObject.getString("dollpersonalitysetting_title3"));
            binding.saveButton.setText(jsonObject.getString("save_button"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }


    private void findView() {

        imageView = findViewById(R.id.ImageViewNowIcon);

        imageButton = findViewById(R.id.dollSettingIcon_ImageButton_return);

    }

    private void initView() {

        imageView.setImageDrawable(Util.getIconRadius(getResources() ,doll.getBitmap()));
        binding.saveButton.setOnClickListener(new DollIconSettingSaveButtonImp(this));

        if(doll.getFrameId() != -1) {

            binding.frame.setImageResource(doll.getFrameId());

        }

        imageButton.setOnClickListener(new ReturnButtonOnClickImp(this));
        binding.flowradioGroup.setOnCheckedChangeListener(new OnDollFrameCheckedChangeImp(this));
        binding.backgroundGroup.setOnCheckedChangeListener(new OnDollBackgroundCheckedChangeImp(this));
        lockImage();

    }

    private void lockImage() {


        for (Integer integer : itemHashMap.keySet()) {

            if(!itemHashMap.get(integer).isLocked()) {

                findViewById(integer).setBackgroundResource(R.drawable.lockimage);

            }else {

                findViewById(integer).setBackground(Util.getIconRadius(getResources(), itemHashMap.get(integer).getBitmap()));

            }

        }

    }

    public HashMap<Integer, Item> getItemHashMap() {
        return itemHashMap;
    }

    public ActivityDollIconSettingBinding getBinding() {
        return binding;
    }

    public void setSelectedframeItemId(int selectedframeItemId) {
        this.selectedframeItemId = selectedframeItemId;
    }

    public void setSelectedbackgroundItemId(int selectedbackgroundItemId) {
        this.selectedbackgroundItemId = selectedbackgroundItemId;
    }

    public int getSelectedbackgroundItemId() {
        return selectedbackgroundItemId;
    }

    public int getSelectedframeItemId() {
        return selectedframeItemId;
    }
}