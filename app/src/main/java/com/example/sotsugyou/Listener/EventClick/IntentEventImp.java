package com.example.sotsugyou.Listener.EventClick;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class IntentEventImp implements View.OnClickListener {

    private Context context;
    private Class aClass;

    public IntentEventImp(Context context, Class aClass) {
        this.context = context;
        this. aClass = aClass;
    }

    @Override
    public void onClick(View view) {

        if(context != null) {

            Intent intent = new Intent(context, aClass);
            context.startActivity(intent);

        }

    }
}
