package com.example.sotsugyou.Activity.First;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sotsugyou.Listener.Button.FirstIconSettingSaveButtonImp;
import com.example.sotsugyou.Listener.Button.MainDollImageImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.Util;

public class FirstDollImageSettingActivity extends AppCompatActivity {

    private ImageView icon;
    private Button save;

    private Bitmap rotatedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_doll_image_setting);

        findView();
        initView();

    }

    private void findView() {

        icon = findViewById(R.id.firstIcon_imageView);
        save = findViewById(R.id.firstdollIcon_button_save);

    }

    private void initView() {

        icon.setOnClickListener(new MainDollImageImp(this));
        icon.setImageDrawable(Util.getIconRadius(getResources(), R.drawable.whiteround));

        save.setOnClickListener(new FirstIconSettingSaveButtonImp(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MainDollImageImp.REQUESTCODE_CAMERA) {

            Bundle bundle = data.getExtras();

            if(bundle == null) {

                return;

            }

            Bitmap bitmap = (Bitmap) bundle.get("data");

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

            icon.setImageDrawable(Util.getIconRadius(getResources(), rotatedBitmap));

        }

    }

    public ImageView getIcon() {
        return icon;
    }

    public Bitmap getRotatedBitmap() {
        return rotatedBitmap;
    }
}