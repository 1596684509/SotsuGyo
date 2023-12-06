package com.example.sotsugyou.Activity.SettingActivity.UserSettingActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.sotsugyou.Activity.View.ListViewAdapter;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.Listener.SettingListViewOnClickImp;
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
        listViewItems.add(new ListViewItem("アイコン設定", user.getIconId(), AccountIconSettingActivity.class));
        listViewItems.add(new ListViewItem("名前設定", R.drawable.system_user_icon, AccountNameSettingActivity.class));
        listViewItems.add(new ListViewItem("ぱすわーど設定", R.drawable.system_password_icon, AccountPasswordSettingActivity.class));

    }
    private void findView() {

        listView = findViewById(R.id.account_setting_listView);

    }

    private void initView() {

        listView.setAdapter(new ListViewAdapter(listViewItems, this));
        listView.setOnItemClickListener(new SettingListViewOnClickImp(this));

    }

}