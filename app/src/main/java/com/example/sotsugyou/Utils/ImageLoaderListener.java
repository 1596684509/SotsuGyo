package com.example.sotsugyou.Utils;

import android.graphics.Bitmap;

import java.util.HashMap;

public interface ImageLoaderListener {

    void onLoaded(HashMap<Integer, Bitmap> loadedImageList);

}
