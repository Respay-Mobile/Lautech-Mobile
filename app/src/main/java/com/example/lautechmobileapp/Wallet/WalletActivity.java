package com.example.lautechmobileapp.Wallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lautechmobileapp.Courses.CourseActivity;
import com.example.lautechmobileapp.Courses.CourseCardRecyclerView.CourseCardAdapter;
import com.example.lautechmobileapp.Dashboard.HomeActivity;
import com.example.lautechmobileapp.News.NewsActivity;
import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Tasks.AssignmentFragmentRecyclerView.AssignmentsAdapter;
import com.example.lautechmobileapp.Tasks.AssignmentFragmentRecyclerView.AssignmentsItem;
import com.example.lautechmobileapp.Tasks.TaskActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class WalletActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private WalletCardAdapter mWalletCardAdapter;
    private TextView seeAllText, walletId, walletIdNum;
    private CardView transactionCard;
    private RelativeLayout transactionLayout;
    private ConstraintLayout walletRelativeLayout1;
    private List walletDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        mRecyclerView = findViewById(R.id.walletRecyclerView);
        seeAllText = findViewById(R.id.seeAllTextView);
        walletId = findViewById(R.id.walletID);
        walletIdNum = findViewById(R.id.walletIDNum);
        transactionCard = findViewById(R.id.transactionCard);
        walletRelativeLayout1 = findViewById(R.id.walletRelativeLayout1);
        transactionLayout = findViewById(R.id.transactionLayout);

        //Object for WalletCardAdapter
        mWalletCardAdapter = new WalletCardAdapter(walletDataList);
        mRecyclerView.setAdapter(mWalletCardAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()) );
        //Use the method declared below
        setTransactionValues();

        //onclick listener for see all textview
        seeAllText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeAllTextClicked();
            }
        });


        //Onclick listener for bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);


        // Perform item selected listener
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.courses:
                        startActivity(new Intent(getApplicationContext(), CourseActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
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

        setTopBarColor();
    }

    //set the texts for the textviews
    public void setTransactionValues(){
        WalletCardItem data = new WalletCardItem("LAUTECH", "School Fees", "- ₦10,000,00", "09:39 AM", R.drawable.ic_person_icon, R.drawable.ic_red_arrow_icon );
        walletDataList.add(data);
        data = new WalletCardItem("Wallet Top-up", "MasterCard • 5318", "+ ₦100,000,00", "09:39 AM", R.drawable.ic_person_icon, R.drawable.ic_green_arrow_icon );
        walletDataList.add(data);
        data = new WalletCardItem("Biochemistry", "Dept. Due", "- ₦10,000,00", "09:39 AM", R.drawable.ic_person_icon, R.drawable.ic_red_arrow_icon );
        walletDataList.add(data);
        data = new WalletCardItem("Bursary", "Ondo State Bursary", "+ ₦10,000,00", "09:39 AM", R.drawable.ic_person_icon, R.drawable.ic_green_arrow_icon );
        walletDataList.add(data);
        data = new WalletCardItem("JL Bookstore", "Bookstore", "- ₦10,000,00", "09:39 AM", R.drawable.ic_person_icon, R.drawable.ic_red_arrow_icon );
        walletDataList.add(data);
        data = new WalletCardItem("Savings Interest", "May ‘22 Interest", "+ ₦10,000,00", "09:39 AM", R.drawable.ic_person_icon, R.drawable.ic_green_arrow_icon );
        walletDataList.add(data);
        data = new WalletCardItem("Wallet Transfer", "Seyi Second", "- ₦10,000,00", "09:39 AM", R.drawable.ic_person_icon, R.drawable.ic_red_arrow_icon );
        walletDataList.add(data);
    }

    public void seeAllTextClicked(){
        //change the amount of data shown(4) to the number of items in the list
        mWalletCardAdapter.num = walletDataList.size();
        mWalletCardAdapter.notifyDataSetChanged();

        //make the card and the see all text invisible
        seeAllText.setVisibility(View.GONE);
        transactionCard.setVisibility(View.GONE);

        walletId.setText("Balance :");
        walletIdNum.setText("₦10,000.00");

        //change height of the wallet relative layout and transaction layout
        walletRelativeLayout1.getLayoutParams().height = 250;

        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) transactionLayout.getLayoutParams();
        params.topMargin = 300;
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