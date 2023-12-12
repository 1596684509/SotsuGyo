package com.example.sotsugyou.Activity.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sotsugyou.Listener.Button.MainDollImageImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.Util;

public class MainFragment extends Fragment {

    private View view;
    private ImageView dollImageView;
    private Doll doll;

    private TextView dollNameTextzView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        initObj();
        findView();
        initView();
        // Inflate the layout for this fragment
        return view;
        
    }

    private void initObj() {

        doll = MainActivity.getApp().getUser().getDoll();

    }

    private void findView() {

        dollImageView = view.findViewById(R.id.main_dollImageView);
        dollNameTextzView = view.findViewById(R.id.main_dollName_textView);


    }

    private void initView() {

        dollNameTextzView.setText(doll.getName());

        dollImageView.setOnClickListener(new MainDollImageImp((Activity) view.getContext()));
        dollImageView.setImageDrawable(Util.getIconRadius(getResources(), MainActivity.getApp().getUser().getDoll().getBitmap()));

    }

    public ImageView getDollImageView() {
        return dollImageView;
    }
}