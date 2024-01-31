package com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.sotsugyou.Activity.View.ListViewAdapter;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.Listener.Button.LogOutButtonImp;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.EventClick.UpdataButtonClickImp;
import com.example.sotsugyou.Listener.SettingListViewOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityAccountSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccountSettingActivity extends AppCompatActivity {

    private ActivityAccountSettingBinding binding;

    private ListViewAdapter adapter;
    private List<ListViewItem> listViewItems;
    private User user;

    private LanguageHandler languageHandler;
    private JSONObject jsonObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAccountSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        user = MainActivity.getApp().getUser();
        initObj();
        initListData();
        initView();
        initLanguage();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initUserIcon();
    }

    private void initUserIcon() {

        if(user.getIconId() != -1) {

            listViewItems.get(0).setImageId(user.getIconId());
            binding.accountSettingListView.setAdapter(adapter);

        }

    }

    private void initObj() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }

    private void initListData() {



        try {

            listViewItems = new ArrayList<>();
            listViewItems.add(new ListViewItem(jsonObject.getString("mainsetting_listitem2_title"), R.drawable.updataicon, new UpdataButtonClickImp(this)));
            listViewItems.add(new ListViewItem(jsonObject.getString("accountsetting_listitem1_title"), user.getIconId(), AccountIconSettingActivity.class));
            listViewItems.add(new ListViewItem(jsonObject.getString("accountsetting_listitem2_title"), R.drawable.system_user_icon, AccountNameSettingActivity.class));
            listViewItems.add(new ListViewItem(jsonObject.getString("accountsetting_listitem3_title"), R.drawable.system_password_icon, AccountPasswordSettingActivity.class));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void initView() {

        adapter = new ListViewAdapter(listViewItems, this);

        binding.accountSettingListView.setAdapter(adapter);
        binding.accountSettingListView.setOnItemClickListener(new SettingListViewOnClickImp(this));

        binding.settingUserMainBackImageButton.setOnClickListener(new ReturnButtonOnClickImp(this));
        binding.accountSettingLigoutButton.setOnClickListener(new LogOutButtonImp(this));



    }

    private void initLanguage() {

        try {

            binding.accountSettingTitle.setText(jsonObject.getString("accountsetting_title"));
            binding.accountSettingLigoutButton.setText(jsonObject.getString("accountsetting_logoutbutton_text"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

}