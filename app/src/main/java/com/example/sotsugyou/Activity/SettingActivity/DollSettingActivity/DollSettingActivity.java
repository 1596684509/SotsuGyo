package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.sotsugyou.Activity.View.ListViewAdapter;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.Listener.EventClick.ReturnButtonOnClickImp;
import com.example.sotsugyou.Listener.SettingListViewOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;

import java.util.ArrayList;
import java.util.List;

public class DollSettingActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private List<ListViewItem> listViewItems;
    private User user;

    private ImageButton returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_setting);

        user = MainActivity.getApp().getUser();
        initListData();
        findView();
        initView();

    }

    private void initListData() {

        listViewItems = new ArrayList<>();
        listViewItems.add(new ListViewItem("オリジナル設定", user.getDoll().getBitmap(), DollIconSettingActivity.class));
        listViewItems.add(new ListViewItem("名前設定", R.drawable.system_user_icon, DollNameSettingActivity.class));
        listViewItems.add(new ListViewItem("声設定", R.drawable.soundicon));

    }
    private void findView() {

        listView = findViewById(R.id.doll_setting_listView);
        returnButton = findViewById(R.id.dollSettingMain_ImageButton_return);

    }

    private void initView() {

        adapter = new ListViewAdapter(listViewItems, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new SettingListViewOnClickImp(this));

        returnButton.setOnClickListener(new ReturnButtonOnClickImp(this));

    }
}