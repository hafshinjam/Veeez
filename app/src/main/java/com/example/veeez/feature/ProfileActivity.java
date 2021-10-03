package com.example.veeez.feature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.veeez.R;
import com.example.veeez.feature.address.AddressActivity;

public class ProfileActivity extends AppCompatActivity {

    ImageView ic_back;

    CardView cons_adress;
    CardView cons_message;
    CardView logOut;

    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ic_back=findViewById(R.id.profile_back_btn);

        constraintLayout = findViewById(R.id.cons_address);


        constraintLayout.setOnClickListener(view->{
            startActivity(new Intent(this,AddressActivity.class));
        });


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