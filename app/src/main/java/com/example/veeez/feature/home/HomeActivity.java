package com.example.veeez.feature.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.example.veeez.feature.AboutusActivity;
import com.example.veeez.feature.FinanntialActivity;
import com.example.veeez.feature.OrderActivity;
import com.example.veeez.feature.ProfileActivity;
import com.example.veeez.feature.QuestionActivity;
import com.example.veeez.feature.SupportActivity;
import com.example.veeez.feature.TermsOfService;
import com.example.veeez.feature.WalletActivity;
import com.example.veeez.feature.map.DeliveryActivity;
import com.example.veeez.R;
import com.example.veeez.feature.map.PostalActivity;
import com.google.android.material.navigation.NavigationView;



public class HomeActivity extends AppCompatActivity {


    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView homeNav;

    private CardView normalDeliveryCourierCv;
    private CardView postalCourierCv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initView();

        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        // to make the Navigation drawer icon always appear on the action bar

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        normalDeliveryCourierCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, DeliveryActivity.class));
            }
        });

        postalCourierCv.setOnClickListener(view -> {
            startActivity(new Intent(HomeActivity.this, PostalActivity.class));
        });


        homeNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.aboutUs:
                        startActivity(new Intent(HomeActivity.this, AboutusActivity.class));
                        break;
                    case R.id.support:
                        startActivity(new Intent(HomeActivity.this, SupportActivity.class));
                        break;
                    case R.id.termsOfService:
                        startActivity(new Intent(HomeActivity.this, TermsOfService.class));
                        break;
                    case R.id.question:
                        startActivity(new Intent(HomeActivity.this, QuestionActivity.class));
                        break;


                    case R.id.profile:
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                        break;

                    case R.id.wallet:
                        startActivity(new Intent(HomeActivity.this, WalletActivity.class));
                        break;
                    case R.id.orderList:
                        startActivity(new Intent(HomeActivity.this, OrderActivity.class));
                        break;
                    case R.id.financialHistory:
                        startActivity(new Intent(HomeActivity.this, FinanntialActivity.class));
                        break;

                }
                drawerLayout.closeDrawers();
                return true;
            }

        });


    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);
        homeNav = findViewById(R.id.homeNav);
        normalDeliveryCourierCv = findViewById(R.id.normal_delivery_courier_cv);
        postalCourierCv = findViewById(R.id.postal_courier_cv);
        drawerLayout = findViewById(R.id.my_drawer_layout);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null && item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            } else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }
        return false;
    }

}