package com.example.sotsugyou.Utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.example.sotsugyou.Item.Item;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.ThreadLocalRandom;

public class Util{

    /**
     * 画像IDから画像を探し、丸にして表示する
     * 例：setImageDrawable(Util.getIconRadius(getResources(), user.getIconId()))
     * @param rec 画像のPATH
     * @param imageId 画像のID
     * @return RoundedBitMapDrawabel
     */
    public static RoundedBitmapDrawable getIconRadius(Resources rec, int imageId) {

        Bitmap src = BitmapFactory.decodeResource(rec, imageId);

        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(rec, src);
        roundedBitmapDrawable.setCircular(true);
        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;

    }

    public static RoundedBitmapDrawable getIconRadius(Resources rec, Bitmap bitmap) {


        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(rec, bitmap);
        roundedBitmapDrawable.setCircular(true);
        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;

    }
    public static String getImageByte(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        if(bitmap != null) {

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

        }else {

            Log.i("getImageByte", "bitmap is null");

        }

        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);

    }

    public static void delay(int i) {

        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static Bitmap getBitMapForBtye(String byteStr) {

        if(byteStr != null) {

            byte[] imageByte = Base64.decode(byteStr, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);

        }

        return null;

    }
}
