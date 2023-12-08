package com.example.sotsugyou.Listener.Button;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ReturnButtonOnClickImp implements View.OnClickListener {

    private AppCompatActivity context;

    public ReturnButtonOnClickImp(AppCompatActivity context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {

        context.finish();

    }
}
