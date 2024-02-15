package com.example.sotsugyou.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sotsugyou.R;

import java.util.HashMap;

public class ImageLoader implements Runnable {

    private static final int MSG_LOAD_IMAGE = 1;
    private HashMap<Integer, Bitmap> bitmapHashMap;
    private Context context;
    private ImageLoaderListener listener;

    public ImageLoader(Context context) {
        this.context = context;
        loadImage();

    }

    public void loadImage() {

        bitmapHashMap = new HashMap<>();
        addImage(R.drawable.frame1);
        addImage(R.drawable.frame2);
        addImage(R.drawable.frame3);
        addImage(R.drawable.frame4);
        addImage(R.drawable.frame5);
        addImage(R.drawable.frame6);
        addImage(R.drawable.background1);
        addImage(R.drawable.background2);
        addImage(R.drawable.background3);
        addImage(R.drawable.background4);
        addImage(R.drawable.background5);
//        addImage(R.drawable.background6);

        onLoaded();

    }

    private void addImage(int id) {

        if(bitmapHashMap != null && context != null) {

            bitmapHashMap.put(id, BitmapFactory.decodeResource(context.getResources(), id));

        }

    }

    public void registerListener(ImageLoaderListener listener) {

        this.listener = listener;

    }

    private void onLoaded() {

        if(listener != null) {

            listener.onLoaded(bitmapHashMap);

        }


    }

    @Override
    public void run() {

        loadImage();

    }
}
