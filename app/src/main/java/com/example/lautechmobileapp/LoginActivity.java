package com.example.lautechmobileapp;

import static android.icu.lang.UProperty.INT_START;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView createAccoutnTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccoutnTextView = findViewById(R.id.dontHaveAccoutnTextView);
        //set status bar color
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.loginTop));


        SpannableStringBuilder str = new SpannableStringBuilder("Dont have an account? Create Here");
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 22 ,33, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        createAccoutnTextView.setText(str);
    }
}