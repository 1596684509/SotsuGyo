package com.example.sotsugyou;

import static com.example.sotsugyou.Handler.BluetoothHandler.REQUESTCODE_ENABLE_BLUETOOTH;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sotsugyou.Activity.First.FirstDollNameSettingActivity;
import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.Activity.Fragment.SettingFragment;
import com.example.sotsugyou.Data.Data;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.Listener.Button.MainDollImageImp;
import com.example.sotsugyou.Listener.OnNavigationItemSenetedImp;
import com.example.sotsugyou.Object.AppObject;
import com.example.sotsugyou.Utils.BluetoothConnectEventListener;
import com.example.sotsugyou.Handler.BluetoothHandler;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.ActivityMainBinding;
import com.example.sotsugyou.databinding.FragmentMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static AppObject app;
    private BottomNavigationView navigationView;
    private ActivityMainBinding bind;

    private DataHandler dataHandler;

    private static MainFragment mainFragment;
    private SettingFragment settingFragment;

    public static final int CODE_FRAGMENT_MAIN = 0;
    public static final int CODE_FRAGMENT_SETTING = 1;

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            mainFragment = getMainFragment();
            FragmentMainBinding binding = mainFragment.getBinding();

            switch (state) {

                case BluetoothAdapter.STATE_OFF:
                    binding.explanation.setText("ブルートゥースは起動していません");
                    app.getBluetoothHandler().enableBluetooth(MainActivity.this);
                    break;

                case BluetoothAdapter.STATE_ON:
                    binding.explanation.setText("ブルートゥースは起動しました");
                    break;

            }

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        initObj();

        app.getBluetoothHandler().checkPermission(this);

        if(firstLunch()) {

            return;

        }

        findView();
        initView();
        initLanguage();
        initBluetooth();


    }

    private void initBluetooth() {

        if(app == null) {

            Log.i("Main", " app is null");

        }

        if(!app.getBluetoothHandler().enableBluetooth(this)) {

            Log.e("MainActivity", "bluetooth 対応できない");

        }

        app.getBluetoothHandler().setOnConnectedEventListener(new BluetoothConnectEventListener() {
            @Override
            public void onConnected() {

                if(mainFragment != null) {

                    mainFragment.getBinding().explanation.setText("ぬいぐるみは接続すみ");

                }

            }

            @Override
            public void onDisConnected() {

                if(mainFragment.getBinding() != null) {

                    mainFragment.getBinding().explanation.setText("ぬいぐるみは切れています, ブルートゥースの接続を確認してください");

                }

            }

            @Override
            public void onConnectionRetry() {

                if(!BluetoothHandler.isDollConnected) {

                    app.getBluetoothHandler().searchBondedHardWare();

                }

            }

            @Override
            public void onDonthasDoll() {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(mainFragment.getBinding() != null) {

                            mainFragment.getBinding().explanation.setText("ぬいぐるみは見つかりません、ブルートゥースの設定を確認してください");

                        }
                        MainActivity.getApp().getBluetoothHandler().searchBondedHardWare();
                    }
                }).start();

            }
        });
        app.getBluetoothHandler().searchBondedHardWare();

    }

    private void initObj() {


        app = new AppObject(this);

        dataHandler = AppObject.getData();

        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver, filter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        initLanguage();
    }

    private void initLanguage() {

        SharedPreferences sharedPreferences = getSharedPreferences(Data.DATAFILE_NAME, MODE_PRIVATE);
        app.getLanguageHandler().setLanguageType(sharedPreferences.getString("language", "jp"));
        try {

            JSONObject jsonObject = app.getLanguageHandler().getLanguageJson();

            if(jsonObject == null) {

                return;

            }

            Menu menu = bind.mainBottomNavigation.getMenu();

            MenuItem menuItem = menu.getItem(0);
            menuItem.setTitle((CharSequence) jsonObject.get("main_menu1_title"));

            menuItem = menu.getItem(1);
            menuItem.setTitle((CharSequence) jsonObject.get("main_menu2_title"));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }



    }

    private boolean firstLunch() {

        if(dataHandler.load()) {


            Intent intent = new Intent(this, FirstDollNameSettingActivity.class);
            startActivity(intent);

            return true;

        }

        return false;

    }

    private void findView() {

        navigationView = findViewById(R.id.main_bottomNavigation);

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


        mainFragment.getBinding().mainDollImageView.setImageDrawable(Util.getIconRadius(getResources(), app.getUser().getDoll().getBitmap()));

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

        if(requestCode == REQUESTCODE_ENABLE_BLUETOOTH) {

            if(resultCode != RESULT_OK) {

                getApp().getBluetoothHandler().enableBluetooth(this);

            }

        }

        if(requestCode == MainDollImageImp.REQUESTCODE_CAMERA) {

            if(data == null) {

                return;

            }

            Bundle bundle = data.getExtras();

            if(bundle == null) {

                return;

            }

            Bitmap bitmap = (Bitmap) bundle.get("data");

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            app.getUser().getDoll().setBitmap(rotatedBitmap);

            updateImageView();

        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        app.getData().save();

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

    public static MainFragment getMainFragment() {
        return mainFragment;
    }

    public static AppObject getApp() {

        return app;
    }



}