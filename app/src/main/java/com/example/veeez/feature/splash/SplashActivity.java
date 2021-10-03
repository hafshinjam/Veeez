package com.example.veeez.feature.splash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.veeez.R;
import com.example.veeez.data.UserManager;
import com.example.veeez.data.UserManagerProvider;
import com.example.veeez.feature.home.HomeActivity;
import com.example.veeez.feature.main.MainActivity;
import com.example.veeez.services.http.veeez.VeeezApiInterfaceProvider;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import org.jetbrains.annotations.NotNull;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class SplashActivity extends AppCompatActivity {

    AppCompatImageView splash;
    ProgressBar progressBar;
    private UserManager userManager;
    private SplashViewModel viewModel = new SplashViewModel(VeeezApiInterfaceProvider.getInstance());
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Button retryBtn;
    private TextView retryHint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        userManager = UserManagerProvider.getInstance(this);

        retryBtn = findViewById(R.id.retry_btn);
        retryHint = findViewById(R.id.retry_hint);

        splash = findViewById(R.id.splash);
        progressBar = findViewById(R.id.spin_kit);
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

        splashMethod();

        retryBtn.setOnClickListener(view->{
            progressBar.setVisibility(View.VISIBLE);
            splashMethod();
        });

       /* if (a==2){
                Intent intent=new Intent(Splash.this,Main_Page.class);
                startActivity(intent);

        }else {
            SplashMethod();

        }*/


    }

    private void splashMethod() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                // progressBar.isShown();

                isServerReady(aBoolean->{
                   if (aBoolean){
                       progressBar.setVisibility(View.VISIBLE);
                       retryBtn.setVisibility(View.INVISIBLE);
                       retryHint.setVisibility(View.INVISIBLE);

                       if (!userManager.getUserId().isEmpty()) {
                           Intent mainIntent = new Intent(getApplicationContext(), HomeActivity.class);
                           SplashActivity.this.startActivity(mainIntent);
                           finish();
                       } else {
                           Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                           SplashActivity.this.startActivity(mainIntent);
                           finish();
                       }
                       SplashActivity.this.finish();

                   }else {
                       retryBtn.setVisibility(View.VISIBLE);
                       retryHint.setVisibility(View.VISIBLE);
                       progressBar.setVisibility(View.INVISIBLE);
                   }

                });




            }
        }, 3000);
    }

    private Boolean isServerReady(ServerCallBack serverCallBack){

        viewModel.getServerStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NotNull Boolean aBoolean) {
                        serverCallBack.onServedConnect(aBoolean);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.e("TAG", "onError: "+e.toString() );
                        retryBtn.setVisibility(View.VISIBLE);
                        retryHint.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

        return true;
    }

    private interface ServerCallBack{
        void onServedConnect(Boolean isConnect);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}