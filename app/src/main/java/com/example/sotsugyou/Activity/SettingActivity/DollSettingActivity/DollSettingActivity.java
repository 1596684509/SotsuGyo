package com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity;

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

public class DollSettingActivity extends AppCompatActivity {

    private ListView listView;
    private ListViewAdapter adapter;
    private List<ListViewItem> listViewItems;
    private User user;

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
        listViewItems.add(new ListViewItem("オリジナル設定", user.getDoll().getPhotoID()));
        listViewItems.add(new ListViewItem("名前設定", R.drawable.system_user_icon));
        listViewItems.add(new ListViewItem("声設定", R.drawable.soundicon));

    }
    private void findView() {

        listView = findViewById(R.id.doll_setting_listView);

    }

    private void initView() {

        listView.setAdapter(new ListViewAdapter(listViewItems, this));

    }
}