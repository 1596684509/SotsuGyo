package com.example.sotsugyou.Activity.Fragment;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sotsugyou.Enum.SoundIdEnum;
import com.example.sotsugyou.Listener.Button.MainDollImageImp;
import com.example.sotsugyou.Listener.EventClick.TestClickImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.R;
import com.example.sotsugyou.Utils.BluetoothHandler;
import com.example.sotsugyou.Utils.SoundPlay;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.FragmentMainBinding;

import java.util.Random;

public class MainFragment extends Fragment {

    private View view;
    private Doll doll;


    private FragmentMainBinding binding;
    private SoundPlay soundPlay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        view = inflater.inflate(R.layout.fragment_main, container, false);
//        // Inflate the layout for this fragment
//        return view;

        binding = FragmentMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        return view;
        
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initObj();
        initView();
    }

    private void initObj() {

        doll = MainActivity.getApp().getUser().getDoll();
        doll.getExp().updataExpbar();
        soundPlay = MainActivity.getApp().getSoundPlay();



    }

    @Override
    public void onResume() {
        super.onResume();

        setShowDoll();

    }

    private void setShowDoll() {

        binding.mainDollNameTextView.setText(doll.getName());
        binding.mainDollImageView.setImageDrawable(Util.getIconRadius(getResources(), MainActivity.getApp().getUser().getDoll().getBitmap()));

        if(doll.getFrameId() != -1) {

            //binding.frame.setImageResource(doll.getFrameId());
            binding.frame.setImageDrawable(Util.getIconRadius(getResources(), doll.getFrameId()));

        }

        if(doll.getBackgroundId() != -1) {

            binding.background.setBackgroundResource(doll.getBackgroundId());

        }

    }

    private void initView() {

        binding.mainExpbar.setOnClickListener(new TestClickImp(this.getContext()));
        binding.mainDollImageView.setOnClickListener(new MainDollImageImp((Activity) view.getContext()));
        setShowDoll();

    }

    public FragmentMainBinding getBinding() {
        return binding;
    }
}