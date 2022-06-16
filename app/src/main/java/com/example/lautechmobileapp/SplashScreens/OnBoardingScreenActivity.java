package com.example.lautechmobileapp.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lautechmobileapp.LoginActivity;
import com.example.lautechmobileapp.MainClass;
import com.example.lautechmobileapp.R;
import com.google.android.material.tabs.TabLayout;

public class OnBoardingScreenActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideAdapter slideAdapter;
    private TabLayout tabLayout;
    private int currentPage;
    private Button btnNext;

    MainClass mainClass = new MainClass(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);

        viewPager = findViewById(R.id.pager);
        btnNext = findViewById(R.id.onbButton);
        tabLayout = findViewById(R.id.tab_layout);


        //adapter for viewpager
        slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);

        //set tablayout to move with viewpager
        tabLayout.setupWithViewPager(viewPager);

        //listens for when user swipes and the page is changed
        viewPager.addOnPageChangeListener(viewListener);

        //change the size of the skip and next button based on the size of the screen

        if(mainClass.getWidth() >= 720){
            if(mainClass.getSize() <= 1){
                btnNext.setTextSize(18f);
            }else {
                btnNext.setTextSize(16f);
            }
        } else {
            btnNext.setTextSize(14f);
        }

        //when next button is clicked
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If text on button is next move to next image else move tothe next activity
                if(btnNext.getText().toString().matches("Next")){
                    viewPager.setCurrentItem(currentPage + 1);
                } else {
                    //If user reaches the last onboarding activity move to the OnBoarding End activtity
                    SharedPreferences sharedPreferences = getSharedPreferences("ON_BOARDING", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("on_boarding", "shown");
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }

            }
        });

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            currentPage = position;

            if(position == 0){
                btnNext.setText(R.string.next);
            } else if(position == 3){
                btnNext.setText(R.string.getStart);
            } else {
                btnNext.setText(R.string.next);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}