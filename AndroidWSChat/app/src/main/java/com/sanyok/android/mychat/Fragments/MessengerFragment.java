package com.sanyok.android.mychat.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sanyok.android.mychat.ConnectionService;
import com.sanyok.android.mychat.R;

/**
 * Created by sanyok on 3/9/2017.
 */

public class MessengerFragment extends android.app.Fragment {

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState){

        final String LOG_TAG = "myLogs";
        View v = inflater.inflate(R.layout.fragment_messaging, null);

        final Activity activity = getActivity();
        Button button = (Button) v.findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "Button click in Fragment1");

                Intent intent = new Intent(activity, ConnectionService.class);
                activity.startService(intent);
            }
        });

        return v;
    }
}
