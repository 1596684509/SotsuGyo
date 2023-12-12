package com.example.sotsugyou.Activity.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollSettingActivity;
import com.example.sotsugyou.Activity.SettingActivity.SystemSettingActivity.SystemLanguageSettingActivity;
import com.example.sotsugyou.Activity.View.ListViewAdapter;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.Listener.Button.UserSettingClickImp;
import com.example.sotsugyou.Listener.SettingListViewOnClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.User;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.Util;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {

    private View view;
    private ListViewAdapter adapter;
    private ListView listView;
    private List<ListViewItem> listViewDatas;
    private TextView hintTextView;
    private ImageView userIcon;
    private User user;
    private TextView userNameTextView;
    private LinearLayout userLinearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_setting, container, false);
        initObject();
        findView();
        initView();
        // Inflate the layout for this fragment
        return view;
    }

    private void initObject() {

        user = MainActivity.getApp().getUser();
        initListData();

    }

    private void initListData() {

        listViewDatas = new ArrayList<>();

        if(user.getDoll() != null) {

            listViewDatas.add(new ListViewItem("ぬいぐるみ設定", MainActivity.getApp().getUser().getDoll().getBitmap(), DollSettingActivity.class));

        }else {

            listViewDatas.add(new ListViewItem("ぬいぐるみ設定", null, DollSettingActivity.class));

        }

        listViewDatas.add(new ListViewItem("データ保存", R.drawable.updataicon));
        listViewDatas.add(new ListViewItem("言語設定", R.drawable.languageicon, SystemLanguageSettingActivity.class));
        listViewDatas.add(new ListViewItem("ぬいぐるみと接続", R.drawable.bluetoothicon));

        adapter = new ListViewAdapter(listViewDatas, view.getContext());

    }

    public void updataImage(Bitmap bitmap) {

        listViewDatas.get(0).setBitmap(bitmap);
        listView.setAdapter(new ListViewAdapter(listViewDatas, view.getContext()));

    }

    private void findView() {

        listView = view.findViewById(R.id.setting_listView);
        hintTextView = view.findViewById(R.id.setting_hint_textView);
        userIcon = view.findViewById(R.id.setting_userIcon_imageView);
        userNameTextView = view.findViewById(R.id.setting_userName_textView);
        userLinearLayout = view.findViewById(R.id.setting_account_linearlayout);

    }

    private void initView() {

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new SettingListViewOnClickImp(view.getContext()));

        String hint = "Var: v1.0.0\n著作権: 朱家宝所有\nCopyrightAll © 2099 syu kahou.️ Rights Reserved";
        hintTextView.setText(hint);

        userIcon.setImageDrawable(Util.getIconRadius(getResources(), user.getIconId()));

        userNameTextView.setText(user.getName());

        userLinearLayout.setOnClickListener(new UserSettingClickImp(view.getContext()));


    }

    public ListViewAdapter getAdapter() {
        return adapter;
    }
}