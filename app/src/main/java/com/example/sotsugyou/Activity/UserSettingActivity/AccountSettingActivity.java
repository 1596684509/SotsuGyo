package com.example.sotsugyou.Activity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sotsugyou.Activity.View.ListViewAdapter;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;

import java.util.ArrayList;
import java.util.List;

public class AccountSettingActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private List<ListViewItem> listViewItems;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        user = MainActivity.getApp().getUser();
        initListData();
        findView();
        initView();

    }

    private void initListData() {

        listViewItems = new ArrayList<>();
        listViewItems.add(new ListViewItem("アイコン設定", user.getIconId()));
        listViewItems.add(new ListViewItem("名前設定", user.getIconId()));
        listViewItems.add(new ListViewItem("ぱすわーど設定", user.getIconId()));

    }
    private void findView() {

        listView = findViewById(R.id.account_setting_listView);

    }

    private void initView() {

        listView.setAdapter(new ListViewAdapter(listViewItems, this));

    }

}