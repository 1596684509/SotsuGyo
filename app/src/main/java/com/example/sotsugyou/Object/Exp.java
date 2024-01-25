package com.example.sotsugyou.Object;

import android.util.Log;

import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.databinding.FragmentMainBinding;

public class Exp {

    private int level;
    public static final int MAXLEAVE = 100;

    private int exp;
    private int nextLeaveExp;

    public void initExp(int level, int exp) {

        this.exp = exp;
        this.level = level;
        updataNextExp();
        updataExpbar();

    }

    public void addExp() {

        if(level >= MAXLEAVE) {

            return;

        }

        int addExp = 5 * level;

        this.exp += addExp;
        upLeave();
        updataExpbar();
        DataHandler dataHandler = AppObject.getData();
        dataHandler.save();

    }

    private void upLeave() {

        while (exp >= nextLeaveExp && level != MAXLEAVE) {

            exp -= nextLeaveExp;
            level++;
            updataNextExp();

            if(level == MAXLEAVE) {

                exp = 0;
                nextLeaveExp = 0;

            }

        }

    }

    public void setLevel(int level) {
        this.level = level;
        updataNextExp();
        updataExpbar();
    }

    public void setExp(int exp) {
        this.exp = exp;
        updataNextExp();
        updataExpbar();
    }

    private void updataNextExp() {

        nextLeaveExp = 5*(level * level + 5 * level);

    }

    public void updataExpbar() {

        MainFragment mainFragment = MainActivity.getMainFragment();

        if(mainFragment != null) {

            FragmentMainBinding bind = mainFragment.getBinding();

            if(bind != null) {

                mainFragment.getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        bind.exptext.setText("EXP " + exp + "/" + nextLeaveExp);
                        bind.leavetext.setText("Lv " + level);
                        int progressBarNum = (int) ((int) ((double)exp) / ((double)nextLeaveExp) * 100);
                        Log.i("Exp", "updataExpBar, progressBarNum=" + progressBarNum);
                        bind.mainExpbar.setProgress(progressBarNum);
                    }
                });

            }

        }

    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }
}
