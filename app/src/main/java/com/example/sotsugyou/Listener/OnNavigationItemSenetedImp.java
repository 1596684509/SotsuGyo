package com.example.sotsugyou.Listener;

import android.content.Context;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OnNavigationItemSenetedImp implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Context context;

    public OnNavigationItemSenetedImp(Context context) {
        this.context = context;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if(context instanceof MainActivity) {

            if(item.getItemId() == R.id.menu_main) {

                ((MainActivity) context).selectedFragment(MainActivity.CODE_FRAGMENT_MAIN);

            }else if(item.getItemId() == R.id.menu_setting) {

                ((MainActivity) context).selectedFragment(MainActivity.CODE_FRAGMENT_SETTING);

            }

        }


        return true;
    }
}
