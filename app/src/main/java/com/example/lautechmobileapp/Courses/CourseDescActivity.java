package com.example.lautechmobileapp.Courses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lautechmobileapp.R;
import com.google.android.material.tabs.TabLayout;

public class CourseDescActivity extends AppCompatActivity {

    private CardView imageCardView;
    private NestedScrollView mScrollView;
    private ImageView courseImage;
    private TextView courseTitle, courseCode, courseUnit, courseComp;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_desc);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        courseTitle = findViewById(R.id.courseTitle);
        courseCode = findViewById(R.id.courseCode);
        courseUnit = findViewById(R.id.courseUnit);
        courseComp = findViewById(R.id.courseCompuls);
        mScrollView = findViewById(R.id.scrollLayout);
        imageCardView = findViewById(R.id.courseImageCard);
        courseImage = findViewById(R.id.courseImage);

        courseTitle.setText("Analytic Chemistry");
        courseCode.setText("CHM 201");
        courseUnit.setText("3 Units");
        courseComp.setText("Compulsory");

        if(courseComp.equals("Compulsory") || courseComp.equals("compulsory"))
        {
            courseComp.setTextColor(getResources().getColor(R.color.greenColor));
        } else{
            courseComp.setTextColor(getResources().getColor(R.color.unitCOlor));
        }

        //disable swipe
        viewPager.setUserInputEnabled(false);

        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Course Content"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
         TabAdapter adapter = new TabAdapter(this, getSupportFragmentManager(), getLifecycle(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        setTopBarColor();

        //Observer to change size of image on scroll
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ScrollPositionObserver());


    }

    //Class to reduce the size of the image onscroll
    private class ScrollPositionObserver implements ViewTreeObserver.OnScrollChangedListener {

        private int mImageViewHeight;

        public ScrollPositionObserver() {
            mImageViewHeight = getResources().getDimensionPixelSize(R.dimen.image_height);
        }

        @Override
        public void onScrollChanged() {
            int scrollY = Math.min(Math.max(mScrollView.getScrollY(), 0), mImageViewHeight);


            // changing position of ImageView
            imageCardView.setTranslationY(scrollY / 2);
            courseImage.setTranslationY(scrollY / 2);

            // alpha you could set to ActionBar background
            float alpha = scrollY / (float) mImageViewHeight;
        }
    }

    public void setTopBarColor(){
        //set status bar color
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.onbTitle));
    }
}