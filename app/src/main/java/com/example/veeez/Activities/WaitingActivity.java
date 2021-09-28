package com.example.veeez.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.veeez.R;

public class WaitingActivity extends AppCompatActivity {


    RelativeLayout touch_vice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        touch_vice=findViewById(R.id.touch_vice);


    }
}