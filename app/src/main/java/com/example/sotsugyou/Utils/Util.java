package com.example.sotsugyou.Utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

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
        roundedBitmapDrawable.setCornerRadius(1000);
        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;

    }

}
