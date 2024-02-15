package com.example.sotsugyou.Activity.Fragment;



import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sotsugyou.Handler.MessageHandler;
import com.example.sotsugyou.Listener.Button.MainDollImageImp;
import com.example.sotsugyou.Listener.EventClick.ShowHintClickEventImp;
import com.example.sotsugyou.MainActivity;
import com.example.sotsugyou.MyListener.Messageable;
import com.example.sotsugyou.Object.Doll;
import com.example.sotsugyou.Utils.SoundPlay;
import com.example.sotsugyou.Utils.Util;
import com.example.sotsugyou.databinding.FragmentMainBinding;

public class MainFragment extends Fragment {

    private View view;
    private Doll doll;


    private FragmentMainBinding binding;
    private MessageHandler messageHandler;
    private SoundPlay soundPlay;
    private boolean isTest = true;


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
        showMessage();
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

        binding.mainDollNameTextView.setText(MainActivity.getApp().getUser().getDoll().getName());

        binding.mainDollImageView.setImageDrawable(Util.getIconRadius(getResources(), MainActivity.getApp().getUser().getDoll().getBitmap()));

        if(doll.getFrameId() != -1) {

            //binding.frame.setImageResource(doll.getFrameId());
            binding.frame.setImageDrawable(Util.getIconRadius(getResources(), doll.getFrameId()));

        }

        if(doll.getBackgroundId() != -1) {

            binding.background.setBackgroundResource(doll.getBackgroundId());

        }

        MainActivity.getApp().getSoundPlay().setSound();

    }


    private void initView() {

        binding.mainDollImageView.setOnClickListener(new MainDollImageImp((Activity) view.getContext()));
        binding.mainExpbar.setOnClickListener(new ShowHintClickEventImp(this, "exption_expbar"));
        binding.exptext.setOnClickListener(new ShowHintClickEventImp(this, "exption_expbar"));
        setShowDoll();


    }

    private void showMessage() {

        if(messageHandler == null) {

            messageHandler = new MessageHandler();

        }

        messageHandler.regiserMessageEvent(new Messageable() {
            @Override
            public void onMessageChenge(String message) {
                binding.explanation.setText(message);
                Log.i("message", message);
            }
        });

        new Thread(messageHandler).start();

    }

    public FragmentMainBinding getBinding() {
        return binding;
    }
}