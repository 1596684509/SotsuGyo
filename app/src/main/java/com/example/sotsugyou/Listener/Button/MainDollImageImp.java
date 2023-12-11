package com.example.sotsugyou.Listener.Button;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;

public class MainDollImageImp implements View.OnClickListener {

    public static final int REQUESTCODE_CAMERA = 10001;

    private Activity context;

    public MainDollImageImp(Activity context) {
        this.context = context;
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivityForResult(intent, REQUESTCODE_CAMERA);

    }
}
