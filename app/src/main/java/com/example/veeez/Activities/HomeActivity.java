package com.example.veeez.Activities;

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

import com.example.veeez.Activities.map.DeliveryActivity;
import com.example.veeez.R;
import com.google.android.material.navigation.NavigationView;

import org.reactivestreams.Subscription;

import java.util.Observable;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {



    CardView normalDeliveryCourierCv;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView homeNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        toolbar=findViewById(R.id.toolbar);
        homeNav=findViewById(R.id.homeNav);
        normalDeliveryCourierCv =findViewById(R.id.normal_delivery_courier_cv);

        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.my_drawer_layout);
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


        homeNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case  R.id.aboutUs:
                        startActivity(new Intent(HomeActivity.this, AboutusActivity.class));
                        break;
                    case  R.id.support:
                        startActivity(new Intent(HomeActivity.this, SupportActivity.class));
                        break;
                    case  R.id.termsOfService:
                        startActivity(new Intent(HomeActivity.this, TermsOfService.class));
                        break;
                    case  R.id.question:
                        startActivity(new Intent(HomeActivity.this, QuestionActivity.class));
                        break;


                    case  R.id.profile:
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                        break;

                     case  R.id.wallet:
                         startActivity(new Intent(HomeActivity.this, WalletActivity.class));
                         break;
                    case  R.id.orderList:
                        startActivity(new Intent(HomeActivity.this, OrderActivity.class));
                        break;
                    case  R.id.financialHistory:
                        startActivity(new Intent(HomeActivity.this, FinanntialActivity.class));
                        break;

                }
                drawerLayout.closeDrawers();
                return true;
            }

        });


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
            }
            else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }
        return false;
    }

}