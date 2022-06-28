package com.example.lautechmobileapp.Courses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lautechmobileapp.Courses.CourseCardRecyclerView.CourseCardAdapter;
import com.example.lautechmobileapp.Courses.CourseCardRecyclerView.CourseCardItem;
import com.example.lautechmobileapp.Courses.CourseCardRecyclerView.RecyclerViewItemDecorator;
import com.example.lautechmobileapp.Dashboard.HomeActivity;
import com.example.lautechmobileapp.News.NewsActivity;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Tasks.TaskActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CourseActivity extends AppCompatActivity {

    private TextView courseCardTitle, courseCardSubtitle, courseCardUnit;
    private ImageView courseCardImage;
    private RecyclerView mRecyclerView;
    private CourseCardAdapter mCardAdapter;
    private List courseDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        mRecyclerView = findViewById(R.id.recyclerView);
        //Inititalize items of course card
        courseCardTitle = findViewById(R.id.courseTitle);
        courseCardSubtitle = findViewById(R.id.courseSubtitle);
        courseCardUnit = findViewById(R.id.courseUnit);
        courseCardImage = findViewById(R.id.courseImage);


        //Object for NewsCardAdapter
        mCardAdapter = new CourseCardAdapter(courseDataList);

        //Set CardAdapter as viewpager2adapter
        mRecyclerView.setAdapter(mCardAdapter);
        // set a GridLayoutManager with 3 number of columns , horizontal gravity and false value for reverseLayout to show the items from start to end
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        mCardAdapter.setOnItemClickListener(new CourseCardAdapter.OnItemClickListener() {
            @Override
            public void onClick(CourseCardItem items) {
                Intent intent = new Intent(getApplicationContext(), CourseDetailsActivity.class);
                startActivity(intent);
            }
        });


        //Use ItemDecorator class to adjust spacing between cards
        RecyclerViewItemDecorator itemDecoration = new RecyclerViewItemDecorator(getApplicationContext(), R.dimen.item_offset);
        mRecyclerView.addItemDecoration(itemDecoration);
        setCourseCardValues();

        //Onclick listener for bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Course selected
        bottomNavigationView.setSelectedItemId(R.id.courses);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.courses:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
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

                }
                return false;
            }
        });


    }


    public void setCourseCardValues(){
        CourseCardItem data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
        data = new CourseCardItem("CHM 201", "Amet minim mollit non deserunt ullamco est sit", "3 Units", R.drawable.course_image);
        courseDataList.add(data);
    }

}