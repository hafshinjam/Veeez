package com.example.veeez.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.veeez.Activities.auth.login.LoginActivity;
import com.example.veeez.R;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    AppCompatButton fab_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pager=findViewById(R.id.intro_pager);
        fab_next=findViewById(R.id.fab_next);




        int layouts[]={
                R.layout.simple,
                R.layout.post,
                R.layout.resturant

        };


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position==2){

                    fab_next.setText("خوش آمدین");
                }

            }

            @Override
            public void onPageSelected(int position) {


                if (position==2){

                    fab_next.setText("خوش آمدین");
                }
                else {

                    fab_next.setText("بعدی");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current=pager.getCurrentItem();
                if (current<layouts.length){
                    pager.setCurrentItem(current+1);
                }if(current==2) {

                    int state=current;
                    SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);

                    SharedPreferences.Editor myEdit = sharedPreferences.edit();

                    myEdit.putString("intro", String.valueOf(state));

                    myEdit.commit();

                    Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });



       pager.setAdapter(new PagerAdapter() {

           @NonNull
           @Override
           public Object instantiateItem(@NonNull ViewGroup container, int position) {
               LayoutInflater layoutInflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
               View view=layoutInflater.inflate(layouts[position],container,false);
                container.addView(view);
               return view;
           }

           @Override
           public int getCount() {
               return layouts.length;
           }

           @Override
           public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
               return view==object;
           }

           @Override
           public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

               View view= (View) object;
               container.removeView(view);
           }
       });

    }


}