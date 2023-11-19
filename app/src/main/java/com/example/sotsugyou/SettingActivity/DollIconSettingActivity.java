package com.example.sotsugyou.SettingActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.RoundedCorner;
import android.widget.ImageView;

import com.example.sotsugyou.R;

public class DollIconSettingActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_icon_setting);

        findView();
        initView();

    }

    private void findView() {

        imageView = findViewById(R.id.ImageViewNowIcon);

    }

    private void initView() {

        imageView.setImageDrawable(getIconRadius(R.drawable.dollicon1));

    }

    private RoundedBitmapDrawable getIconRadius(int imageId) {

        Bitmap src = BitmapFactory.decodeResource(getResources(), imageId);

        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), src);
        roundedBitmapDrawable.setCornerRadius(1000);
        roundedBitmapDrawable.setAntiAlias(true);

        return roundedBitmapDrawable;

    }

}