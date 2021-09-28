package com.example.veeez.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.veeez.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;


public class Splash extends AppCompatActivity {

    AppCompatImageView splash;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash=findViewById(R.id.splash);
        progressBar=findViewById(R.id.spin_kit);
        Sprite Wave = new Wave();
        progressBar.setIndeterminateDrawable(Wave);

/*

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);


        String s1 = sh.getString("intro", "");
        //int a = sh.getInt("age", 0);

        int a=sh.getInt("intro", Integer.parseInt("0"));
      //  name.setText(s1);
       // age.setText(String.valueOf(a));
*/



        /*Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anime_splash);
        splash.startAnimation(rotate);
*/

        ObjectAnimator animation = ObjectAnimator.ofFloat(splash, "translationX", 10000f);
        animation.setDuration(10000);
        animation.start();

        SplashMethod();

       /* if (a==2){
                Intent intent=new Intent(Splash.this,Main_Page.class);
                startActivity(intent);

        }else {
            SplashMethod();

        }*/


    }

    private void SplashMethod() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
               // progressBar.isShown();
                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                Splash.this.startActivity(mainIntent);
                Splash.this.finish();
            }
        }, 3000);
    }
}