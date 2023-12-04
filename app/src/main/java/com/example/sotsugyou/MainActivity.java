package com.example.sotsugyou;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.Activity.Fragment.SettingFragment;
import com.example.sotsugyou.Listener.OnNavigationItemSenetedImp;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Utils.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static AppObject app;
    private BottomNavigationView navigationView;
    private FrameLayout frameLayout;

    private MainFragment mainFragment;
    private SettingFragment settingFragment;

    public static final int CODE_FRAGMENT_MAIN = 0;
    public static final int CODE_FRAGMENT_SETTING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app = new AppObject();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findView();
        initView();

    }

    private void findView() {

        navigationView = findViewById(R.id.main_bottomNavigation);
        frameLayout = findViewById(R.id.main_frame);

    }

    private void initView() {

        selectedFragment(CODE_FRAGMENT_MAIN);

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setIcon(Util.getIconRadius(getResources(), app.getDoll().getPhotoID()));
        navigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSenetedImp(this));

    }


    public void selectedFragment(int position) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);

        if(position == 0) {

            if(mainFragment == null) {

                mainFragment = new MainFragment();
                fragmentTransaction.add(R.id.main_frame, mainFragment);

            }else {

                fragmentTransaction.show(mainFragment);

            }

        }else if(position == 1) {

            if(settingFragment == null) {

                settingFragment = new SettingFragment();
                fragmentTransaction.add(R.id.main_frame, settingFragment);

            }else {

                fragmentTransaction.show(settingFragment);

            }

        }

        fragmentTransaction.commit();

    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {

        if(mainFragment != null) {

            fragmentTransaction.hide(mainFragment);

        }

        if(settingFragment != null) {

            fragmentTransaction.hide(settingFragment);

        }

    }

    public static AppObject getApp() {
        return app;
    }
}