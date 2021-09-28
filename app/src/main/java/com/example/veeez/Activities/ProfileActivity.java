package com.example.veeez.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.veeez.R;

public class ProfileActivity extends AppCompatActivity {

    ImageView ic_back;

    CardView cons_adress;
    CardView cons_message;
    CardView logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ic_back=findViewById(R.id.profile_back_btn);


        cons_adress=findViewById(R.id.profileAddressLayout);
        cons_message = findViewById(R.id.profileMessageLayout);
        logOut = findViewById(R.id.profileLogoutLayout);


        ic_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cons_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ProfileActivity.this, AddressActivity.class));
            }
        });


        cons_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,MessageActivity.class));
            }
        });
    }
}