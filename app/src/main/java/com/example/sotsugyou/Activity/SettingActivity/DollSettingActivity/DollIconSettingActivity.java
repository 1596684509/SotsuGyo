package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sotsugyou.Item.Item;
import com.example.sotsugyou.Listener.Button.DollIconSettingSaveButtonImp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.OnDollBackgroundCheckedChangeImp;
import com.example.sotsugyou.Listener.OnDollFrameCheckedChangeImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.R;
import com.example.sotsugyou.databinding.ActivityDollIconSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DollIconSettingActivity extends AppCompatActivity {

    private ActivityDollIconSettingBinding binding;
    private HashMap<Integer, Item> itemHashMap = new HashMap<>();
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

        binding = ActivityDollIconSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

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

    }

    private void initItemData() {

        itemHashMap.put(R.id.doll_setting_icon_frameR1, new Item(R.drawable.frame1, 10));
        itemHashMap.put(R.id.doll_setting_icon_frameR2, new Item(R.drawable.frame2, 25));
        itemHashMap.put(R.id.doll_setting_icon_frameR3, new Item(R.drawable.frame3, 40));
        itemHashMap.put(R.id.doll_setting_icon_frameR4, new Item(R.drawable.frame4, 55));
        itemHashMap.put(R.id.doll_setting_icon_frameR5, new Item(R.drawable.frame5, 70));
        itemHashMap.put(R.id.doll_setting_icon_frameR6, new Item(R.drawable.frame6, 85));
        itemHashMap.put(R.id.backgroundRb1, new Item(R.drawable.background1, 15));
        itemHashMap.put(R.id.backgroundRb2, new Item(R.drawable.background2, 30));
        itemHashMap.put(R.id.backgroundRb3, new Item(R.drawable.background3, 45));
        itemHashMap.put(R.id.backgroundRb4, new Item(R.drawable.background4, 60));
        itemHashMap.put(R.id.backgroundRb5, new Item(R.drawable.background5, 75));
        itemHashMap.put(R.id.backgroundRb6, new Item(R.drawable.background6, 90));

        for (Integer integer : itemHashMap.keySet()) {

            if(doll.getExp().getLeave() >= itemHashMap.get(integer).getLockLevel()) {

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

                findViewById(integer).setBackground(Util.getIconRadius(getResources(), itemHashMap.get(integer).getId()));

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