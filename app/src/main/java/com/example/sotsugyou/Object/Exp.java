package com.example.sotsugyou.Object;

import android.util.Log;
import android.widget.TextView;

import com.example.sotsugyou.Activity.Fragment.MainFragment;
import com.example.sotsugyou.Data.DataHandler;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.databinding.FragmentMainBinding;

public class Exp {

    private int leave;
    public static final int MAXLEAVE = 100;

    private int exp;
    private int nextLeaveExp;

    public void initExp(int leave, int exp) {

        this.exp = exp;
        this.leave = leave;
        updataNextExp();
        updataExpbar();

    }

    public void addExp(int exp) {

        if(leave >= MAXLEAVE) {

            return;

        }

        this.exp += exp;
        upLeave();
        updataExpbar();
        DataHandler dataHandler = AppObject.getData();
        dataHandler.save();

    }

    private void upLeave() {

        while (exp >= nextLeaveExp && leave != MAXLEAVE) {

            exp -= nextLeaveExp;
            leave++;
            updataNextExp();

            if(leave == MAXLEAVE) {

                exp = 0;
                nextLeaveExp = 0;

            }

        }

    }

    public void setLeave(int leave) {
        this.leave = leave;
        updataNextExp();
        updataExpbar();
    }

    public void setExp(int exp) {
        this.exp = exp;
        updataNextExp();
        updataExpbar();
    }

    private void updataNextExp() {

        nextLeaveExp = 5*(leave * leave + 5 * leave);

    }

    public void updataExpbar() {

        MainFragment mainFragment = MainActivity.getMainFragment();

        if(mainFragment != null) {

            FragmentMainBinding bind = mainFragment.getBinding();

            if(bind != null) {

                bind.exptext.setText("EXP " + exp + "/" + nextLeaveExp);
                bind.leavetext.setText("Lv " + leave);
                int progressBarNum = (int) ((int) ((double)exp) / ((double)nextLeaveExp) * 100);
                Log.i("Exp", "updataExpBar, progressBarNum=" + progressBarNum);
                bind.mainExpbar.setProgress(progressBarNum);

            }

        }

    }

    public int getExp() {
        return exp;
    }

    public int getLeave() {
        return leave;
    }
}
