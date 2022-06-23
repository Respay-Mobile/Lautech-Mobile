package com.example.lautechmobileapp.Dashboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lautechmobileapp.Dashboard.NewsCardViewPager2.NewsCardAdapter;
import com.example.lautechmobileapp.Dashboard.NewsCardViewPager2.NewsCardItem;
import com.example.lautechmobileapp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private EditText searchInput;
    private String code1, time1, venue1, code2, time2, venue2, code3, time3, venue3;
    private TextView coursecode1, coursetime1, coursevenue1, coursecode2, coursetime2, coursevenue2, coursecode3, coursetime3, coursevenue3;
    private ViewPager2 mViewPager;
    private NewsCardAdapter mCardAdapter;

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
        mViewPager = (ViewPager2) findViewById(R.id.pager);

        //set values with various textvies in the Timetable card
        setTextValues();

        enableSearchInput();

        //Item class for purple card
        List<NewsCardItem> newsCardItem = new ArrayList<>();

        //Object for CardVIewPager2Adapter
        mCardAdapter = new NewsCardAdapter(newsCardItem);
        newsCardItem.add(new NewsCardItem(R.string.PGschool, R.string.temporaryText));
        newsCardItem.add(new NewsCardItem(R.string.PGschool, R.string.temporaryText));
        newsCardItem.add(new NewsCardItem(R.string.PGschool, R.string.temporaryText));
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

    public void enableSearchInput(){
        searchInput.setEnabled(true);
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