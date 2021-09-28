package com.example.veeez.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.veeez.R;

public class SimplePostActivity extends AppCompatActivity {


    AppCompatButton id_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_post);


        id_ok=findViewById(R.id.id_ok);

        id_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SimplePostActivity.this,WaitingActivity.class));
            }
        });
    }
}