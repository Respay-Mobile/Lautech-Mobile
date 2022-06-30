package com.example.lautechmobileapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class MainClass {
    private final Context context1;


    public MainClass(Context context) {
        this.context1 = context;
    }

    public int getWidth(){
        WindowManager wm = (WindowManager) context1.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }

    public int getSize(){
        int defInt;
        int ss = context1.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        switch (ss){
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                defInt = 1;
                break;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                defInt = 3;
                break;
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                defInt = 4;
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
            case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
            default:
                defInt = 2;
                break;
        }

        return defInt;
    }



    public SpannableStringBuilder makesignInBold(String text){
        //Using SpannableStringBuilder method to make text bold
        SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 17 ,24, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }

    public SpannableStringBuilder makecreateHereBold(String text){
        //Using SpannableStringBuilder method to make text bold
        SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 22 ,34, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }



}
