package com.example.sotsugyou;

import static java.security.AccessController.getContext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.FrameLayout;

import com.example.sotsugyou.Activity.First.FirstDollImageSettingActivity;
import com.example.sotsugyou.Activity.First.FirstDollNameSettingActivity;
import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.Activity.Fragment.SettingFragment;
import com.example.sotsugyou.Activity.SettingActivity.DollSettingActivity.DollNameSettingActivity;
import com.example.sotsugyou.Activity.View.ListViewItem;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.Listener.Button.MainDollImageImp;
import com.example.sotsugyou.Listener.OnNavigationItemSenetedImp;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Utils.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.MatchResult;

public class MainActivity extends AppCompatActivity {

    private static AppObject app;
    private BottomNavigationView navigationView;
    private FrameLayout frameLayout;
    private DataHandler dataHandler;

    private MainFragment mainFragment;
    private SettingFragment settingFragment;

    public static final int CODE_FRAGMENT_MAIN = 0;
    public static final int CODE_FRAGMENT_SETTING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        app = new AppObject();
        app.initDefaultUser();
        dataHandler = AppObject.getData();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        if(firstLunch()) {

            return;

        }

        findView();
        initView();

    }

    private boolean firstLunch() {

        if(dataHandler.load(this)) {


            Intent intent = new Intent(this, FirstDollNameSettingActivity.class);
            startActivity(intent);

            return true;

        }

        return false;

    }

    private void findView() {

        navigationView = findViewById(R.id.main_bottomNavigation);
        frameLayout = findViewById(R.id.main_frame);

    }

    private void initView() {

        selectedFragment(CODE_FRAGMENT_MAIN);

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);

        Log.i("MainActivity", "initView: " + app.getUser().getDoll().getName());
        menuItem.setIcon(Util.getIconRadius(getResources(), app.getUser().getDoll().getBitmap()));
        navigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSenetedImp(this));



    }

    private void updateImageView() {


        mainFragment.getDollImageView().setImageDrawable(Util.getIconRadius(getResources(), app.getUser().getDoll().getBitmap()));

        Menu menu = navigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setIcon(Util.getIconRadius(getResources(), app.getUser().getDoll().getBitmap()));



        if(settingFragment != null) {

            settingFragment.updataImage(app.getUser().getDoll().getBitmap());

        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == MainDollImageImp.REQUESTCODE_CAMERA) {

            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            app.getUser().getDoll().setBitmap(rotatedBitmap);

            updateImageView();

        }

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