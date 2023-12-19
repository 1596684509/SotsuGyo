package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.sotsugyou.Activity.View.ListViewAdapter;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.SettingListViewOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.databinding.ActivityDollSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DollSettingActivity extends AppCompatActivity {


    private ActivityDollSettingBinding binding;
    private LanguageHandler languageHandler;
    private JSONObject jsonObject;

    private ListViewAdapter adapter;
    private List<ListViewItem> listViewItems;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDollSettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        initObj();
        initListData();
        initView();
        initLanguage();

    }

    private void initObj() {

        user = MainActivity.getApp().getUser();
        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

    }

    private void initLanguage() {

        try {

            binding.dollMainsettingTitle.setText(jsonObject.getString("dollsetting_title"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void initListData() {

        try {

            listViewItems = new ArrayList<>();
            listViewItems.add(new ListViewItem(jsonObject.getString("dollsetting_listitem1_title"), user.getDoll().getBitmap(), DollIconSettingActivity.class));
            listViewItems.add(new ListViewItem(jsonObject.getString("dollsetting_listitem2_title"), R.drawable.system_user_icon, DollNameSettingActivity.class));
            listViewItems.add(new ListViewItem(jsonObject.getString("dollsetting_listitem3_title"), R.drawable.soundicon));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    private void initView() {

        adapter = new ListViewAdapter(listViewItems, this);
        binding.dollSettingListView.setAdapter(adapter);
        binding.dollSettingListView.setOnItemClickListener(new SettingListViewOnClickImp(this));

        binding.dollSettingMainImageButtonReturn.setOnClickListener(new ReturnButtonOnClickImp(this));

    }
}