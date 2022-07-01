package com.example.lautechmobileapp.Profile.Results;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.lautechmobileapp.Courses.CourseActivity;
import com.example.lautechmobileapp.Dashboard.HomeActivity;
import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsAdapter;
import com.example.lautechmobileapp.News.AllNewsFragmentRecyclerView.AllNewsItem;
import com.example.lautechmobileapp.News.NewsActivity;
import com.example.lautechmobileapp.Profile.PersonalInfoActivity;
import com.example.lautechmobileapp.Profile.ProfileActivity;
import com.example.lautechmobileapp.Profile.Results.ParentRecyclerView.ParentSemesterResultAdapter;
import com.example.lautechmobileapp.Profile.Results.ParentRecyclerView.ParentSemesterResultItem;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Tasks.TaskActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    private RelativeLayout profileLayout;
    private RecyclerView resultParentRecyclerView;
    private ParentSemesterResultAdapter parentAdapter;
    private List parentResultDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        profileLayout = findViewById(R.id.resultProfileRelativeLayout);
        resultParentRecyclerView = findViewById(R.id.parentSemesterResultRecyclerView);

        //Onclick listener for profile relative layout
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PersonalInfoActivity.class );
                startActivity(intent);
            }
        });

        setTopBarColor();

        //Object for parentResultAdapter
        parentAdapter = new ParentSemesterResultAdapter(parentResultDataList, getApplicationContext());
        resultParentRecyclerView.setAdapter(parentAdapter);
        resultParentRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()) );

        //Use the method declared below
        setResultData();


        //Onclick listener for bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.more);

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
                        startActivity(new Intent(getApplicationContext(), NewsActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.more:
                        return true;
                }
                return false;
            }
        });
    }

    public void setResultData(){
        ParentSemesterResultItem data = new ParentSemesterResultItem("100 Level", "1st Semester", "4.63", "(74/16)");
        parentResultDataList.add(data);

        data = new ParentSemesterResultItem("100 Level", "2nd Semester", "4.44", "(71/16)");
        parentResultDataList.add(data);

        data = new ParentSemesterResultItem("200 Level", "1st Semester", "4.63", "(74/16)");
        parentResultDataList.add(data);

        data = new ParentSemesterResultItem("200 Level", "2nd Semester", "4.44", "(71/16)");
        parentResultDataList.add(data);

    }

    public void setTopBarColor(){
        //set status bar color
        Window window = this.getWindow();


        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.dashboardTitle));
    }



}