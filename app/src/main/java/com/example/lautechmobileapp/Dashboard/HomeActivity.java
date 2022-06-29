package com.example.lautechmobileapp.Dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lautechmobileapp.Courses.CourseActivity;
import com.example.lautechmobileapp.Courses.CourseCardRecyclerView.CourseCardAdapter;
import com.example.lautechmobileapp.Courses.CourseCardRecyclerView.CourseCardItem;
import com.example.lautechmobileapp.Courses.CourseDetailsActivity;
import com.example.lautechmobileapp.Dashboard.NewsCardViewPager2.NewsCardAdapter;
import com.example.lautechmobileapp.Dashboard.NewsCardViewPager2.NewsCardItem;
import com.example.lautechmobileapp.News.NewsActivity;
import com.example.lautechmobileapp.News.NewsDetailsActivity;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Tasks.TaskActivity;
import com.example.lautechmobileapp.Wallet.WalletActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText searchInput;
    private String code1, time1, venue1, code2, time2, venue2, code3, time3, venue3;
    private TextView coursecode1, coursetime1, coursevenue1, coursecode2, coursetime2, coursevenue2, coursecode3, coursetime3, coursevenue3,
            courseCardTitle1, courseCardTitle2, courseCardSubtitle1, courseCardSubtitle2, courseCardUnit1, courseCardUnit2,
            timeTableSeeAllText,courseSeeAllText, newsSeeAllText;
    private CardView courseCard1, courseCard2;
    private ViewPager2 mViewPager;
    private NewsCardAdapter mCardAdapter;
    private List newsDataList = new ArrayList<>();
    private ImageView walletIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //change the topbar color
        setTopBarColor();

        searchInput = findViewById(R.id.searchInput);
        coursecode1 = findViewById(R.id.courseCode1);
        coursetime1 = findViewById(R.id.courseTime1);
        coursevenue1 = findViewById(R.id.courseVenue1);
        coursecode2 = findViewById(R.id.courseCode2);
        coursetime2 = findViewById(R.id.courseTime2);
        coursevenue2 = findViewById(R.id.courseVenue2);
        coursecode3 = findViewById(R.id.courseCode3);
        coursetime3 = findViewById(R.id.courseTime3);
        coursevenue3 = findViewById(R.id.courseVenue3);
        //view pager for card
        mViewPager =  findViewById(R.id.pager);

        //Inititalize items of course card
        courseCardTitle1 = findViewById(R.id.courseTitle1);
        courseCardTitle2 = findViewById(R.id.courseTitle2);
        courseCardSubtitle1 = findViewById(R.id.courseSubtitle1);
        courseCardSubtitle2 = findViewById(R.id.courseSubtitle2);
        courseCardUnit1 = findViewById(R.id.courseUnit1);
        courseCardUnit2 = findViewById(R.id.courseUnit2);

        //Initialize see all textviews
        timeTableSeeAllText = findViewById(R.id.seeAllTextView);
        newsSeeAllText = findViewById(R.id.seeAllTextView2);
        courseSeeAllText = findViewById(R.id.seeAllTextView3);

        courseCard1 = findViewById(R.id.courseCard1);
        courseCard2 = findViewById(R.id.courseCard2);

        walletIcon = findViewById(R.id.walletIcon);

        //set values with various textvies in the Timetable card
        setTextValues();

        //set values with various textvies in the Course card
        setCourseCardValues();

        //Object for NewsCardAdapter
        mCardAdapter = new NewsCardAdapter(newsDataList);

        //Set CardAdapter as viewpager2adapter
        mViewPager.setAdapter(mCardAdapter);
        setNewsCardValues();
        mViewPager.setClipToPadding(false);
        mViewPager.setClipChildren(false);
        mViewPager.setOffscreenPageLimit(3);

        int pageMarginPx = 110;
        int offsetPx = 120;

        mViewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                ViewPager2 viewPager = (ViewPager2) page.getParent().getParent();
                float offset = position * -(2 * offsetPx + pageMarginPx);

                if (viewPager.getOrientation()  == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(viewPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-offset);
                    } else {
                        page.setTranslationX(offset);
                    }
                } else {
                    page.setTranslationY(offset);
                }
            }
        });


        //See all textviews onclick listener
        courseSeeAllText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
                startActivity(intent);
            }
        });

        newsSeeAllText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
                startActivity(intent);
            }
        });

        //onclick listener for news card
        mCardAdapter.setOnItemClickListener(new NewsCardAdapter.OnItemClickListener() {
            @Override
            public void onClick(NewsCardItem items) {
                Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
                startActivity(intent);
            }
        });

        //Onclick listener for course cards
        courseCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CourseDetailsActivity.class);
                startActivity(intent);
            }
        });

        courseCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CourseDetailsActivity.class);
                startActivity(intent);
            }
        });

        //Onclick listener for wallet icon
        walletIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WalletActivity.class);
                startActivity(intent);
            }
        });

        //Onclick listener for bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.home:
                        return true;
                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(),CourseActivity.class));
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
        courseCardTitle1.setText("CHEM 201");
        courseCardSubtitle1.setText("Amet minim mollit non deserunt ullamco est sit");
        courseCardUnit1.setText("3 Units");

        courseCardTitle2.setText("CHEM 201");
        courseCardSubtitle2.setText("Amet minim mollit non deserunt ullamco est sit");
        courseCardUnit2.setText("3 Units");
    }
    public void setNewsCardValues(){
        NewsCardItem data = new NewsCardItem("Post Graduate School", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do.");
        newsDataList.add(data);
        data = new NewsCardItem("Undergraduate School", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do.");
        newsDataList.add(data);
        data = new NewsCardItem("Post Graduate2 School", "Amet minim mollit non deserunt ullamco est sit aliqua dolor do.");
        newsDataList.add(data);
    }

    public void setTextValues()
    {
        code1 = "SSC 203";
        time1 = "8.00 am - 10.00 am";
        venue1 = "ODLT";
        code2 = "CHM 201";
        time2 = "10.00 am - 11.20 am";
        venue2 = "EFT LT";
        code3 = "PHY 205";
        time3 = "2.00 pm - 3.00 pm";
        venue3 = "Vas LT";


        coursecode1.setText(code1);
        coursetime1.setText(time1);
        coursevenue1.setText(venue1);

        coursecode2.setText(code2);
        coursetime2.setText(time2);
        coursevenue2.setText(venue2);

        coursecode3.setText(code3);
        coursetime3.setText(time3);
        coursevenue3.setText(venue3);
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


    //If user clicks back button show alert dialog
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder
                .setMessage(getString(R.string.sureToExit))
                .setCancelable(true)
                .setPositiveButton(getString(R.string.no),
                        (dialog, which) -> dialog.dismiss())
                .setNegativeButton(getString(R.string.yesToExit),
                        (dialog, which) -> finishAffinity());

        AlertDialog dialog = alertDialogBuilder.create();
        dialog.show();
    }



}