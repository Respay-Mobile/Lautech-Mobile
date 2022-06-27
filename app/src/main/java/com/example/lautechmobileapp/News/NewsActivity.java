package com.example.lautechmobileapp.News;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lautechmobileapp.Courses.CourseActivity;
import com.example.lautechmobileapp.Dashboard.HomeActivity;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Tasks.TaskActivity;
import com.example.lautechmobileapp.Tasks.TaskTabAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

public class NewsActivity extends AppCompatActivity {

    private TabLayout newsTabLayout;
    private ViewPager2 newsViewPager2;
    private TextView newsTextView;
    private RelativeLayout learnMoreLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);


        newsTabLayout = findViewById(R.id.newsTabLayout);
        newsViewPager2 = findViewById(R.id.newsviewPager2);
        newsTextView = findViewById(R.id.newsTextView);
        learnMoreLayout = findViewById(R.id.learnMoreLayout);

        newsTabLayout.addTab(newsTabLayout.newTab().setText("All"));
        newsTabLayout.addTab(newsTabLayout.newTab().setText("School"));
        newsTabLayout.addTab(newsTabLayout.newTab().setText("Faculty"));
        newsTabLayout.addTab(newsTabLayout.newTab().setText("SUG"));
        newsTabLayout.addTab(newsTabLayout.newTab().setText("Career"));
        newsTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        NewsTabAdapter adapter = new NewsTabAdapter(this, getSupportFragmentManager(), getLifecycle(), newsTabLayout.getTabCount());
        newsViewPager2.setAdapter(adapter);
        newsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                newsViewPager2.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        newsViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                newsTabLayout.selectTab(newsTabLayout.getTabAt(position));
            }
        });




        //set text for textviews
        newsTextView.setText("COVID-19 Omicron Variant Lorem ipsum siat riwfe");

        learnMoreLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Onclick listener for bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Course selected
        bottomNavigationView.setSelectedItemId(R.id.news);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(), CourseActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.tasks:
                        startActivity(new Intent(getApplicationContext(), TaskActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.news:
                        return true;

                }
                return false;
            }
        });


    }
}