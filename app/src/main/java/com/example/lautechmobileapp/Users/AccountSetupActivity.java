package com.example.lautechmobileapp.Users;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lautechmobileapp.R;
import com.example.lautechmobileapp.Users.Registration.RegistrationExistingStudentActivity;

public class AccountSetupActivity extends AppCompatActivity {

    private TextView desc1, desc2, desc3, desc4, createAccountTextView;
    private FrameLayout frame1, frame2, frame3, frame4;
    private ImageView image1, image2, image3, image4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setup);

        //Framelayouts
        frame1 = findViewById(R.id.view1);
        frame2 = findViewById(R.id.view2);
        frame3 = findViewById(R.id.view3);
        frame4 = findViewById(R.id.view4);

        //Images
        image1 = findViewById(R.id.acountImage1);
        image2 = findViewById(R.id.acountImage2);
        image3 = findViewById(R.id.acountImage3);
        image4 = findViewById(R.id.acountImage4);

        //Textview
        desc1 = findViewById(R.id.acountDesc1);
        desc2 = findViewById(R.id.acountDesc2);
        desc3 = findViewById(R.id.acountDesc3);
        desc4 = findViewById(R.id.acountDesc4);
        createAccountTextView = findViewById(R.id.dontHaveAccoutnTextView);

        //Set top bar color
        setTopBarColor();

        //Make text bold
        SpannableStringBuilder boldText = makeBold("Have an account? Sign in");
        createAccountTextView.setText(boldText);

        //Onclicklistener for each frame
        frame1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              }
        });

        frame2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegistrationExistingStudentActivity.class);
                startActivity(intent);
                 }
        });

        frame3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        frame4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        createAccountTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setTopBarColor(){
        //set status bar color
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.loginTop));
    }

    public SpannableStringBuilder makeBold(String text){
        //Using SpannableStringBuilder method to make text bold
        SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 17 ,24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }
}