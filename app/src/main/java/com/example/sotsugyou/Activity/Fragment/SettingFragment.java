package com.example.sotsugyou.Activity.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.sotsugyou.Setting.LanguageHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.FragmentSettingBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;


    private LanguageHandler languageHandler;
    private JSONObject jsonObject;
    private ListViewAdapter adapter;
    private List<ListViewItem> listViewDatas;
    private User user;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSettingBinding.inflate(getLayoutInflater());
        view = binding.getRoot().getRootView();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initObject();
        initView();
        initLanguage();

    }

    @Override
    public void onResume() {
        super.onResume();

        initObject();
        initView();
        initLanguage();

    }

    private void initObject() {

        languageHandler = MainActivity.getApp().getLanguageHandler();
        jsonObject = languageHandler.getLanguageJson();

        user = MainActivity.getApp().getUser();
        initListData();

    }

    private void initLanguage() {

        try {

            binding.mainsettingTitle.setText(jsonObject.getString("main_menu2_title"));
            binding.mainsettingAccountTitle.setText(jsonObject.getString("mainsetting_accountbar_title"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }

    private void initListData() {

        try {

            listViewDatas = new ArrayList<>();

            if(user.getDoll() != null) {

                listViewDatas.add(new ListViewItem(jsonObject.getString("mainsetting_listitem1_title"), MainActivity.getApp().getUser().getDoll().getBitmap(), DollSettingActivity.class));

            }else {

                listViewDatas.add(new ListViewItem(jsonObject.getString("mainsetting_listitem1_title"), null, DollSettingActivity.class));

            }

            listViewDatas.add(new ListViewItem(jsonObject.getString("mainsetting_listitem2_title"), R.drawable.updataicon));
            listViewDatas.add(new ListViewItem(jsonObject.getString("mainsetting_listitem3_title"), R.drawable.languageicon, SystemLanguageSettingActivity.class));
            listViewDatas.add(new ListViewItem(jsonObject.getString("mainsetting_listitem4_title"), R.drawable.bluetoothicon, new Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS)));

            adapter = new ListViewAdapter(listViewDatas, binding.getRoot().getContext());

        } catch (JSONException e) {

            e.printStackTrace();

        }

    }

    public void updataImage(Bitmap bitmap) {

        listViewDatas.get(0).setBitmap(bitmap);
        binding.settingListView.setAdapter(new ListViewAdapter(listViewDatas, binding.getRoot().getContext()));

    }

    private void initView() {

        binding.settingListView.setAdapter(adapter);
        binding.settingListView.setOnItemClickListener(new SettingListViewOnClickImp(view.getContext()));

        String hint = "Var: v1.0.0\n著作権: 朱家宝所有\nCopyrightAll © 2099 syu kahou.️ Rights Reserved";
        binding.settingHintTextView.setText(hint);

        binding.settingUserIconImageView.setImageDrawable(Util.getIconRadius(getResources(), user.getIconId()));

        binding.settingUserNameTextView.setText(user.getName());

        binding.settingAccountLinearlayout.setOnClickListener(new UserSettingClickImp(view.getContext()));


    }

    public ListViewAdapter getAdapter() {
        return adapter;
    }
}